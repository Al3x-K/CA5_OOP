package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonConverter
{
    private Gson gson;

    public JsonConverter()
    {
        this.gson = new Gson();
    }

    public <T> String ConvertObjectToJsonString(T object)
    {
        return gson.toJson(object);
    }

    public <T> String convertListToJsonString(List<T> list)
    {
        return gson.toJson(list);
    }

    public <T> T ConvertJsonStringToObject(String json, Class<T> object)
    {
        return gson.fromJson(json, object);
    }

    public <T> List<T> convertJsonStringToList(String json, Class<T> a)
    {
        return gson.fromJson(json, TypeToken.getParameterized(List.class, a).getType());
    }
}
