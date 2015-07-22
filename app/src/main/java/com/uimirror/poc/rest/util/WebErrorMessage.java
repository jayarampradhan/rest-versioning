package com.uimirror.poc.rest.util;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.springframework.util.CollectionUtils;

import static com.uimirror.poc.rest.util.RestCommonConstants.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jpradhan on 7/20/15.
 */
@XmlRootElement(name=ERROR_CLASS)
public class WebErrorMessage implements Serializable{

    private static final long serialVersionUID = -4541419583694630703L;

    @XmlElement(name=ERROR_MESSAGE)
    private String message;

    @XmlElement(name=SUGGESTIONS)
    private List<String> suggestions;

    @XmlList
    @XmlElement(name=ERROR_FIELDS)
    private List<String> fields;

    public static class WebErrorBuilder implements Builder<WebErrorMessage>{

        private String message;
        private List<String> suggestions = new LinkedList<String>();
        private List<String> fields = new LinkedList<String>();

        public WebErrorBuilder(String message){
            this.message = message;
        }

        public WebErrorBuilder addSuggestions(String suggestions){
            this.suggestions.add(suggestions);
            return this;
        }

        public WebErrorBuilder addErrorFields(String field){
            this.fields.add(field);
            return this;
        }

        /* (non-Javadoc)
         * @see com.uimirror.core.Builder#build()
         */
        @Override
        public WebErrorMessage build() {
            return new WebErrorMessage(this);
        }

    }

    private WebErrorMessage(WebErrorBuilder builder){
        this.message = builder.message;
        this.suggestions = CollectionUtils.isEmpty(builder.suggestions) ? null : builder.suggestions;
        this.fields = CollectionUtils.isEmpty(builder.fields) ? null : builder.fields;
    }

    public WebErrorMessage() {
        //NOP, highly discourageable to have a constructor, as for Genson its required.
    }

    @XmlTransient
    public String getMessage() {
        return message;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public List<String> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        StandardToStringStyle style = new StandardToStringStyle();
        style.setFieldSeparator(", ");
        style.setUseClassName(false);
        style.setUseIdentityHashCode(false);
        return new ReflectionToStringBuilder(this, style).toString();
    }

}
