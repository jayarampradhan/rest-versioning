package com.uimirror.poc.rest.exceptions;

import com.uimirror.poc.rest.util.WebErrorMessage;

import javax.ws.rs.core.Response;

/**
 * Created by jpradhan on 7/20/15.
 */
public class IllegalArgumentException extends ExceptionInJson{

    private static final long serialVersionUID = 2893053632116181333L;

    private static final String MSG = "provided details are insufficient";
    private static final WebErrorMessage WB_ERR_MSG = new WebErrorMessage.WebErrorBuilder(MSG).build();
    private static final Response.Status CODE = Response.Status.NOT_ACCEPTABLE;

    /**
     * Constructs the default exception for insufficient arguments.
     */
    public IllegalArgumentException() {
        super(CODE, WB_ERR_MSG);
    }

    /**
     * <p>Construct the response with user provided message</p>
     * @param message stating the reason of the exception, it might be String or an instance of
     * {@link WebErrorMessage} or any object which can be transformed to json string.
     */
    public IllegalArgumentException(Object message) {
        super(CODE, message);
    }
}
