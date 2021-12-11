package state;

import token.Token;

import java.io.IOException;
import java.util.List;

public interface TokenizerState {
    boolean isEOF();
    TokenizerState consume(final char c, final List<Token> tokens) throws IllegalStateException;
}
