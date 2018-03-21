package com.epam.serializers.service;

import java.lang.reflect.Field;
import java.util.Arrays;

public class MergeService {

    /**
     * Method merges two elements and returns first changed element
     */
    public <E> E mergeElements(E element1, E element2) throws IllegalAccessException {
        Field[] fields = element1.getClass().getDeclaredFields();
        Arrays.asList(fields).forEach(f -> f.setAccessible(true));

        for(Field field : fields){
            Object valueFirstElem = field.get(element1);
            Object valueSecondElem = field.get(element2);
            if(valueFirstElem == null){
                field.set(element1, valueSecondElem);
            }
        }
        return element1;
    }
}
