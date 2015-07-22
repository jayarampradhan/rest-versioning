package com.uimirror.poc.rest.conf;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jpradhan on 7/20/15.
 */
@Configuration
@Import(WebAppInitializer.Bootstrap.class)
public class WebAppInitializer extends SpringBootServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebAppInitializer.class);
    }

    @Bean
    public ServletContainer jersseyServlet() {
        return new ServletContainer();
    }

//    @Bean
//    public ServletRegistrationBean jersseyServletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(jersseyServlet());
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("javax.ws.rs.Application", JerssyApplicationInitializer.class.getName());
//        registration.setInitParameters(params);
//        registration.addUrlMappings("/api/*");
//        registration.setLoadOnStartup(1);
//        return registration;
//    }

    @Bean
    public ServletRegistrationBean jersseyServletRegistrationWithVersionFilter() {
        ServletRegistrationBean registration = new ServletRegistrationBean(jersseyServlet());
        Map<String, String> params = new HashMap<String, String>();
        params.put("javax.ws.rs.Application", JerssyAppVersionFilterInitializer.class.getName());
        registration.setInitParameters(params);
        registration.addUrlMappings("/apifilter/*");
        registration.setLoadOnStartup(2);
        return registration;
    }

    @Bean
    @Autowired
    public ServletRegistrationBean swaggerBootStrapBean(Bootstrap bootStrap) {
        ServletRegistrationBean registration = new ServletRegistrationBean(bootStrap);
        registration.setLoadOnStartup(2);
        return registration;
    }

    /**
     * Configuration for the swagger UI
     *
     * @author Jayaram
     */
    @Configuration
    public static class Bootstrap extends HttpServlet {
        private static final long serialVersionUID = -488257706121168000L;

        @Value("${tomcat.contextPath:}/api")
        private String apiPath;
        @Value("${app.package:com.uimirror.poc.rest}.endpoint")
        private String baseEndpointPath;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
            BeanConfig beanConfig = new BeanConfig();
            beanConfig.setVersion("1.0");
            beanConfig.setBasePath(apiPath);
            beanConfig.setResourcePackage(baseEndpointPath);
            beanConfig.setTitle("Account Manager- By Uim.");
            beanConfig.setDescription("Manage user and client account details.\n"
                    + "For Consuming application from application use directly without any /api path");
            beanConfig.setContact("jayaram.pradhan@uimirror.com");
            beanConfig.setLicense("Uimirror ©");
            //ClassReaders.setReader(new JerseyApiReader());
            //ClassReaders.setReader(new BasicJaxrsReader());
            beanConfig.setScan(true);
        }
    }
}

