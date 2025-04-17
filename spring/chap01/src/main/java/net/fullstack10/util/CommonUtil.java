package net.fullstack10.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommonUtil {
    public boolean isNumeric(String str) {
        return str != null && !str.trim().isEmpty() && str.matches("[0-9]+");
    }

    public int parseInt(String str) {
        return (isNumeric(str) ? Integer.parseInt(str) : 0);
    }

    public int parseInt(String str, int def) {
        return (isNumeric(str) ? Integer.parseInt(str) : def);
    }

    public int setPageParam(String param, int def) {
        if (param == null || param.trim().isEmpty() || !isNumeric(param)) {
            return def;
        }
        int value = Integer.parseInt(param.trim());
        return (value > 0 ? value : def);
    }

    public String setStringParam(String param) {
        return param != null && !param.trim().isEmpty() ? param.trim() : "";
    }

    public String setDateParam(String param, String pattern) {
        if (param == null || param.trim().isEmpty()) return "";

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setLenient(false);

        try {
            formatter.parse(param.trim());
            return param.trim();
        } catch (ParseException e) {
            return "";
        }
    }
}
