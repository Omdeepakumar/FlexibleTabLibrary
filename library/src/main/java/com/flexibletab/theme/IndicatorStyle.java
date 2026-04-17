package com.flexibletab.theme;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for indicator styles
 */
@IntDef({
    IndicatorStyle.UNDERLINE,
    IndicatorStyle.PILL,
    IndicatorStyle.DOT,
    IndicatorStyle.NONE,
    IndicatorStyle.MATERIAL,
    IndicatorStyle.CAPSULE
})
@Retention(RetentionPolicy.SOURCE)
public @interface IndicatorStyle {
    int UNDERLINE = 0;
    int PILL = 1;
    int DOT = 2;
    int NONE = 3;
    int MATERIAL = 4;
    int CAPSULE = 5;
}
