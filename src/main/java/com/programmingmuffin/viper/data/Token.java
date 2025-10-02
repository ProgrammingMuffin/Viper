package com.programmingmuffin.viper.data;

public class Token {

    public TokenType type;

    public Object value;

    public Integer start;

    public Integer end;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType type, Object value, Integer start, Integer end) {
        this.type = type;
        this.value = value;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + type.toString() + ": " + value + " (" + this.start + ", " + this.end + ")}";
    }
}
