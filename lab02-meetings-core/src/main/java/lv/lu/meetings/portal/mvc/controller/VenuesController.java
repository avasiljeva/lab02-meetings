package lv.lu.meetings.portal.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import lv.lu.meetings.domain.jpa.venue.Venue;
import lv.lu.meetings.interfaces.service.VenueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for displaying venues page
 */
@Controller
public class VenuesController {

    private static final String VIEW_NAME = "venues";

    @Autowired
    private VenueService venueService;
    
    @RequestMapping(value = "/venues", method = RequestMethod.GET)
    public String display(ModelMap model, HttpSession session) {

        List<Venue> venues = venueService.getAllVenues();
        model.addAttribute("venues", venues);
        
        return VIEW_NAME;
    }
}
