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
                temporaryTokens.add(tokenFactory.getToken(null, TokenType.LPAREN, this.state.start, this.state.current));
                next();
                reset();
            } else if (current() == ')') {
                temporaryTokens.add(tokenFactory.getToken(null, TokenType.RPAREN, this.state.start, this.state.current));
                next();
                reset();
            } else if (Character.isWhitespace(current())) {
                next();
                reset();
            } else if (current() == '{') {
                temporaryTokens.add(tokenFactory.getToken(null, TokenType.LBRACE, this.state.start, this.state.current));
                next();
                reset();
            } else if (current() == '}') {
                temporaryTokens.add(tokenFactory.getToken(null, TokenType.RBRACE, this.state.start, this.state.current));
                next();
                reset();
            } else if (Character.isLetter(current())) {
                scanIdentifier();
            }
        }
        return this.temporaryTokens;
    }

    private void scanIdentifier() {
        next();
        if (Character.isLetterOrDigit(current())) {
            scanIdentifier();
        } else {
            if (KEYWORDS.contains(scan())) {
                temporaryTokens.add(tokenFactory.getToken(scan(), TokenType.KEYWORD, this.state.start, this.state.current));
                reset();
            } else {
                temporaryTokens.add(tokenFactory.getToken(scan(), TokenType.IDENT, this.state.start, this.state.current));
                reset();
            }
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
        return this.line.substring(this.state.start, this.state.current);
    }

    private static class State {

        public Integer start;

        public Integer current;
    }
}
