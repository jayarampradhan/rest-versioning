package com.uimirror.poc.rest.filter;

import com.uimirror.poc.rest.annotation.NotSupported;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by jpradhan on 7/22/15.
 */
@NotSupported
public class VersionRestrictionFilter implements ContainerRequestFilter{

    private static final String VERSION_HEADER="x-version";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String requestedVersion = requestContext.getHeaderString(VERSION_HEADER);
        requestContext.abortWith(Response.status(Response.Status.GONE).entity("No More Supported").build());
    }
}
