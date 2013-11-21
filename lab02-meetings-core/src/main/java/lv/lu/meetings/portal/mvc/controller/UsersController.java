package lv.lu.meetings.portal.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.interfaces.service.UserService;
import lv.lu.meetings.portal.mvc.WebConst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for displaying a list of all portal users
 */
@Controller
public class UsersController {

    private static final String VIEW_NAME = "users";

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String displayStartPage(ModelMap model, HttpSession session) {
        // get current user from session
        User user = (User)session.getAttribute(WebConst.SessionAttribute.SESSION_USER);
        
        // load all users except current
        List<User> users = userService.getAllUsers(user.getUsername());
        model.addAttribute("users", users);
        return VIEW_NAME;
    }
    
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public String addFriend(@RequestParam("id") Long friendId, HttpSession session) {
    	
    	// get current user from session
        User user = (User)session.getAttribute(WebConst.SessionAttribute.SESSION_USER);
        userService.addFriend(user, friendId);
    	
    	return "redirect:" + VIEW_NAME;
    }
}
