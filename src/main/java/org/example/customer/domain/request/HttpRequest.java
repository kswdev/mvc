package org.example.customer.domain.request;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private final RequestLine requestLine;

    public HttpRequest(BufferedReader br) throws IOException {
        this.requestLine = new RequestLine(br.readLine());
    }

    public QueryStrings getQueryStrings() {
        return this.requestLine.getQueryStrings();
    }

    public boolean isGetRequest() {
        return this.requestLine.isGetRequest();
    }

    public boolean matchPath(String path) {
        return this.requestLine.matchPath(path);
    }
}
