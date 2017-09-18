package com.nanter1986.blockpusher;

import java.lang.reflect.Field;

/**
 * Created by user on 18/9/2017.
 */

public class FieldPrinter {
    public static String printFields(Object c){
        String result="";
        for (Field field : c.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = null;
            try {
                value = field.get(c);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.printf("%s: %s%n", name, value);
        }
        return result;
    }
}
