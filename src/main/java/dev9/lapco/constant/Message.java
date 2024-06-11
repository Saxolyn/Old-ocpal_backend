package dev9.lapco.constant;

public interface Message {
//Message success: MI
//Message false: MF
//Message error: ME

    static final String MI0001 = "Message sent successfully";

    static final String MI0002 = "Your password is restored successfully to [lapco], you should change your password after login to secure your account";

    static final String MI0003 = "Password changed successfully";

    static final String MI0004 = "New Account created successfully";

    static final String MI0005 = "No account has been created yet";

    static final String MI0006 = "Progress successfully";

    static final String ME0001 = "Unauthorized error: {}";

    static final String ME0002 = "You are not authorized";

    static final String ME0003 = "Cannot find role of user with username/phone number:";

    static final String ME0004 = "Invalid credentials";

    static final String ME0005 = "username already exists";

    static final String ME0006 = "Phone number or identity number is invalid";

    static final String ME0007 = "User not found";

    static final String ME0008 = "You cannot changed password ";

    static final String ME0009 = "New Password invalid. Less 8 character with 01 uppercase, 01 special character, 01 number";

    static final String ME0010 = "Account has been deleted";

    static final String MF0001 = "Error occur when creating new user ";

    static final String MF0002 = "Error occur when delete user ";
}
