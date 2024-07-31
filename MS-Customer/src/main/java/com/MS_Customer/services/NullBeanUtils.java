package com.MS_Customer.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Component
public class NullBeanUtils {
    public static void copyNonNullProperties(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
    private static String[] getNullPropertyNames(Object source){
        final Field[] allFields = source.getClass().getDeclaredFields();
        Set<String> nullNames = new HashSet<>();
        for (Field field : allFields) {
            boolean originalAccessible = field.isAccessible();
            field.setAccessible(true); //modificando a acessibilidade
            try {
                if (field.get(source) == null) {
                    nullNames.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                throw new BeansException("Could not acess field", e) {};
            } finally {
                field.setAccessible(originalAccessible); //restaurar o estado original
            }
        }
        return nullNames.toArray(new String[0]);
    }
}
