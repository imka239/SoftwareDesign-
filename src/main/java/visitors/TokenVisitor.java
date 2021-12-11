package visitors;

import token.BracketToken;
import token.NumberToken;
import token.OperationToken;

public interface TokenVisitor {
    void visit(NumberToken token);
    void visit(BracketToken token);
    void visit(OperationToken token);
}