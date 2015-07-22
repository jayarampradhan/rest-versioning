package com.uimirror.poc.rest.endpoint.v1;

import com.uimirror.poc.rest.annotation.NotSupported;
import com.uimirror.poc.rest.bean.Hello;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static com.uimirror.poc.rest.util.ErrorCodes._200;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * Created by jpradhan on 7/20/15.
 */
@Path("/v1/hello")
@Singleton
@Produces({ APPLICATION_JSON,  APPLICATION_XML})
@Consumes({  APPLICATION_XML, APPLICATION_JSON})
@Api(value = "/v1/hello", description = "Some Resource Version Test")
public class SomeResource {
    private static Logger LOG = LoggerFactory.getLogger(SomeResource.class);

    @NotSupported
    @GET
    @ApiOperation(value = "Gets the Client details from the given client ID.", response = Hello.class)
    @ApiResponses({
            @ApiResponse(code = _200, message = "If Some Test Hello. ")
    })
    public Hello sayHello(){
        Hello hello = new Hello();
        hello.setVersion("V1");
        hello.setMessage("Hello World");
        return hello;
    }

    @GET
    @Path("old")
    @ApiOperation(value = "Gets the Client details from the given client ID.", response = Hello.class)
    @ApiResponses({
            @ApiResponse(code = _200, message = "If Some Test Hello. ")
    })
    public Hello sayHelloToOldGuy(){
        Hello hello = new Hello();
        hello.setVersion("V1");
        hello.setMessage("Hello Old School Boy!!!");
        return hello;
    }
}
