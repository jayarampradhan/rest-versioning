package com.uimirror.poc.rest.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by jpradhan on 7/20/15.
 */
public class ApplicationException extends WebApplicationException{

    private static final long serialVersionUID = 7633918125632783005L;

    /**
     * <p>Initialize the exception response to be part of the response</p>
     * @param res which will be mapped
     */
    protected ApplicationException(Response res) {
        super(res);
    }

}
