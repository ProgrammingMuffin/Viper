package com.programmingmuffin.viper.data;

public class TokenFactory {

    public Token getToken(String value, TokenType type) {
        Token token = null;
        switch (type) {
            case IDENT -> token = createIdentifier(value);
            case NUMBER -> token = createNumber(value);
            case LPAREN -> token = createLParen();
            case RPAREN -> token = createRParen();
            case LBRACE -> token = createLBrace();
            case RBRACE -> token = createRBrace();
            case OPERATOR -> token = createOperator(value);
            case KEYWORD -> token = createKeyword(value);
        }
        return token;
    }

    private Token createKeyword(String keyword) {
        return new Token(TokenType.KEYWORD, keyword);
    }

    private Token createOperator(String operator) {
        return new Token(TokenType.OPERATOR, operator);
    }

    private Token createIdentifier(String name) {
        return new Token(TokenType.IDENT, name);
    }

    private Token createNumber(String number) {
        return new Token(TokenType.NUMBER, parseNumber(number));
    }

    private Token createLParen() {
        return new Token(TokenType.LPAREN, null);
    }

    private Token createRParen() {
        return new Token(TokenType.RPAREN, null);
    }

    private Token createLBrace() {
        return new Token(TokenType.LBRACE, null);
    }

    private Token createRBrace() {
        return new Token(TokenType.RBRACE, null);
    }

    private Object parseNumber(String number) {
        Object result;
        try {
            result = Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            try {
                result = Float.parseFloat(number);
            } catch (NumberFormatException ex2) {
                try {
                    result = Double.parseDouble(number);
                } catch (NumberFormatException ex3) {
                    return null;
                }
            }
        } catch (Exception ex) {
            return null;
        }
        return result;
    }
}
