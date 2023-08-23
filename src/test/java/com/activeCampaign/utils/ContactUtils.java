package com.activeCampaign.utils;

import com.activeCampaign.base.BaseAPI;
import com.activeCampaign.models.Contact;
import com.activeCampaign.models.RequestBody;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import java.io.IOException;
import static com.activeCampaign.models.Contact.getContact;

public abstract class ContactUtils extends BaseAPI {

    public static JsonNode createAccount() throws IOException {
        RequestBody request = requestBody(getContact());

        APIResponse apiResponse = requestContext.post(ConfigReader.readProperty("url"),
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Api-Token", ConfigReader.readProperty("Api-Token"))
                        .setData(request));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());

        return jsonResponse;
    }

    public static JsonNode getAccountByID(String id) throws IOException {
        APIResponse responseGet = requestContext.get(ConfigReader.readProperty("url") + id,
                RequestOptions.create()
                        .setHeader("Api-Token", ConfigReader.readProperty("Api-Token")));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(responseGet.body());

        return jsonResponse;
    }

    public static APIResponse deleteAccountByID(String id) throws IOException {
        APIResponse responseDelete = requestContext.delete(ConfigReader.readProperty("url") + id,
                RequestOptions.create()
                        .setHeader("Api-Token", ConfigReader.readProperty("Api-Token"))
        );
        return responseDelete;
    }

    public static RequestBody requestBody(Contact contact) {
        return RequestBody.builder()
                .contact(contact)
                .build();
    }

    public static String getRandomEmail() {
        String email = "testAutomationPW" + System.currentTimeMillis() + "@gmail.com";
        return email;
    }
}