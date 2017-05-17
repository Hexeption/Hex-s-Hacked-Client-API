package uk.co.hexeption.api.mod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Hexeption on 16/01/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ModInfo {

    String name();

    Category category();

    String description() default "";

    boolean visible() default true;

    int bind() default 0;

}
