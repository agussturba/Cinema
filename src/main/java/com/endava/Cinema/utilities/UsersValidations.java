package com.endava.Cinema.utilities;

import com.endava.Cinema.exceptions.userException.InvalidPasswordException;
import com.endava.Cinema.exceptions.userException.InvalidRoleException;
import com.endava.Cinema.vo.UserVo;

import javax.xml.bind.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersValidations {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    private static void validateEmail(String email) throws ValidationException {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (email == null) {
            throw new ValidationException("Not a valid email");
        }
        if (!matcher.matches()) {
            throw new ValidationException("Not a valid email");
        }
    }

    private static boolean isValidPassword(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private static boolean isValidRole(String role) {
        if (role != "ADMIN" && role != "CLIENT"){
            return false;
        }
        return true;
    }
    public static void validateUserData(UserVo userVo) throws InvalidRoleException, InvalidPasswordException, ValidationException {
        if (!isValidRole(userVo.getRole())){
            throw new InvalidRoleException();
        }
        if(!isValidPassword(userVo.getPassword())){
            throw new InvalidPasswordException();
        }
        validateEmail(userVo.getEmail());
    }
}
