package com.programmingmuffin.viper.data;

import java.util.ArrayList;
import java.util.List;

public class ErrorManagement {

    public List<Error> fatalErrors;

    public List<Error> warnErrors;

    public List<Info> infos;

    public ErrorManagement() {
        this.fatalErrors = new ArrayList<>();
        this.warnErrors = new ArrayList<>();
        this.infos = new ArrayList<>();
    }
}
