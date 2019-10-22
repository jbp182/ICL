package Nodes;
import Values.IValue;

import java.util.List;

public interface ASTNode {
	
	IValue eval(Environment env);

	List<String> compile(Environment env);

}
