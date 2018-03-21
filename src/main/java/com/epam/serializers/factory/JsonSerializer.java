package com.epam.serializers.factory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Viktor_Skapoushchenk on 3/20/2018.
 */
public interface JsonSerializer{

    <E> void writeObjectToJson (File file, E element, boolean isPretty) throws IOException;

    <E> E readObjectFromJsonFile(File file, Class<E> clazz) throws IOException;

    <E> void writeListObjectsToJson(File file, List<E> elements, boolean isPretty) throws IOException;

    <E> List<E> readListElementsFromJsonFile(File file, Class<E> clazz) throws IOException;
}
