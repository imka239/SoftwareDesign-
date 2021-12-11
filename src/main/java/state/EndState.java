package state;

import token.Token;

import java.util.List;

public class EndState implements TokenizerState{
    @Override
    public boolean isEOF() {
        return true;
    }

    @Override
    public TokenizerState consume(char c, List<Token> tokens) throws IllegalStateException {
        return this;
    }
}
