package com.colin.annotation_compiler;

import com.colin.annotation.WatchEdit;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by tianweiping on 2017/11/28.
 */

public class WatchEditField {
    private VariableElement mFieldElement;

    private int[] mEditIds;

    public WatchEditField(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.FIELD) {
            throw new IllegalArgumentException(String.format("Only field can be annotated with @%s",
                    WatchEdit.class.getSimpleName()));
        }
        mFieldElement = (VariableElement) element;

        WatchEdit bindView = mFieldElement.getAnnotation(WatchEdit.class);
        if (bindView != null) {
            mEditIds = bindView.editIds();
            if (mEditIds == null && mEditIds.length <= 0) {
                throw new IllegalArgumentException(String.format("editIds() in %s for field % is not valid",
                        WatchEdit.class.getSimpleName(), mFieldElement.getSimpleName()));
            }
        }
    }


    public Name getFieldName() {
        return mFieldElement.getSimpleName();
    }


    public int[] getResIds() {
        return mEditIds;
    }

    public TypeMirror getFieldType() {
        return mFieldElement.asType();
    }

    public Object getConstantValue(){
        return mFieldElement.getConstantValue();
    }
}
