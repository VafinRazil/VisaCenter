package com.rvafin.visacenter.imprecise_data_matcher.interfaces;

import com.rvafin.visacenter.imprecise_data_matcher.annotations.FieldParams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public interface StringFormatter {

    default String format(String delimiter) {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .filter(field -> field.isAnnotationPresent(FieldParams.class))
                .sorted((o1, o2) -> -Integer.compare(o1.getAnnotation(FieldParams.class).position(), o2.getAnnotation(FieldParams.class).position()))
                .map(field -> {
                    try {
                        if (field.getType() == LocalDate.class && field.getAnnotation(FieldParams.class).format() != null && !field.getAnnotation(FieldParams.class).format().isBlank()) {
                            return ((LocalDate) field.get(this)).format(DateTimeFormatter.ofPattern(field.getAnnotation(FieldParams.class).format()));
                        }
                        return String.valueOf(field.get(this));
                    }catch (Exception e){
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.joining(delimiter));
    }
}
