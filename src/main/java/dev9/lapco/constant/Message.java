package dev9.lapco.constant;

public interface Message {
//Message success: MI
//Message false: MF
//Message error: ME
    static final String MI0001 = "Message sent successfully";

    static final String MI0002 = "User not found";

    static final String MI0003 = "Cannot set user authentication: {}";

    static final String MI0004 = "You cannot changed password ";

    static final String MI0005 = "Your password is restored successfully to [lapco], you should change your password after login to secure your account";

    static final String ME0003 = "Unauthorized error: {}";

    static final String ME0004 = "Error: Unauthorized";

    static final String ME0005 = "Cannot find role of user with username/phone number:";

    static final String ME0006 = "Invalid credentials";
}
