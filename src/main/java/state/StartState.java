package state;

import token.BracketToken;
import token.OperationToken;
import token.Token;

import java.io.IOException;
import java.util.List;

public class StartState implements TokenizerState {
    @Override
    public boolean isEOF() {
        return false;
    }

    @Override
    public TokenizerState consume(char c, List<Token> tokens) throws IllegalStateException {
        if (c == ' ') {
            return this;
        }
        if (c == '+') {
            return addToken(new OperationToken(OperationToken.OperationType.ADD), tokens);
        }
        if (c == '-') {
            return addToken(new OperationToken(OperationToken.OperationType.SUBTRACT), tokens);
        }
        if (c == '/') {
            return addToken(new OperationToken(OperationToken.OperationType.DIVIDE), tokens);
        }
        if (c == '*') {
            return addToken(new OperationToken(OperationToken.OperationType.MULTIPLY), tokens);
        }
        if (c == '(') {
            return addToken(new BracketToken(BracketToken.BracketType.LEFT), tokens);
        }
        if (c == ')') {
            return addToken(new BracketToken(BracketToken.BracketType.RIGHT), tokens);
        }
        if (Character.isDigit(c)) {
            return new NumberState().consume(c, tokens);
        }
        if (c == '\0') {
            return new EndState();
        }
        throw new IllegalStateException("Can't parse token from " + c);

    }
    private TokenizerState addToken(final Token token, final List<Token> tokens) {
        tokens.add(token);
        return this;
    }
}
