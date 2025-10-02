package com.programmingmuffin.viper.data;

public class Error {

    public String message;

    public Integer line;

    public Error(String message, Integer line) {
        this.message = message;
        this.line = line;
    }
}
