package com.programmingmuffin.viper.lexer;

import com.programmingmuffin.viper.data.Token;

import java.util.List;

public interface ILexer {

    List<Token> lexLine(String line);
}
