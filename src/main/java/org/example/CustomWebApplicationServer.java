package org.example;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.example.calculator.normal.Calculator;
import org.example.calculator.normal.interface_version.number.PositiveNumber;
import org.example.customer.domain.request.HttpRequest;
import org.example.customer.domain.request.QueryStrings;
import org.example.customer.domain.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {

    private static final Logger log = LoggerFactory.getLogger(CustomWebApplicationServer.class);

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

                try (InputStream is  = clientSocket.getInputStream();
                     OutputStream os = clientSocket.getOutputStream()) {

                    BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(os);

                    HttpRequest httpRequest = new HttpRequest(br);

                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                        QueryStrings queryStrings = httpRequest.getQueryStrings();

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
                        byte[] body = String.valueOf(result).getBytes();

                        HttpResponse response = new HttpResponse(dos);

                        response.response200Header("application/json", body.length);
                        response.responseBody(body);
                    }
                }
            }
        }
    }
}