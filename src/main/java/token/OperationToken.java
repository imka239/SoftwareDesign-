package token;

import visitors.TokenVisitor;

public class OperationToken implements Token {

    private final OperationType operationType;

    public OperationToken(OperationType operationType) {
        this.operationType = operationType;
    }

    public enum OperationType {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public char print() {
        if (operationType == OperationType.ADD) {
            return '+';
        }
        if (operationType == OperationType.SUBTRACT) {
            return '-';
        }
        if (operationType == OperationType.MULTIPLY) {
            return '*';
        }
        if (operationType == OperationType.DIVIDE) {
            return '/';
        }
        throw new RuntimeException("Unexpected operation type");
    }

    public int apply(int x, int y) {
        if (operationType == OperationType.ADD) {
            return x + y;
        }
        if (operationType == OperationType.SUBTRACT) {
            return x - y;
        }
        if (operationType == OperationType.MULTIPLY) {
            return x * y;
        }
        if (operationType == OperationType.DIVIDE) {
            return x / y;
        }
        throw new RuntimeException("Type isn't expected");
    }
}
