package com.Udemy.Udemy_backend.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class AuthResponse {
    private String jwt;
    private String message;
    private Boolean status;

    public AuthResponse(String jwt, String message, boolean status) {
        this.jwt = jwt;
        this.message = message;
        this.status = status;
    }

    // ✅ Default Constructor (needed for Spring)
    public AuthResponse() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

