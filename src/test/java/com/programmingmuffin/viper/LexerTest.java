package com.programmingmuffin.viper;

import com.programmingmuffin.viper.data.ErrorManagement;
import com.programmingmuffin.viper.data.Token;
import com.programmingmuffin.viper.data.TokenFactory;
import com.programmingmuffin.viper.error.BasicErrorHandler;
import com.programmingmuffin.viper.error.IErrorHandler;
import com.programmingmuffin.viper.lexer.BasicLexer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LexerTest {

    private final String positiveLineCode = "private static void main() {}";

    private final List<Token> positiveLineTokens = Arrays.asList(

    );

    private TokenFactory tokenFactory;

    private IErrorHandler errorHandler;

    public LexerTest() {
        this.tokenFactory = new TokenFactory();
        this.errorHandler = new BasicErrorHandler(new ErrorManagement());
    }

    @Test
    void testPositiveLine() {
        BasicLexer basicLexer = new BasicLexer(tokenFactory, errorHandler);
        System.out.println(basicLexer.lexLine(positiveLineCode));
    }
}
