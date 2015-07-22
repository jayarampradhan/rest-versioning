package com.uimirror.poc.rest.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jpradhan on 7/20/15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Hello {
    @XmlElement(name="v")
    private String version;
    @XmlElement(name="msg")
    private String message;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
