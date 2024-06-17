package org.example;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.example.calculator.normal.Calculator;
import org.example.calculator.normal.interface_version.number.PositiveNumber;
import org.example.customer.domain.request.HttpRequest;
import org.example.customer.domain.request.QueryStrings;
import org.example.customer.domain.response.HttpResponse;
import org.example.customer.handler.ClientRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {

    private static final Logger log = LoggerFactory.getLogger(CustomWebApplicationServer.class);
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final int port;

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("[CustomWebApplicationServer] started port : {}", port);

            Socket clientSocket;
            log.info("[CustomWebApplicationServer] waiting for client");

            while ((clientSocket = serverSocket.accept()) != null) {
                log.info("[CustomWebApplicationServer] client connected");

                /**
                 *  Step1 - 사용자 요청을 Main Thread로 처
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}