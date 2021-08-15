package com.example.config;

import com.example.endpoint.MesManEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.xml.ws.Endpoint;


@Configuration
public class SoapWebServiceConfig {

    @Bean
    public ServletRegistrationBean<CXFServlet> dispatcherservlet() {
        return new ServletRegistrationBean<CXFServlet>(new CXFServlet(), "/getMessages/*");
    }

    @Bean
    @Primary
    public DispatcherServletPath dispatcherservletpathprovider() {
        return () -> "";
    }

    @Bean
    public Endpoint endpoint(Bus bus, MesManEndpoint mesManEndpoint) {
        EndpointImpl endpoint = new EndpointImpl(bus, mesManEndpoint);
        endpoint.publish("/MessageBeanService");
        return endpoint;
    }
}
