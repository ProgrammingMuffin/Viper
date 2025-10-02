package com.programmingmuffin.viper.lexer;

import com.programmingmuffin.viper.data.Token;
import com.programmingmuffin.viper.data.TokenFactory;
import com.programmingmuffin.viper.data.TokenType;
import com.programmingmuffin.viper.error.IErrorHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicLexer implements ILexer {

    private List<Token> temporaryTokens;

    private static final List<String> KEYWORDS = Arrays.asList(
            "private",
            "static",
            "void"
    );

    private State state;

    private String line;

    private TokenFactory tokenFactory;

    private IErrorHandler errorHandler;

    public BasicLexer(TokenFactory tokenFactory, IErrorHandler errorHandler) {
        this.temporaryTokens = new ArrayList<>();
        this.tokenFactory = tokenFactory;
        this.errorHandler = errorHandler;
        this.state = new State();
    }

    @Override
    public List<Token> lexLine(String line) {
        this.temporaryTokens.clear();
        this.state.start = 0;
        this.state.current = 0;
        this.line = line;
        while (this.state.current < this.line.length()) {
            if (current() == '(') {
                temporaryTokens.add(tokenFactory.getToken(null, TokenType.LPAREN));
                next();
                reset();
            } else if (current() == ')') {
                temporaryTokens.add(tokenFactory.getToken(null, TokenType.RPAREN));
                next();
                reset();
            } else if (Character.isAlphabetic(current())) {
                scanIdentifier();
            } else if (current() == ' ') {
                next();
                reset();
            } else if (current() == '{') {
                temporaryTokens.add(tokenFactory.getToken(null, TokenType.LBRACE));
                next();
                reset();
            } else if (current() == '}') {
                temporaryTokens.add(tokenFactory.getToken(null, TokenType.RBRACE));
                next();
                reset();
            }
        }
        return this.temporaryTokens;
    }

    private void scanIdentifier() {
        if (Character.isAlphabetic(lookAhead()) || Character.isDigit(lookAhead())) {
            next();
            scanIdentifier();
        } else {
            if (KEYWORDS.contains(scan())) {
                temporaryTokens.add(tokenFactory.getToken(scan(), TokenType.KEYWORD));
                next();
                reset();
            }
            temporaryTokens.add(tokenFactory.getToken(scan(), TokenType.IDENT));
            next();
            reset();
        }
    }

    private char current() {
        return this.line.charAt(this.state.current);
    }

    private char lookAhead() {
        return this.line.charAt(this.state.current + 1);
    }

    private void next() {
        this.state.current += 1;
    }

    private void reset() {
        this.state.start = this.state.current;
    }

    private String scan() {
        return this.line.substring(this.state.start, this.state.current + 1);
    }

    private static class State {

        public Integer start;

        public Integer current;
    }
}
