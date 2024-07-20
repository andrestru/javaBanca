package com.java.banca.client.domain.model;

import lombok.Data;

@Data
public class ClientRequest {
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
    private String clientId;
    private String password;
    private String status;
    private Long personId;
}
