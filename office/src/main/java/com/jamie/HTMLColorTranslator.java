package com.jamie;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLColorTranslator {

    public static Color translate(String rgbStr) {

        if (rgbStr.startsWith("rgb")){
            Pattern c = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
            Matcher m = c.matcher(rgbStr);

            if (m.matches()) {
                return new Color(Integer.parseInt(m.group(1)),  // r
                        Integer.parseInt(m.group(2)),  // g
                        Integer.parseInt(m.group(3))); // b
            }
        } else if(rgbStr.startsWith("#")) {
            return Color.decode(rgbStr);
        }

        return null;
    }


}
