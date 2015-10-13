package com.plaid.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.plaid.client.http.ApacheHttpClientHttpDelegate;
import com.plaid.client.http.HttpDelegate;
import com.plaid.client.response.CategoriesResponse;
import com.plaid.client.response.Institution;
import com.plaid.client.response.InstitutionsResponse;

public class PlaidPublicClientTest {

    private PlaidPublicClient plaidPublicClient;

    @Before
    public void setup() {

        HttpDelegate httpDelegate = ApacheHttpClientHttpDelegate.createDefault("https://tartan.plaid.com", true); // disable content compression
        plaidPublicClient = new DefaultPlaidPublicClient.Builder()
                .withHttpDelegate(httpDelegate)
                .build();
    }

    @Test
    public void testGetAllCategories() {
        CategoriesResponse categoriesResponse = plaidPublicClient.getAllCategories();
        assertTrue(categoriesResponse.getCategories().size() > 0);
    }

    @Test
    public void testGetAllInstitutions() {
        InstitutionsResponse instResponse = plaidPublicClient.getAllInstitutions();
        assertNotNull(instResponse);
        Institution[] institutions = instResponse.getInstitutions();
        Map<String, Institution> map = new HashMap<>();
        for (Institution institution : institutions) {
            map.put(institution.getName(), institution);
        }

        assertNotNull(map.get("Bank of America"));
    }

}
