package token;

import visitors.TokenVisitor;

public class BracketToken implements Token {
    public enum BracketType {
        LEFT,
        RIGHT
    }

    private final BracketType bracketType;

    public BracketToken(BracketType bracketType) {
        this.bracketType = bracketType;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public BracketType getBracketType() {
        return bracketType;
    }
}
