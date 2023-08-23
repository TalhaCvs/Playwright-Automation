package com.activeCampaign.models;

import lombok.Builder;

import static com.activeCampaign.utils.ContactUtils.getRandomEmail;

@Builder
public class Contact {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    public static Contact getContact() {
        return Contact.builder()
                .email(getRandomEmail())
                .firstName("Test")
                .lastName("test1")
                .phone("11111111").build();
    }
}