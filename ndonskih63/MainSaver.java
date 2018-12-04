package com.gmail.ndonskih63;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainSaver {

    public static void main(String[] args) {
        TextConteiner cont = new TextConteiner();
        Class<?> clazz = cont.getClass();

        SaveTo saveTo = clazz.getAnnotation(SaveTo.class);
        String path = saveTo.path();
        Method[] methods = clazz.getDeclaredMethods();
        try {
            for (Method meth : methods) {
                if (meth.isAnnotationPresent(Saver.class)) {
                    meth.invoke(cont, path);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            
        }
        System.out.println("I'm do it!");
    }
}
