package org.example.customer.domain.request;


import java.util.Objects;

/**
 * GET /calculate?operand1=11&operator=*&operand2=11 HTTP/1.1
 */

public class RequestLine {
    private final String method;
    private final String urlPath;
    private QueryStrings queryStrings;

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];

        String[] urlPathTokens = tokens[1].split("\\?");
        this.urlPath = urlPathTokens[0];

        if (urlPathTokens.length >= 2)
            queryStrings = new QueryStrings(urlPathTokens[1]);
    }

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(queryString);
    }

    public String getMethod() {
        return method;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public QueryStrings getQueryStrings() {
        return queryStrings;
    }

    @Override
    public String toString() {
        return "RequestLine{" +
                "method='" + method + '\'' +
                ", urlPath='" + urlPath + '\'' +
                ", queryString='" + queryStrings + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStrings, that.queryStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryStrings);
    }

    public boolean isGetRequest() {
        return this.getMethod().equals("GET");
    }

    public boolean matchPath(String path) {
        return this.getUrlPath().equals(path);
    }
}
