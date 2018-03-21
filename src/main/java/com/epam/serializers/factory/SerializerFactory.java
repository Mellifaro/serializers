package com.epam.serializers.factory;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Viktor_Skapoushchenk on 3/20/2018.
 */
public class SerializerFactory {
    private static final Map<SerializerType, JsonSerializer> serializerMap;

    static {
        serializerMap = new EnumMap<>(SerializerType.class);
        serializerMap.put(SerializerType.GSON, new GsonWrapper());
        serializerMap.put(SerializerType.JACKSON, new JacksonWrapper());
    }

    public static JsonSerializer getSerializer(SerializerType type){
        if(type.equals(SerializerType.GSON)){
            return serializerMap.get(type);
        }
        return serializerMap.get(SerializerType.JACKSON);
    }
}
