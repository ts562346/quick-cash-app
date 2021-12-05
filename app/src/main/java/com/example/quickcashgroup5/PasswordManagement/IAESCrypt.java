package com.example.quickcashgroup5.PasswordManagement;

public interface IAESCrypt {
    String encrypt(String value) throws Exception;

    String decrypt(String value) throws Exception;
}
