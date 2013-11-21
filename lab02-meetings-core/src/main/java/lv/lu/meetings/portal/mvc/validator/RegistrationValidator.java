package lv.lu.meetings.portal.mvc.validator;

import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.interfaces.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for "Registration" form.
 */
@Component
public class RegistrationValidator implements Validator{

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
        if (!StringUtils.hasText(user.getPasswordConfirm())){
            errors.rejectValue("passwordConfirm", "error.empty", "Empty password confirm!");
            return;
        }
        if (!StringUtils.hasText(user.getFirstName())){
            errors.rejectValue("firstName", "error.empty", "Empty first name!");
            return;
        }
        if (!StringUtils.hasText(user.getLastName())){
            errors.rejectValue("lastName", "error.empty", "Empty last name!");
            return;
        }
                    
        // check if username is free
        User userFromDB = userService.getByUsername(user.getUsername());                
        if (userFromDB != null){            
            errors.rejectValue("username", "error.empty", "User with this username already exists!");
        }       
    }
}