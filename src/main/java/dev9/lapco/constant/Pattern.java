package dev9.lapco.constant;

public interface Pattern {

    static String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    static String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

    static String STUDENT_CODE_PATTERN = "^\\.\\d{2}\\.\\d{6}$";

    static String PHONE_NUMBER_PATTERN = "^0\\d{9}$";

    static String IDENTITY_CARD_PATTERN = "^\\d{12}$";

    static String DATE_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";
}
