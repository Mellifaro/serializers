package com.epam.serializers.factory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Viktor_Skapoushchenk on 3/20/2018.
 */
public class JacksonWrapper  implements JsonSerializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <E> void writeObjectToJson(File file, E element, boolean isPretty) throws IOException {
        if(isPretty) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, element);
        }else{
            objectMapper.writeValue(file, element);
        }
    }

    @Override
    public <E> E readObjectFromJsonFile(File file, Class<E> clazz) throws IOException {
        return objectMapper.readValue(file, clazz);
    }

    @Override
    public <E> void writeListObjectsToJson(File file, List<E> elements, boolean isPretty) throws IOException {
        if(isPretty){
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, elements);
        }else {
            objectMapper.writeValue(file, elements);
        }
    }

    @Override
    public <E> List<E> readListElementsFromJsonFile(File file, Class<E> clazz) throws IOException {
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
