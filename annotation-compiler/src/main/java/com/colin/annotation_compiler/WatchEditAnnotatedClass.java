package com.colin.annotation_compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by tianweiping on 2017/11/28.
 */

public class WatchEditAnnotatedClass {
    public TypeElement mClassElement;

    public List<WatchEditField> mFiled;

    public Elements mElementUtils;

    public WatchEditAnnotatedClass(TypeElement classElement, Elements elementUtils) {
        this.mClassElement = classElement;
        this.mElementUtils = elementUtils;
        this.mFiled = new ArrayList<>();
    }

    public String getFullClassName() {
        return mClassElement.getQualifiedName().toString();
    }


    public void addField(WatchEditField field) {
        mFiled.add(field);
    }

    public JavaFile generateWatcher() {
        String packageName = getPackageName(mClassElement);
        String className = getClassName(mClassElement, packageName);
        //获取到当前使用了注解标记的类名(MainActivity)
        ClassName bindClassName = ClassName.get(packageName, className);
        //构建出重写的inject方法
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(mClassElement.asType()), "host", Modifier.FINAL)
                .addParameter(TypeName.OBJECT, "source")
                .addParameter(TypeUtil.WATCHER, "watcher");

        //添加代码
        for (WatchEditField field : mFiled) {
            //获得每个button要监听的EditText的id
            int[] ids = field.getResIds();
            if (ids != null) {
                //为每个EditText添加监听
                for (int i = 0; i < ids.length; i++) {
                    //添加监听
                    methodBuilder.addStatement("watcher.watchEdit(watcher.findView(source,$L),$N)",
                            ids[i], "host." + field.getFieldName());
//                    methodBuilder.addStatement("watcher.watchEdit(watcher.findView(source,$L),$N)",
//                            ids[i], field);
                }
            }
        }

//        构建类 MainActivity$$Injector
        TypeSpec finderClass = TypeSpec.classBuilder(bindClassName.simpleName() + "$$Injector")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeUtil.INJECTOR, TypeName.get(mClassElement.asType())))
                .addMethod(methodBuilder.build()) //添加刚刚生成的injector方法
                .build();
        //生成一个java文件
        return JavaFile.builder(packageName, finderClass).build();
    }


    public String getPackageName(TypeElement type) {
        return mElementUtils.getPackageOf(type).getQualifiedName().toString();
    }


    private static String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
    }
}
