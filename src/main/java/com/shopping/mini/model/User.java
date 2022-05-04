package com.shopping.mini.model;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@Getter
@Setter
public class User {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    private Address address;
    private Timestamp addedDate;
    private Timestamp updatedDate;
}

