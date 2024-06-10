package dev9.lapco.commonUtil;

import dev9.lapco.constant.Pattern;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class OcpalUtil implements Pattern {

    @Value("${spring.application.name}")
    private String name;

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

    public String studentCodeGenerator(String userIdentityNumber){
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return name + currentDate.substring(2,4) + userIdentityNumber.substring(6,12);
    }

    public String teacherCodeGenerator(String userIdentityNumber){
        return name + userIdentityNumber.substring(6,11);
    }



}
