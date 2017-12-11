package com.colin.basemvp.net;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by tianweiping on 2017/12/11.
 */

public class DesResponesConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private Gson gson = new Gson();

    public DesResponesConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException, JsonSyntaxException {
        try {
            String result = value.string();
//            String res = Des3.decode(result);
            return gson.fromJson(result, type);
        } catch (Exception e) {
            return null;
        } finally {

        }
    }
}
