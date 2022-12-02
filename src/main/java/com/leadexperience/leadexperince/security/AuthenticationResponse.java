package com.leadexperience.leadexperince.security;

import com.leadexperience.leadexperince.models.StatusCodeModel;
import com.leadexperience.leadexperince.models.UserDataModel;

public class AuthenticationResponse extends StatusCodeModel {
    private String _token;
    private UserDataModel data;

    public AuthenticationResponse(String status, int code, String message, String jwt, UserDataModel userDataModel) {
        super(status, code, message);
        this._token = jwt;
        this.data = userDataModel;
    }

    public AuthenticationResponse(String status, int code, String jwt, UserDataModel userDataModel) {
        super(status, code);
        this._token = jwt;
        this.data = userDataModel;
    }

    public UserDataModel getData() {
        return data;
    }

    public void setData(UserDataModel data) {
        this.data = data;
    }


    public String get_token() {
        return _token;
    }

    public void set_token(String _token) {
        this._token = _token;
    }
}
