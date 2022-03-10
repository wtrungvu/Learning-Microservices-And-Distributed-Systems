package com.wtrungvu.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {

}
