package com.example.login.entity;

public class AESUser {
    private String AES;

    public AESUser() {
    }

    public AESUser(String AES) {
        this.AES = AES;
    }

    @Override
    public String toString() {
        return "AESUser{" +
                "AES='" + AES + '\'' +
                '}';
    }

    public String getAES() {
        return AES;
    }

    public void setAES(String AES) {
        this.AES = AES;
    }
}
