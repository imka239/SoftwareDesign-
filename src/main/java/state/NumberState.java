package state;

import token.NumberToken;
import token.Token;

import java.util.List;

public class NumberState implements TokenizerState {
    final StringBuffer number = new StringBuffer();

    @Override
    public boolean isEOF() {
        return false;
    }

    @Override
    public TokenizerState consume(char c, List<Token> tokens) throws IllegalStateException {
        if (Character.isDigit(c)) {
            number.append(c);
            return this;
        } else {
            tokens.add(new NumberToken(Integer.parseInt(number.toString())));
            return new StartState().consume(c, tokens);
        }
    }
}
