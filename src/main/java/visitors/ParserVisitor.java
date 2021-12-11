package visitors;

import token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {
    private final Stack<Token> stack = new Stack<>();
    private final List<Token> tokens;
    private final List<Token> polish = new ArrayList<>();

    public ParserVisitor(final List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public void visit(final NumberToken numberToken) {
        polish.add(numberToken);
    }

    @Override
    public void visit(final BracketToken bracketToken) {
        if (bracketToken.getBracketType() == BracketToken.BracketType.LEFT) {
            stack.push(bracketToken);
        } else {
            while (!stack.isEmpty()) {
                Token topToken = stack.pop();
                if (topToken instanceof BracketToken) {
                    if (((BracketToken) topToken).getBracketType() == BracketToken.BracketType.LEFT) {
                        return;
                    }
                } else {
                    polish.add(topToken);
                }
            }
            throw new IllegalStateException("There are more close brackets than opened");
        }
    }

    private int getPriority(final OperationToken token) {
        if (token.getOperationType() == OperationToken.OperationType.ADD ||
            token.getOperationType() == OperationToken.OperationType.SUBTRACT) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void visit(final OperationToken operationToken) {
        final int priority = getPriority(operationToken);

        while (!stack.isEmpty()) {
            final Token top = stack.peek();
            if (top instanceof OperationToken operation && getPriority(operation) >= priority) {
                polish.add(stack.pop());
            } else {
                break;
            }
        }

        stack.add(operationToken);
    }

    public List<Token> getPolish() {
        if (!polish.isEmpty()) {
            return polish;
        }
        int pos = 0;
        while (pos < tokens.size()) {
            Token token = this.tokens.get(pos++);
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

        while (!stack.empty()) {
            if (stack.peek() instanceof BracketToken) {
                throw new IllegalStateException("Too many Open brackets");
            }
            polish.add(stack.pop());
        }

        return polish;
    }
}
