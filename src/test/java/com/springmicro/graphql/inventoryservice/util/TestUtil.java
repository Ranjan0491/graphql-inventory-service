package com.springmicro.graphql.inventoryservice.util;

import com.graphql.spring.boot.test.GraphQLResponse;
import org.springframework.http.ResponseEntity;

public class TestUtil {

    public static void printResponse(ResponseEntity<String> response) {
        System.out.println("Status Code:\t" + response.getStatusCodeValue());
        System.out.println("Body:\t" + response.getBody());
    }

    public static void printResponse(GraphQLResponse graphQLResponse) {
        printResponse(graphQLResponse.getRawResponse());
    }
}
