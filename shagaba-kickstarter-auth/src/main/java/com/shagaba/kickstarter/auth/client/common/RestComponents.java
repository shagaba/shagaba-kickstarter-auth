package com.shagaba.kickstarter.auth.client.common;

import java.net.URI;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Scope("prototype")
public class RestComponents {

    protected String scheme = "http";
    
    protected String host = "localhost";
    
    protected int port = 8765;
    
    @Resource
    protected HttpHeaders httpHeaders;
    
    @Resource
    protected RestOperations restOperations;
    
    public URI getUri(){
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme(scheme).host(host).port(port).build();
        return uriComponents.toUri();
    }

    /**
     * @return the scheme
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * @param scheme the scheme to set
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the httpHeaders
     */
    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    /**
     * @param httpHeaders the httpHeaders to set
     */
    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    /**
     * @return the restOperations
     */
    public RestOperations getRestOperations() {
        return restOperations;
    }

    /**
     * @param restOperations the restOperations to set
     */
    public void setRestOperations(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

}
