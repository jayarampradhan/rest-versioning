package com.uimirror.poc.rest.annotation;

import com.uimirror.poc.rest.filter.PatchingInterceptor;

import javax.ws.rs.NameBinding;
import java.lang.annotation.*;

/**
 * Created by jpradhan on 7/20/15.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
//@HttpMethod("PATCH")
@Documented
@NameBinding
public @interface PATCH {
    /**
     * Specifies the which method, get current state will give.
     * @return string representation of the method name.
     */
    String methodName() default "";
    /**
     * If to invoke get current state, method needs argument, then by which key its been passed.
     * Remember it will be required when method invocation requires some argument.
     * @return key using which value will be retrived.
     */
    String key() default "";
    /**
     * Which source argument might be present possibly PATH or QueryParam
     * @return from where argument will be looked on.
     */
    PatchingInterceptor.ParamInTypes lookAt() default PatchingInterceptor.ParamInTypes.PATH;
}
