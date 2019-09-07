package com.example.bmicalculator;

import androidx.annotation.Nullable;

public class CustomException extends Exception{
    @Nullable
    @Override
    public String getMessage(){
        return super.getMessage();
    }
    public String getMessage(String message) {
        return message;
    }
}
