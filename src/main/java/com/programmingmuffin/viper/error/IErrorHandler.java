package com.programmingmuffin.viper.error;

public interface IErrorHandler {

    void fatal(String error, Integer line);

    void warn(String error, Integer line);

    void info(String message, Integer line);
}
