package com.uimirror.poc.rest.endpoint.v2;

import static com.uimirror.poc.rest.util.ErrorCodes.*;

import com.uimirror.poc.rest.bean.Hello;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * Created by jpradhan on 7/20/15.
 */
@Path("/v2/hello")
@Singleton
@Produces({ APPLICATION_JSON,  APPLICATION_XML})
@Api(value = "/v2/hello", description = "Some Resource Version Test")
public class SomeResource extends com.uimirror.poc.rest.endpoint.v1.SomeResource{
    private static Logger LOG = LoggerFactory.getLogger(SomeResource.class);

    @GET
    @ApiOperation(value = "Gets the Client details from the given client ID.", response = Hello.class)
    @ApiResponses({
            @ApiResponse(code = _200, message = "If Some Test Hello. ")
    })
    public Hello sayHello(){
        Hello hello = new Hello();
        hello.setVersion("V2");
        hello.setMessage("Hello World");
        return hello;
    }
}
