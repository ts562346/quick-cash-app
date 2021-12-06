package com.example.quickcashgroup5.passwordmanagement;

public interface IAESCrypt {
    String encrypt(String value) throws Exception;

    String decrypt(String value) throws Exception;
}
