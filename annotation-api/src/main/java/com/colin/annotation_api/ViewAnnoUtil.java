package com.colin.annotation_api;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianweiping on 2017/11/28.
 */

public class ViewAnnoUtil {
    private static ActivityWatcher actWatcher = new ActivityWatcher();

    private static Map<String, Injector> WATCH_MAP = new HashMap<>();

    public static void watch(Activity activity) {
        watch(activity, activity, actWatcher);
    }

    private static void watch(Object host, Object source, Watcher watcher) {
        String className = host.getClass().getName();
        try {
            Injector injector = WATCH_MAP.get(className);
            if (injector == null) {
                Class<?> finderClass = Class.forName(className + "$$Injector");
                injector = (Injector) finderClass.newInstance();
                WATCH_MAP.put(className, injector);
            }
            injector.inject(host, source, watcher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
