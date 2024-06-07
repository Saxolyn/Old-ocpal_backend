package dev9.lapco.commonUtil;

import dev9.lapco.constant.Pattern;
import org.apache.logging.log4j.util.Strings;

import java.util.regex.Matcher;

public class ValidateUtil implements Pattern {

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

    public static boolean isValidIdentityCard(String identityCard) {
        if (Strings.isEmpty(identityCard) || Strings.isBlank(identityCard)) {
            return false;
        }
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(Pattern.IDENTITY_CARD_PATTERN);
        Matcher matcher = pattern.matcher(identityCard);
        return matcher.matches();
    }
}
