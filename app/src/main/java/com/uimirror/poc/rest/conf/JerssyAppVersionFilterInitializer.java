package com.uimirror.poc.rest.conf;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import com.uimirror.poc.rest.filter.*;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.UriConnegFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jpradhan on 7/20/15.
 */
public class JerssyAppVersionFilterInitializer extends ResourceConfig {

    protected static final Logger LOG = LoggerFactory.getLogger(JerssyAppVersionFilterInitializer.class);


    public JerssyAppVersionFilterInitializer(){
        // Register resources and providers using package-scanning.
        packages(true, "com.uimirror.poc.rest.endpoint");
        //Gson Converter
        //register(JacksonFeatures.class);
        register(GensonJsonConverter.class);
        //Swagger Configuration
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
        register(UriConnegFilter.class);

        //Custom filters
        register(PreMatchingVersionFilter.class);
        register(VersionRestrictionFilter.class);
        register(PatchingInterceptor.class);
        register(OptionsAcceptHeader.class);
        register(UimCORSFilter.class);
        register(VersionResponseFilter.class);

        property(ServerProperties.LANGUAGE_MAPPINGS, "english : en");
        property(ServerProperties.APPLICATION_NAME, "restapiversion");
        // Register an instance of LoggingFilter.
        register(new LoggingFilter());
        // Enable Tracing support.
        property(ServerProperties.TRACING, "ALL");
//        property(ServerProperties.TRACING, "ALL");
    }

}

