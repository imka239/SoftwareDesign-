package visitors;

import token.BracketToken;
import token.NumberToken;
import token.OperationToken;
import token.Token;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class PrintVisitor implements TokenVisitor {
    private PrintWriter printWriter;
    private List<Token> tokens;

    public PrintVisitor(List<Token> tokens, OutputStream outputStream) {
        this.printWriter = new PrintWriter(outputStream);
        this.tokens = tokens;
    }

    @Override
    public void visit(NumberToken numberToken) {
        printWriter.print(numberToken.getValue());
        printWriter.print(' ');
    }

    @Override
    public void visit(BracketToken bracketToken) {
        throw new IllegalStateException("Brackets are not expected in Polish notation");
    }

    @Override
    public void visit(OperationToken operationToken) {
        printWriter.print(operationToken.print());
        printWriter.print(' ');
    }

    public void print() {
        tokens.forEach(token -> {
            if (token instanceof BracketToken) {
                this.visit((BracketToken) token);
            }
            if (token instanceof OperationToken) {
                this.visit((OperationToken) token);
            }
            if (token instanceof NumberToken) {
                this.visit((NumberToken) token);
            }
        });
        printWriter.println();
        printWriter.flush();
    }
}
