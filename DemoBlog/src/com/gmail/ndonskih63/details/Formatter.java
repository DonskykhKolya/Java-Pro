package com.gmail.ndonskih63.details;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Formatter {
    private final String form;
    private final String info;

    public Formatter(String format, String information) {
        this.form = format;
        this.info = information;
    }
    public List<String> getInfo() {
        if (form.equals("csv")) {
            List<String> tempList = new ArrayList<>(Arrays.asList(info.
                    replace("\"", "|").replace("|,", "#").
                    replace("|", "").split("#")));
            List<String> tempFormattedLis = new ArrayList<>();
            for (String s : tempList) {
                tempFormattedLis.add(s.trim());
            }
            return tempFormattedLis;
        }
        System.out.println("Error!");
        return new ArrayList<>();
    }
}

