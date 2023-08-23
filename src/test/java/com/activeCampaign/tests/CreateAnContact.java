package com.activeCampaign.tests;

import com.activeCampaign.base.BaseAPI;
import com.activeCampaign.utils.ContactUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateAnContact extends BaseAPI {

    @Test
    public static void createAccount() throws IOException {

        //Create a new contact
        JsonNode apiPostResponse = ContactUtils.createAccount();

        JsonNode contactPostResponse = apiPostResponse.get("contact");
        String emailPostResponse = contactPostResponse.get("email").asText();
        String id = contactPostResponse.get("id").asText();

        //Verify id is not null
        Assert.assertNotNull(id);

        // Retrieve contact by its id
        JsonNode apiGetResponse = ContactUtils.getAccountByID(id);

        JsonNode contactGetResponse = apiGetResponse.get("contact");
        String emailGetResponse = contactGetResponse.get("email").asText();

        //Verify the contact exists
        Assert.assertEquals(emailGetResponse, emailPostResponse);

        //Create a list and add this contact to that list
        List<JsonNode> allContacts = new ArrayList<>();
        allContacts.add(apiPostResponse);
        Assert.assertTrue(allContacts.contains(apiPostResponse));

        //Delete Contact by its id
        APIResponse apiDeleteResponse = ContactUtils.deleteAccountByID(id);
        Assert.assertEquals(apiDeleteResponse.status(), 200);
    }
}