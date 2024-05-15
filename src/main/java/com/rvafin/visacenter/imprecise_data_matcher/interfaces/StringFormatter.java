package com.rvafin.visacenter.imprecise_data_matcher.interfaces;

import com.rvafin.visacenter.imprecise_data_matcher.annotations.FieldParams;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public interface StringFormatter {

    default String format(String delimiter) {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .filter(field -> field.getAnnotation(FieldParams.class) != null)
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(FieldParams.class).position())).map(this::formatLine)
                .collect(Collectors.joining(delimiter));
    }

    private String formatLine(Field field) {
        String formatedLine = "";
        if (field != null){
            String format = getFormatOfField(field);
            try {
                if (!format.isBlank()) {
                    if (field.getType() == LocalDate.class) {
                        formatedLine = ((LocalDate) field.get(this)).format(DateTimeFormatter.ofPattern(format));
                    } else if (field.getType() == LocalDateTime.class) {
                        formatedLine = ((LocalDateTime) field.get(this)).format(DateTimeFormatter.ofPattern(format));
                    } else if (field.getType() == Double.class) {
                        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
                        DecimalFormat decimalFormat = new DecimalFormat(format, otherSymbols);
                        return decimalFormat.format(field.get(this));
                    }
                }else{
                    formatedLine = String.valueOf(field.get(this));
                }
            }catch (Exception ignored){}
        }return formatedLine;
    }

    private String getFormatOfField(Field field){
        String format = "";
        if (field.getAnnotation(FieldParams.class).format() != null && !field.getAnnotation(FieldParams.class).format().isEmpty()) {
            format = field.getAnnotation(FieldParams.class).format();
        }
        return format;
    }
}
