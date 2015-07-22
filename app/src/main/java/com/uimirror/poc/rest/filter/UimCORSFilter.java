package com.uimirror.poc.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * Created by jpradhan on 7/20/15.
 */
public class UimCORSFilter implements ContainerResponseFilter {

    protected static final Logger LOG = LoggerFactory.getLogger(UimCORSFilter.class);


    private static final String CONT_ACC_AUTH_API = "Content-Type, Accept, Authorization, access_code, x-version";
    private static final String GET_POST_DELETE = "GET, POST, PUT, OPTIONS, PATCH";
    private static final String ALL = "*";
    private static final String ACCESS_C_A_O = "Access-Control-Allow-Origin";
    private static final String ACCESS_C_A_M = "Access-Control-Allow-Methods";
    private static final String ACCESS_C_A_H = "Access-Control-Allow-Headers";
    private static final String ACCESS_C_M_A = "Access-Control-Max-Age";
    private static final String ACCESS_C_MAX_AGE = "3600";
    private static final String CONTENT_LANG = "Content-Language";
    private static final String ORIGIN = "origin";

    @Override
    public final void filter(ContainerRequestContext requestContext, ContainerResponseContext cResponse) throws IOException {
        String requestingOrigin = requestContext.getHeaderString(ORIGIN);
        LOG.debug("[START]-Putting cross region parameters to response");
        addAccessControlOrigin(cResponse, requestingOrigin);
        addAccessControlMethod(cResponse);
        addAccessControlHeader(cResponse);
        addAccessControlMaxAge(cResponse);
        addContentLanguage(cResponse);
        LOG.debug("[END]-Putting cross region parameters to response");
    }

    /**
     * Adds the supported language to the response header
     *
     * @param cResponse response object
     */
    public void addContentLanguage(ContainerResponseContext cResponse) {
        cResponse.getHeaders().putSingle(CONTENT_LANG, "en_US");
    }

    /**
     * Adds access control max age
     *
     * @param cResponse response object
     */
    public void addAccessControlMaxAge(ContainerResponseContext cResponse) {
        cResponse.getHeaders().putSingle(ACCESS_C_M_A, ACCESS_C_MAX_AGE);
    }


    /**
     * Adds the access control header parameter for authentications.
     *
     * @param cResponse response object
     */
    public void addAccessControlHeader(ContainerResponseContext cResponse) {
        cResponse.getHeaders().putSingle(ACCESS_C_A_H, CONT_ACC_AUTH_API);
    }


    /**
     * Adds the supporting operations.
     *
     * @param cResponse response object
     */
    public void addAccessControlMethod(ContainerResponseContext cResponse) {
        cResponse.getHeaders().putSingle(ACCESS_C_A_M, GET_POST_DELETE);
    }

    /**
     * Adds the origin control to the response.
     *
     * @param cResponse response object
     */
    public void addAccessControlOrigin(ContainerResponseContext cResponse, String requestorOrigin) {
        String accessUrl = ALL;
        //TODO check the requestorOrigin for any check of the caller
//        if (StringUtils.hasText(requestorOrigin)) {
//            accessUrl = WebUtil.getURLDomain(requestorOrigin);
//        }
        LOG.info("Last Request was from {} of domain", requestorOrigin, accessUrl);
        cResponse.getHeaders().putSingle(ACCESS_C_A_O, accessUrl);
    }
}
