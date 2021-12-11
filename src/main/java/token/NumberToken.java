package token;

import visitors.TokenVisitor;

public class NumberToken implements Token {
    private final int value;
    public NumberToken(int number) {
        this.value = number;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public int getValue() {
        return value;
    }
}
