package com.colin.basemvp.net;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by tianweiping on 2017/12/11.
 */

public class DesConverterFactor extends Converter.Factory {
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        try {
            return new DesResponesConverter<>(type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
