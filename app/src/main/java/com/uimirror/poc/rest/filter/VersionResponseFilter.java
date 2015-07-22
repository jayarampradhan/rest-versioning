package com.uimirror.poc.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * Created by jpradhan on 7/21/15.
 */
public class VersionResponseFilter implements ContainerResponseFilter{
    private static final String VERSION_HEADER="x-version";
    public VersionResponseFilter() {
        super();
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().putSingle(VERSION_HEADER, requestContext.getHeaderString(VERSION_HEADER));
    }

}
