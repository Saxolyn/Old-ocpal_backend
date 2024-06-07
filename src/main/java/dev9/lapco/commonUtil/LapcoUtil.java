package dev9.lapco.commonUtil;

import dev9.lapco.constant.Pattern;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LapcoUtil implements Pattern {
    public static String localDateTimeToString (LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        return localDateTime.format(formatter);
    }

    public static String createOTP(){
        return RandomStringUtils.random(6,false,true);
    }




}
