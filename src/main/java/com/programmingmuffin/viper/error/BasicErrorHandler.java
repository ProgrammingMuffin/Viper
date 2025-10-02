package com.programmingmuffin.viper.error;

import com.programmingmuffin.viper.data.Error;
import com.programmingmuffin.viper.data.ErrorManagement;
import com.programmingmuffin.viper.data.Info;

public class BasicErrorHandler implements IErrorHandler {

    private Boolean fatal;

    private ErrorManagement em;

    public BasicErrorHandler(ErrorManagement em) {
        this.fatal = false;
        this.em = em;
    }

    @Override
    public void fatal(String error, Integer line) {
        this.em.fatalErrors.add(new Error(error, line));
        this.fatal = true;
    }

    @Override
    public void warn(String error, Integer line) {
        this.em.warnErrors.add(new Error(error, line));
    }

    @Override
    public void info(String message, Integer line) {
        this.em.infos.add(new Info(message, line));
    }
}
