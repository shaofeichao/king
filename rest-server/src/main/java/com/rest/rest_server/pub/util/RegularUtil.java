package com.rest.rest_server.pub.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
