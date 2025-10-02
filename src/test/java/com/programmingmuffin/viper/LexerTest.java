package com.programmingmuffin.viper;

import com.programmingmuffin.viper.data.ErrorManagement;
import com.programmingmuffin.viper.data.Token;
import com.programmingmuffin.viper.data.TokenFactory;
import com.programmingmuffin.viper.data.TokenType;
import com.programmingmuffin.viper.error.BasicErrorHandler;
import com.programmingmuffin.viper.error.IErrorHandler;
import com.programmingmuffin.viper.lexer.BasicLexer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LexerTest {

    private final String positiveLineCode = "state Car { Wheel }";

    private List<Token> positiveLineTokens;

    private TokenFactory tokenFactory;

    private IErrorHandler errorHandler;

    public LexerTest() {
        this.tokenFactory = new TokenFactory();
        this.errorHandler = new BasicErrorHandler(new ErrorManagement());
        this.positiveLineTokens = Arrays.asList(
                tokenFactory.getToken("state", TokenType.KEYWORD),
                tokenFactory.getToken("Car", TokenType.IDENT),
                tokenFactory.getToken(null, TokenType.LBRACE),
                tokenFactory.getToken("Wheel", TokenType.IDENT),
                tokenFactory.getToken(null, TokenType.RBRACE)
        );
    }

    @Test
    void testPositiveLine() {
        BasicLexer basicLexer = new BasicLexer(tokenFactory, errorHandler);
        List<Token> result = basicLexer.lexLine(positiveLineCode);
        System.out.println(result);
        assert true;
    }
}
