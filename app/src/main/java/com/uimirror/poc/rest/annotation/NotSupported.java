package com.uimirror.poc.rest.annotation;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Resource Marked with this annotation will be stopped from the request getting evaluated and
 * respond with 410 as status code.
 *
 * Created by jpradhan on 7/21/15.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface NotSupported {
    /**
     * Message to be thrown to client when not supported
     * @return
     */
    String message() default  "This Api has been Deprecated, please see the documentation for more details";

    String version() default "";

}
