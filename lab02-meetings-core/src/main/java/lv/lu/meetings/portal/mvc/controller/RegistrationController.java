package lv.lu.meetings.portal.mvc.controller;

import javax.servlet.http.HttpSession;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.interfaces.CommonJpaDAO;
import lv.lu.meetings.portal.mvc.WebConst;
import lv.lu.meetings.portal.mvc.validator.RegistrationValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the new user registration process
 */
@Controller
public class RegistrationController {
    
    /*
     * Logical name of the following physical view:
     * \lab02-meetings\lab02-meetings-portal\src\main\webapp\WEB-INF\jsp\register.jsp
     */
    private static final String FORM_VIEW = "register";
    
    private static final String SUCCESS_VIEW = "redirect:home";
    
    private static final String MODEL_ATTRIBUTE = "user";
    
    @Autowired
    private RegistrationValidator registrationValidator;

    @Autowired
    private CommonJpaDAO jpaDAO;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String setupForm(ModelMap model) {
        /* Empty form setup */
        model.addAttribute(MODEL_ATTRIBUTE, new User());
        return FORM_VIEW;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processSubmit(HttpSession session, @ModelAttribute(MODEL_ATTRIBUTE) User user, BindingResult result) {

        // validate form data
        registrationValidator.validate(user, result);
        
        if (result.hasErrors()) {
            // display form with validation errors
            return FORM_VIEW;
        }

        /* Save new user in database */
        jpaDAO.save(user);        
        
        /* Log user in - save info in HTTP session */
        session.setAttribute(WebConst.SessionAttribute.SESSION_USER, user);  
        
        System.out.println("User '" + user.getUsername() + "' successfully registered");
        return SUCCESS_VIEW;
    }
}
