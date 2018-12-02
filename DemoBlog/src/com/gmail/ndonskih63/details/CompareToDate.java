package com.gmail.ndonskih63.details;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class CompareToDate implements Comparator<ThisPost>{
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    @Override
    public int compare(ThisPost o1, ThisPost o2) {
        return LocalDate.parse(o1.getDate()).
                compareTo(LocalDate.parse(o2.getDate()));
    }
}
