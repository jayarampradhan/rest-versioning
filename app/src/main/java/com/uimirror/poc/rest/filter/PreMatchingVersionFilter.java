package com.uimirror.poc.rest.filter;

import com.uimirror.poc.rest.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import com.uimirror.poc.rest.exceptions.IllegalArgumentException;

/**
 * <p>This will be invoked before any request URI match process starts. It has influence to change
 * to different URI or even Http methods</p>
 * <p>i.e if a request is coming as /test it can change to /v1/test or so depends on the header or some argument.</p>
 *
 * Created by jpradhan on 7/20/15.
 */
@PreMatching
public class PreMatchingVersionFilter implements ContainerRequestFilter{

    protected static Logger LOG = LoggerFactory.getLogger(PreMatchingVersionFilter.class);
    private static final String VERSION_HEADER="x-version";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        //First resolve the path to be added as prefix based on the header
        PathVersion pv = resolveAdditionalPath(requestContext);
        try{
            //requestContext.getUriInfo().
            //Form the URL again based on the base_uri+path_to_be_added+resource_path
            URI uri = new URI(requestContext.getUriInfo().getBaseUri()+pv.path+requestContext.getUriInfo().getPath());
            if(!StringUtils.hasText(requestContext.getHeaderString(VERSION_HEADER)) || !pv.version.equals(requestContext.getHeaderString(VERSION_HEADER)))
                requestContext.getHeaders().putSingle(VERSION_HEADER,pv.version);
            requestContext.setRequestUri(uri);
        }catch (URISyntaxException e){
            LOG.error("Some thing went wrong {}",e);
            throw new IllegalArgumentException("Something URI matching went wrong");
        }

    }

    private PathVersion resolveAdditionalPath(ContainerRequestContext requestContext){
        String version = requestContext.getHeaderString(VERSION_HEADER);
        PathVersion pv = new PathVersion();
        if(StringUtils.hasText(version) && "1".equals(version)){
            pv.path="v1/";
            pv.version="v1";
            return pv;
        }
        pv.path="v2/";
        pv.version="v2";
        return pv;
    }

    public static final class PathVersion{
        private String version;
        private String path;
    }
}
