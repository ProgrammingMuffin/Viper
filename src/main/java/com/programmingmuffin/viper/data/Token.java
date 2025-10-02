package com.programmingmuffin.viper.data;

public class Token {

    public TokenType type;

    public Object value;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + type.toString() + ": " + value + "}";
    }
}
