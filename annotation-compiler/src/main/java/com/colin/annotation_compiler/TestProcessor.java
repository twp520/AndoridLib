package com.colin.annotation_compiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Created by tianweiping on 2017/11/29.
 */

public class TestProcessor extends AbstractProcessor {


    /**
     * 每一个注解处理器类都必须有一个空的构造函数。然而，这里有一个特殊的init()方法，它会被注解处理工具调用，
     * 并输入ProcessingEnviroment参数。ProcessingEnviroment提供很多有用的工具类Elements,Types和Filer。
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    /**
     * 这相当于每个处理器的主函数main()。 在这里写扫描、评估和处理注解的代码，以及生成Java文件。
     * 输入参数RoundEnviroment，可以让查询出包含特定注解的被注解元素。
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }

    /**
     * 这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，
     * 包含本处理器想要处理的注解类型的合法全称。换句话说，在这里定义你的注解处理器注册到哪些注解上。
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return super.getSupportedAnnotationTypes();
    }

    /**
     * 返回支持的java版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }
}
