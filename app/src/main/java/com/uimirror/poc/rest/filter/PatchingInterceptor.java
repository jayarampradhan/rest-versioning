package com.uimirror.poc.rest.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.uimirror.poc.rest.annotation.PATCH;
import com.uimirror.poc.rest.util.WebErrorMessage;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.server.model.AnnotatedMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import com.uimirror.poc.rest.exceptions.IllegalArgumentException;

/**
 * Created by jpradhan on 7/20/15.
 */
@Provider
@PATCH
@Singleton
public class PatchingInterceptor implements ReaderInterceptor {
    private static Logger LOG = LoggerFactory.getLogger(PatchingInterceptor.class);
    /**
     * Supported PATCH data format.
     */
    public static final String PATCH_MEDIA_TYPE = "application/json-patch+json";

    private final UriInfo uriInfo;
    private final MessageBodyWorkers workers;
    private final ResourceInfo resourceInfo;

    /**
     * {@code PatchingInterceptor} injection constructor.
     *
     * @param uriInfo {@code javax.ws.rs.core.UriInfo} proxy instance.
     * @param workers {@link org.glassfish.jersey.message.MessageBodyWorkers} message body workers.
     * @param resourceInfo {@code ResourceInfo} resource info for the current end point
     */
    public PatchingInterceptor(UriInfo uriInfo, @Context MessageBodyWorkers workers, @Context ResourceInfo resourceInfo) {
        this.uriInfo = uriInfo;
        this.workers = workers;
        this.resourceInfo = resourceInfo;
    }

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext readerInterceptorContext){
        LOG.info("[START]- Json Patching started for the current state.");
        //Find the current annotation details
        PATCH am = findAnnotation(resourceInfo.getResourceMethod());
        String toBeMatchMethodName = am.methodName();
        String paramKey = am.key();
        ParamInTypes lookUpAt = am.lookAt();
        if(!StringUtils.hasText(toBeMatchMethodName)){
            LOG.error("[ERROR]- Can't Perform Patch operation, due to no method name specified to get current state.");
            throw new IllegalArgumentException(buildErrorMessage("Can't Perform Patch operation."));
        }
        String requiredArgument = getRequiredArgument(uriInfo, paramKey, lookUpAt);
        // Get the resource we are being called on,
        // and find the GET method
        Object resource = uriInfo.getMatchedResources().get(0);
        Method found = getMatchedMethod(resource, toBeMatchMethodName);

        // Invoke the get method to get the state we are trying to patch
        Object bean = getCurrentState(requiredArgument, resource, found);

        ByteArrayOutputStream baos = getCurrentStateInByteArray(bean);
        // Use the Jackson 2.x classes to convert both the incoming patch
        // and the current state of the object into a JsonNode / JsonPatch

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode serverState = mapper.readValue(baos.toByteArray(), JsonNode.class);
            JsonNode patchAsNode = mapper.readValue(readerInterceptorContext.getInputStream(), JsonNode.class);
            JsonPatch patch = JsonPatch.fromJson(patchAsNode);
            // Apply the patch
            JsonNode result = patch.apply(serverState);

            // Stream the result & modify the stream on the readerInterceptor
            ByteArrayOutputStream resultAsByteArray = new ByteArrayOutputStream();
            mapper.writeValue(resultAsByteArray, result);
            readerInterceptorContext.setInputStream(new ByteArrayInputStream(resultAsByteArray.toByteArray()));
            LOG.info("[END]- Json Patching started for the current state.");
            // Pass control back to the Jersey code
            return readerInterceptorContext.proceed();
        } catch (JsonPatchException | IOException ex) {
            LOG.error("[ERROR]- Error Applying PATCH with the current state, verify your input. {}",ex);
            throw new IllegalArgumentException(buildErrorMessage("Error applying patch."));
        }

    }

    private Object getCurrentState(String requiredArgument, Object resource, Method found) {
        Object bean;
        try {
            if(StringUtils.hasText(requiredArgument))
                bean = found.invoke(resource, requiredArgument);
            else
                bean = found.invoke(resource);
        }catch (Exception e) {
            LOG.error("[ERROR]- Not able to detrmine current state.");
            throw new IllegalArgumentException(buildErrorMessage("Error Reteriving current state."));
        }
        return bean;
    }

    /**
     * Gets the current annotation details present for this resource.
     * @param m current resource Method
     * @return found PATCH annotation
     */
    private PATCH findAnnotation(Method m){
        AnnotatedMethod as = new AnnotatedMethod(m);
        PATCH pr = null;
        if(as.isAnnotationPresent(PATCH.class)) {
            pr = as.getAnnotation(PATCH.class);
        }else{
            LOG.error("[ERROR]- Can't Perform Patch operation, due to invalid configuration.");
            throw new IllegalArgumentException(buildErrorMessage("Can't Perform Patch operation, due to invalid configuration."));
        }
        return pr;
    }

    private String getRequiredArgument(UriInfo uriInfo, String key, ParamInTypes lookUpAt){
        if(key == null && lookUpAt == null){
            LOG.error("[ERROR]- Can't Perform Patch operation, due to invalid configuration.");
            throw new IllegalArgumentException(buildErrorMessage("Can't Perform Patch operation, due to invalid configuration."));
        }
        String val = null;
        switch (lookUpAt) {
            case PATH:
                val = uriInfo.getPathParameters().get(key).get(0);
                break;
            case QUERY:
                val = uriInfo.getQueryParameters().get(key).get(0);
                break;
            default:
                break;
        }
        return val;
    }

    private Method getMatchedMethod(Object resource, String toBeMatchMethodName){
        Method found = null;
        for (Method next : resource.getClass().getMethods()) {
            if (next.getAnnotation(GET.class) != null && next.getName().equalsIgnoreCase(toBeMatchMethodName)) {
                found = next;
                break;
            }
        }
        if (found == null) {
            LOG.error("[ERROR]- No Matching Current state retriver.");
            throw new IllegalArgumentException(buildErrorMessage("No Matching Current state retriver."));
        }
        return found;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private ByteArrayOutputStream getCurrentStateInByteArray(Object bean){
        // Convert this object to a an array of bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MessageBodyWriter bodyWriter =
                workers.getMessageBodyWriter(bean.getClass(), bean.getClass(),
                        new Annotation[0], MediaType.APPLICATION_JSON_TYPE);

        try {
            bodyWriter.writeTo(bean, bean.getClass(), bean.getClass(),
                    new Annotation[0], MediaType.APPLICATION_JSON_TYPE,
                    new MultivaluedHashMap<String, Object>(), baos);
        } catch (WebApplicationException | IOException e) {
            LOG.error("[ERROR]- Can't able to convert to stream.");
            throw new IllegalArgumentException(buildErrorMessage("Current state is unstable."));
        }
        return baos;
    }

    public enum ParamInTypes{
        PATH,QUERY;
    }

    /**
     * Creates the error message that will be shown to the end user.
     * It expects first argument as error message and second argument should be suggestion, violating the semantic, will return a default message.
     * @param msg
     * @return
     */
    private WebErrorMessage buildErrorMessage(String ... msg){
        WebErrorMessage error = null;
        if(msg.length >= 2)
            error = new WebErrorMessage.WebErrorBuilder(msg[0]).addSuggestions(msg[1]).build();
        else if(msg.length == 1)
            error = new WebErrorMessage.WebErrorBuilder(msg[0]).build();
        else
            error = new WebErrorMessage.WebErrorBuilder("Not Able to apply the Patch operation.").build();
        return error;
    }

}
