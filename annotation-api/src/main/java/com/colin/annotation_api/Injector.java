package com.colin.annotation_api;

/**
 * Created by tianweiping on 2017/11/28.
 */

public interface Injector<T> {
    /**
     * @param host    目标
     * @param source  来源
     * @param watcher 提供具体使用的方法 查找edit，添加监听
     */
    void inject(T host, Object source, Watcher watcher);
}
