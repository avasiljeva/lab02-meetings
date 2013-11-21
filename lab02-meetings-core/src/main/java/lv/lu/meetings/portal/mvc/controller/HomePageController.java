package lv.lu.meetings.portal.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.domain.jpa.notification.Notification;
import lv.lu.meetings.domain.redis.Friend;
import lv.lu.meetings.interfaces.service.NotificationService;
import lv.lu.meetings.interfaces.service.UserService;
import lv.lu.meetings.portal.mvc.WebConst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for displaying application home page.
 * 
 * Supports multiple tab views: Main, Friends, Notifications, Meetings. 
 */
@Controller
public class HomePageController {

	private static final String ATTR_SELECTED_TAB = "selected_tab";

    @Autowired
    private UserService userService;
    
    @Autowired
    private NotificationService notificationService;
    
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String display(ModelMap model, HttpSession session) {
		// display Main tab by default
    	return displayUserMain(model, session);
    }
	
	/**
	 * Load data for Main tab and display it
	 */
	@RequestMapping(value = "/home/main", method = RequestMethod.GET)
    public String displayUserMain(ModelMap model, HttpSession session) {
        
		/* 
		 * TODO: load necessary data
		 */
        
        model.addAttribute(ATTR_SELECTED_TAB, "main");        
        return "home";
    }
	
	/**
	 * Load data for Friends tab and display it
	 */
	@RequestMapping(value = "/home/friends", method = RequestMethod.GET)
    public String displayFriends(ModelMap model, HttpSession session) {
 
        User user = getCurrentUser(session);        
		List<Friend> friends = userService.getFriends(user.getId());
		
		model.addAttribute("friends", friends);
        model.addAttribute(ATTR_SELECTED_TAB, "friends");
        return "home";
    }
	
    @RequestMapping(value = "/removeFriend", method = RequestMethod.POST)
    public String removeFriend(@RequestParam("id") Long friendId, HttpSession session) {

        User user = getCurrentUser(session);
        userService.removeFriend(user, friendId);
    	
    	return "redirect:" + "/meetings/home/friends";
    }
    
	/**
	 * Display Notifications tab
	 */
	@RequestMapping(value = "/home/notifications", method = RequestMethod.GET)
    public String displayNotifications(ModelMap model, HttpSession session) {
 
        User user = getCurrentUser(session);        
		List<Notification> notifications = notificationService.getRecentNotifications(user.getId());
		
		model.addAttribute("notifications", notifications);
        model.addAttribute(ATTR_SELECTED_TAB, "notifications");
        return "home";
    }

	/**
	 * Get current user from HTTP session
	 */
	private User getCurrentUser(HttpSession session) {
		return (User)session.getAttribute(WebConst.SessionAttribute.SESSION_USER);
	}
}
