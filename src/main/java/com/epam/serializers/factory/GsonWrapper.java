package com.epam.serializers.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor_Skapoushchenk on 3/20/2018.
 */
public class GsonWrapper implements JsonSerializer {
    private Gson gson = new Gson();

    @Override
    public <E> void writeObjectToJson(File file, E element, boolean isPretty) throws IOException {
        try(Writer writer = new FileWriter(file)){
            gson = isPretty ? new GsonBuilder().setPrettyPrinting().create() : gson;
            gson.toJson(element, writer);
        }
    }

    @Override
    public <E> E readObjectFromJsonFile(File file, Class<E> clazz) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(file));
        return gson.fromJson(reader, clazz);
    }

    @Override
    public <E> void writeListObjectsToJson(File file, List<E> elements, boolean isPretty) throws IOException {
        try(Writer writer = new FileWriter(file)){
            gson = isPretty ? new GsonBuilder().setPrettyPrinting().create() : gson;
            gson.toJson(elements, writer);
        }
    }

    @Override
    public <E> List<E> readListElementsFromJsonFile(File file, Class<E> clazz) throws IOException {
        Type type = new ListParameterizedType(clazz);
        JsonReader reader = new JsonReader(new FileReader(file));
        return gson.fromJson(reader, type);
    }

    private static class ListParameterizedType implements ParameterizedType {

        private Type type;

        public ListParameterizedType(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[] {type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }

        // implement equals method too! (as per javadoc)

    }
}
