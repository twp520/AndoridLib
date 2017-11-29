package com.colin.annotation_compiler;

import com.colin.annotation.WatchEdit;
import com.google.auto.service.AutoService;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * Created by tianweiping on 2017/11/28.
 */

@AutoService(Processor.class)
public class WatchEditProcessor extends AbstractProcessor {
    //文件工具类
    private Filer mFiler;

    //处理元素的工具类
    private Elements mElementUtils;

    //log工具类
    private Messager mMessager;

    //使用了注解的类的包装类的集合
    private Map<String, WatchEditAnnotatedClass> mAnnotatedClassMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementUtils = processingEnvironment.getElementUtils();
        mMessager = processingEnvironment.getMessager();
        mFiler = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //每次都执行，所以要先清空
        mAnnotatedClassMap.clear();
        try {
            processWatchEdit(roundEnvironment);
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
            return true;
        }

        try {

            for (WatchEditAnnotatedClass annotatedClass : mAnnotatedClassMap.values()) {
                info("generating file for %s", annotatedClass.getFullClassName());

                annotatedClass.generateWatcher().writeTo(mFiler);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error("Generate file failed,reason:%s", e.getMessage());
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();

        types.add(WatchEdit.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    private void processWatchEdit(RoundEnvironment roundEnv) {
        //遍历处理 使用了 @WatchEdit 注解的类
        //一个element代表一个元素（可以是类，成员变量等等）
        for (Element element : roundEnv.getElementsAnnotatedWith(WatchEdit.class)) {
            WatchEditAnnotatedClass annotatedClass = getAnnotatedClass(element);
            //通过 roundEnv工具构建一个成员变量
            WatchEditField field = new WatchEditField(element);
            //添加使用了@WatchEdit注解的成员变量
            annotatedClass.addField(field);
        }
    }


    private WatchEditAnnotatedClass getAnnotatedClass(Element element) {
        //得到一个 类元素
        TypeElement encloseElement = (TypeElement) element.getEnclosingElement();
        //拿到类全名
        String fullClassName = encloseElement.getQualifiedName().toString();
        //先从缓存中取
        WatchEditAnnotatedClass annotatedClass = mAnnotatedClassMap.get(fullClassName);
        if (annotatedClass == null) {
            //没有就构建一个
            annotatedClass = new WatchEditAnnotatedClass(encloseElement, mElementUtils);
            //放入缓存
            mAnnotatedClassMap.put(fullClassName, annotatedClass);
        }
        return annotatedClass;
    }

    private void error(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }

    private void info(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
    }
}
