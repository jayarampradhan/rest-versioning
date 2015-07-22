package com.uimirror.poc.rest.util;

import javax.ws.rs.core.Response;

/**
 * Created by jpradhan on 7/20/15.
 */
public class ResponseBuilder {
    /**
     * Builds the response of any object.
     * @param status which needs to be sent
     * @param msg message that needs to be sent
     * @return created response object
     */
    public static Response buildObject(Response.Status status, Object msg){
        return Response.status(status).entity(GensonFactory.getGensonInstance().serialize(msg)).build();
    }

    /**
     * Builds the response of any object annotated with the Jaxb annotations.
     * This will remove any null values at run time.
     * @param status which needs to be sent
     * @param msg message that needs to be sent
     * @return created response object
     */
    public static Response buildJaxbObject(Response.Status status, Object msg){
        return Response.status(status).entity(GensonFactory.getJaxbNotNullGensonInstance().serialize(msg)).build();
    }

    /**
     * Builds the success response that will be sent to the client
     * @param msg which needs to be serialized
     * @see #buildObject(javax.ws.rs.core.Response.Status, Object) for the details
     * @return created response object
     */
    public static Response buildSuccessResponse(Object msg){
        return buildObject(Response.Status.OK, msg);
    }

    /**
     * Builds the success response that will be sent to the client
     * @param msg which needs to be serialized
     * @see #buildJaxbObject(javax.ws.rs.core.Response.Status, Object) for the implemntation details
     * @return created response object
     */
    public static Response buildSuccessResponseWithJaxb(Object msg){
        return buildJaxbObject(Response.Status.OK, msg);
    }
}
