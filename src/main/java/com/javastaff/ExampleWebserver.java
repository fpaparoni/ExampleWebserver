package com.javastaff;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ExampleWebserver {
	public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
        	StringBuilder responseBuilder=new StringBuilder(); 
        	responseBuilder.append("<html><head><title>ExampleWebserver</title></head><body>");
        	responseBuilder.append("<h3>ExampleWebserver</h3>")
        					.append("Tu hai richiesto l'URL ")
        					.append(t.getRequestURI())
        					.append(" ma io non so ancora come poterlo gestire :(");
            t.sendResponseHeaders(200, responseBuilder.toString().length());
            OutputStream os = t.getResponseBody();
            os.write(responseBuilder.toString().getBytes());
            os.close();
        }
    }
}