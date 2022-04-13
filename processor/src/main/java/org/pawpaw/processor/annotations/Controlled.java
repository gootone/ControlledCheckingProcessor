package org.pawpaw.processor.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controlled {
    Controlled.Function[] value();

    public enum Function{
        PHONE_CALL,
        SMS,
        WE_CHAT
    }
}
