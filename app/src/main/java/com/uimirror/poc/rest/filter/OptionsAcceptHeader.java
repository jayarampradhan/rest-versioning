package com.uimirror.poc.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by jpradhan on 7/20/15.
 */
public class OptionsAcceptHeader implements ContainerResponseFilter {

    public static final String OPTIONS = "OPTIONS";
    private static final String ACCEPTPATCH = "Accept-Patch";
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        if (OPTIONS.equals(requestContext.getMethod())) {
            if (responseContext.getHeaderString(ACCEPTPATCH) == null) {
                responseContext.getHeaders().put(ACCEPTPATCH, Collections.<Object> singletonList(PatchingInterceptor.PATCH_MEDIA_TYPE));
            }
        }
    }
}
