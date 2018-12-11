package com.webservices;

import javax.jws.WebService;

@WebService(endpointInterface="com.webservices.TestCxf")
public class TestCxfImpl implements TestCxf{
    public String print() {
        System.out.println("Hello from WebService!");
        return "Hello from service.";
    }
}
