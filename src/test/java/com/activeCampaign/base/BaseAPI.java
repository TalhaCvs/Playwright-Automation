package com.activeCampaign.base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseAPI {
    protected static Playwright playwright;
    protected static APIRequest request;
    protected static APIRequestContext requestContext;

    @BeforeTest
    protected void setup() {
        playwright = Playwright.create();
        request = playwright.request();
        requestContext = request.newContext();
    }

    @AfterTest
    protected void tearDown() {
        playwright.close();
    }
}