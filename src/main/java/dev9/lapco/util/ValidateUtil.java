package dev9.lapco.util;

import dev9.lapco.config.security.UserDetailsImpl;
import dev9.lapco.constant.ERole;
import dev9.lapco.constant.Message;
import dev9.lapco.constant.Pattern;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.regex.Matcher;

public class ValidateUtil implements Pattern, Message {



    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (Strings.isEmpty(phoneNumber) || Strings.isBlank(phoneNumber)) {
            return false;
        }
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(Pattern.PHONE_NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        if (Strings.isEmpty(email) || Strings.isBlank(email)) {
            return false;
        }
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(Pattern.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static boolean isValidPassword(String password) {
        if (Strings.isEmpty(password) || Strings.isBlank(password)) {
            return false;
        }
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(Pattern.PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidPassword(String oldPassword, String newPassword, String confirmPassword) {
        if (Strings.isBlank(newPassword) || Strings.isBlank(oldPassword) || Strings.isBlank(confirmPassword)) {
            return false;
        }

        if (!newPassword.equals(confirmPassword)) {
            return false;
        }

        if (!isValidPassword(newPassword)) {
            return false;
        }

        return !oldPassword.equals(newPassword);
    }

    public static boolean isValidIdentityCard(String identityCard) {
        if (Strings.isEmpty(identityCard) || Strings.isBlank(identityCard)) {
            return false;
        }
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(Pattern.IDENTITY_CARD_PATTERN);
        Matcher matcher = pattern.matcher(identityCard);
        return matcher.matches();
    }

    public static boolean isValidAuthority() {
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getCredentials();
        if (userDetails == null) {
            return false;
        }
        if (!userDetails.getRole().equals(ERole.SUPER_ADMIN) && !userDetails.getRole().equals(ERole.ADMIN)) {
            return false;
        }
        return true;
    }


}
