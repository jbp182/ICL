package Nodes;
import Values.IValue;

public interface ASTNode {
	
	IValue eval(Environment env);

}
