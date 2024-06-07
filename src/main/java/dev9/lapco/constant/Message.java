package dev9.lapco.constant;

public interface Message {
//Message success: MS
//Message false: MF
//Message error: ME
    static final String MS0001 = "Message sent successfully";

    static final String ME0001 = "User not found";

    static final String ME0002 = "Cannot set user authentication: {}";

    static final String ME0003 = "Unauthorized error: {}";

    static final String ME0004 = "Error: Unauthorized";
}
