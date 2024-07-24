package org.example.java_basic.customer.domain.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * operand1=11&operator=*&operand2=11
 */
public class QueryStrings {
    List<QueryString> queryStrings = new ArrayList<>();

    public QueryStrings(String queryStringLine) {
        String[] queryStringTokens = queryStringLine.split("&");
        Arrays.stream(queryStringTokens)
                .forEach(queryString -> {
                    String[] queryStringToken = queryString.split("=");

                    if (queryStringToken.length != 2)
                        throw new IllegalArgumentException("queryString 형태의 문자가 아닙니다.");

                    queryStrings.add(new QueryString(queryStringToken[0], queryStringToken[1]));
                });
    }

    public String getValue(String operand) {
        return queryStrings.stream()
                .filter(queryString -> queryString.exist(operand))
                .map(QueryString::getValue)
                .findFirst()
                .orElseThrow();
    }
}
