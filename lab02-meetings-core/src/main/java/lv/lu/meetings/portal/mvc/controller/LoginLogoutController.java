package lv.lu.meetings.portal.mvc.controller;

import javax.servlet.http.HttpSession;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.interfaces.service.UserService;
import lv.lu.meetings.portal.mvc.WebConst;
import lv.lu.meetings.portal.mvc.validator.LoginValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for performing login and logout operations
 */
@Controller
public class LoginLogoutController {
    
    private static final String FORM_VIEW = "login";
    private static final String LOGIN_SUCCESS_VIEW = "redirect:home";
    
    private static final String MODEL_ATTRIBUTE = "user";
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private LoginValidator loginValidator;
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String setupForm(ModelMap model)
    {
        /* Empty form setup */
        model.addAttribute(MODEL_ATTRIBUTE, new User());
        return FORM_VIEW;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String processSubmit(HttpSession session, @ModelAttribute(MODEL_ATTRIBUTE) User user, BindingResult result)
    {
        // validate form data
        loginValidator.validate(user, result);
        
        if (result.hasErrors()) {
            // display form with validation errors
            return FORM_VIEW;
        }

        /* Store user's information in HTTP session */
        user = userService.getByUsername(user.getUsername());
        session.setAttribute(WebConst.SessionAttribute.SESSION_USER, user);
        
        System.out.println("User '" + user.getUsername() + "' logged in");
        return LOGIN_SUCCESS_VIEW;
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String processLogout(HttpSession session, ModelMap model) 
    {
        User user = (User)session.getAttribute(WebConst.SessionAttribute.SESSION_USER);
        System.out.println("User '" + user.getUsername() + "' logged out");
        
        // invalidate session
        session.removeAttribute(WebConst.SessionAttribute.SESSION_USER);
        session.invalidate();
        return "redirect:/";
    }
}