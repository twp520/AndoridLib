package com.colin.basemvp.net;

import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by tianweiping on 2017/12/11.
 */

public class RxNetLife {
    private static RxNetLife netLife = new RxNetLife();
    private HashMap<String, CompositeDisposable> disposableHashMap;

    private RxNetLife() {
        disposableHashMap = new HashMap<>();
    }

    public static RxNetLife getNetLife() {
        return netLife;
    }

    public void add(String key, Disposable disposable) {
        CompositeDisposable compositeDisposable = disposableHashMap.get(key);
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
            disposableHashMap.put(key, compositeDisposable);
        }
        compositeDisposable.add(disposable);
    }

    public void clear(String key) {
        CompositeDisposable compositeDisposable = disposableHashMap.get(key);
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

}
