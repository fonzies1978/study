package it.elettro.services.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestEasyServices extends Application {
 
    private Set<Object> singletons = new HashSet<Object>();
 
    public RestEasyServices() {
        singletons.add(new ClientiRestWs());
    }
 
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}