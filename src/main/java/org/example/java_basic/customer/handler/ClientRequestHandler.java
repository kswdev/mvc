package org.example.java_basic.customer.handler;

import org.example.java_basic.calculator.normal.Calculator;
import org.example.java_basic.calculator.normal.interface_version.number.PositiveNumber;
import org.example.java_basic.customer.domain.request.HttpRequest;
import org.example.java_basic.customer.domain.request.QueryStrings;
import org.example.java_basic.customer.domain.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(ClientRequestHandler.class);
    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        log.info("[ClientRequestHandler] new client {} started", Thread.currentThread().getName());

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
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
