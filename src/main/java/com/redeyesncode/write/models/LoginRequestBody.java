package com.redeyesncode.write.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class LoginRequestBody {

    private String userEmail;

    private String password;




}
