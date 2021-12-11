package visitors;

import token.BracketToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;

import java.util.List;
import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private final List<Token> tokens;
    private final Stack<Integer> stack = new Stack<>();

    public CalcVisitor(List<Token> tokens) {
        this.tokens = tokens;
    }

    public long calc() {
        if (stack.isEmpty()) {
            for (Token token : tokens) {
                if (token instanceof BracketToken) {
                    this.visit((BracketToken) token);
                }
                if (token instanceof OperationToken) {
                    this.visit((OperationToken) token);
                }
                if (token instanceof NumberToken) {
                    this.visit((NumberToken) token);
                }
            }
        }
        return stack.peek();
    }

    @Override
    public void visit(NumberToken token) {
        stack.push(token.getValue());
    }

    @Override
    public void visit(BracketToken token) {
        throw new RuntimeException("no brackets expected");
    }

    @Override
    public void visit(OperationToken token) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Invalid reverse polish expression");
        }
        final int y = stack.pop();
        final int x = stack.pop();
        stack.push(token.apply(x, y));
    }
}
