package com.uimirror.poc.rest;

/**
 * Created by jpradhan on 7/20/15.
 */

import com.uimirror.poc.rest.conf.AppConfig;
import com.uimirror.poc.rest.conf.BeanInitalizer;
import com.uimirror.poc.rest.conf.WebAppInitializer;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;


/**
 * <p>Main Class Where authentication end point will be deployed</p>
 * <p>This loads all the bean and initialize the spring context to start</p>
 * @author Jay
 */
@Configuration
@ImportResource("classpath*:applicationContext.xml")
@ComponentScan(basePackages= {"com.uimirror.poc.rest"})
@Import({
        AppConfig.class, WebAppInitializer.class, BeanInitalizer.class
})
public class StartApp {

    private static Logger LOG = LoggerFactory.getLogger(StartApp.class);

    @Value("${port:8181}")
    private int tomcatPort;

    @Value("${nio.port:8443}")
    private int tomcatNioPort;

    @Value("${tomcat.sessionTimeout:30}")
    private int tomcatSessionTimeout;

    @Value("${tomcat.contextPath:/restversion}")
    private String contextPath;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.setPort(tomcatPort);
        tomcat.setSessionTimeout(tomcatSessionTimeout);
        tomcat.setContextPath(contextPath);
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        return tomcat;
    }

    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            File keystore = getKeyStoreFile();
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setPort(tomcatNioPort);
            protocol.setSSLEnabled(true);
            protocol.setKeystoreFile(keystore.getAbsolutePath());
            protocol.setKeystorePass("changeit");
            protocol.setTruststoreFile(keystore.getAbsolutePath());
            protocol.setTruststorePass("changeit");
            protocol.setKeyAlias("apitester");
            return connector;
        }
        catch (IOException ex) {
            throw new IllegalStateException("cant access keystore: [" + "keystore"
                    + "] or truststore: [" + "keystore" + "]", ex);
        }
    }

    private File getKeyStoreFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("keystore");
        try {
            return resource.getFile();
        }
        catch (Exception ex) {
            File temp = File.createTempFile("keystore", ".tmp");
            FileCopyUtils.copy(resource.getInputStream(), new FileOutputStream(temp));
            return temp;
        }
    }


    /**
     * Application entry point.
     * @param args command line args
     */
    public static void main(final String[] args) {
        LOG.info("CmdLine Args {}", Arrays.asList(args));
        try {
            SpringApplication sa = new SpringApplication(new Object[] {
                    StartApp.class
            });
            sa.run(args);

        }
        catch (Exception e) {
            LOG.error("Unexpected error", e);
        }
    }

}