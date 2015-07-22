package com.uimirror.poc.rest.util;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.jaxb.JAXBBundle;

/**
 * Created by jpradhan on 7/20/15.
 */
public final class GensonFactory {
    private static final Genson GENSON_BUILDER_JAXB_SKP_NULL = new GensonBuilder().withBundle(new JAXBBundle()).setSkipNull(true).create();
    private static final Genson GNSON = new Genson();

    /**
     * Gives the existing instance of the Genson.
     * @return existing instance of type {@link Genson}
     */
    public static Genson getGensonInstance(){
        return GNSON;
    }

    /**
     * Gives the existing instance of the Genson from the Builder setting necessary property such JAXB support and null value skip.
     * @return existing instance of type {@link Genson}
     */
    public static Genson getJaxbNotNullGensonInstance(){
        return GENSON_BUILDER_JAXB_SKP_NULL;
    }

    /**
     * On demand creates the new Builder instance.
     * @return newly created instance.
     */
    public static GensonBuilder getNewBuilderInstance(){
        return new GensonBuilder();
    }
}
