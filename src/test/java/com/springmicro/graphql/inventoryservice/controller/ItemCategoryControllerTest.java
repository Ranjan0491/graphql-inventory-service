package com.springmicro.graphql.inventoryservice.controller;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.springmicro.graphql.inventoryservice.InventoryServiceApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.String.*;
import static com.springmicro.graphql.inventoryservice.constant.TestConstants.*;
import static com.springmicro.graphql.inventoryservice.util.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = InventoryServiceApplication.class)
class ItemCategoryControllerTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @DisplayName("Test for fetching all item categories")
    @ParameterizedTest
    @ValueSource(strings = {"getAllItemCategories_id_name", "getAllItemCategories_id", "getAllItemCategories_name"})
    void givenQueryTestName_whenGetAllItemCategories_thenReturnListOfItemCategories(String testName) throws Exception {
        // given
        System.out.println("For testName: " + testName);

        // when
        var response = graphQLTestTemplate.postForResource(format(QUERY_REQUEST_FILE_PATH, testName));

        // then
        printResponse(response);
        assertAll("Should return multiple Categories and the first category should have id and name",
                () -> assertTrue(response.isOk()),
                () -> assertNotNull(response.get("$.data.getAllItemCategories[0]"))
        );
    }

    @Test
    void getItemCategoryById() {
    }

    @Test
    void saveItemCategory() {
    }

    @Test
    void updateItemCategory() {
    }

    @Test
    void itemCategoryService() {
    }

}