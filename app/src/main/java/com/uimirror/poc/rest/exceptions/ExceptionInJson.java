package com.uimirror.poc.rest.exceptions;

import com.uimirror.poc.rest.util.ResponseBuilder;
import com.uimirror.poc.rest.util.WebErrorMessage;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by jpradhan on 7/20/15.
 */
public class ExceptionInJson extends ApplicationException{

    private static final long serialVersionUID = 7633918125632783005L;
    public static final String ERROR = "error";

    /**
     * <p>Initialize the exception response to be part of the response</p>
     *
     * @param status cause of the exception
     * @param msg describing the root cause
     */
    public ExceptionInJson(Response.Status status, Object msg) {
        super(responseBuilder(status, msg));
    }

    /**
     * <p>Builds invalid Response message</p>
     * @return {@link Response} object
     */
    private static Response responseBuilder(Response.Status status, Object msg) {
        Response rs = null;
        if(msg instanceof String){
            Map<String, Object> m = new WeakHashMap<String, Object>();
            m.put(ERROR, msg);
            rs = ResponseBuilder.buildObject(status, msg);
        }else if(msg instanceof WebErrorMessage){
            rs = ResponseBuilder.buildJaxbObject(status, msg);
        }else{
            rs = ResponseBuilder.buildObject(status, msg);
        }
        return rs;
    }

}
