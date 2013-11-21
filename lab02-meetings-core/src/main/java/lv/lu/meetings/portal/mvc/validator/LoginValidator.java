package lv.lu.meetings.portal.mvc.validator;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.interfaces.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for "Login" form.
 */
@Component
public class LoginValidator implements Validator {

	@Autowired
    private UserService userService;
    
    @SuppressWarnings("rawtypes")
    public boolean supports(Class givenClass){
        return givenClass.equals(User.class);
    }

    public void validate(Object obj, Errors errors){
        User user = (User)obj;
        if (user == null){
            errors.reject("error.nullpointer", "Null data received");
            return;
        }
        if (!StringUtils.hasText(user.getUsername())){
            errors.rejectValue("username", "error.empty", "Empty username!");
            return;
        }
        if (!StringUtils.hasText(user.getPassword())){
            errors.rejectValue("password", "error.empty", "Empty password!");
            return;
        }
        
        // try to authenticate user
        User userFromDB = userService.getByUsername(user.getUsername());                
        if(userFromDB != null){
            // check that password matches provided value
            if (user.getPassword().equals(userFromDB.getPassword())){
                // success
                return;
            }
            else{
                errors.rejectValue("password", "error.wrong_password", "Wrong password!");
            }
        } else {
            errors.rejectValue("username", "error.wrong_username", "Wrong username!");
        }
    }
}