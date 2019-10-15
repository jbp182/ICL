package Nodes;
import Values.IValue;

public class ASTId implements ASTNode {
	
	String id;
	
	public ASTId(String id) {
		this.id = id;
	}

	@Override
	public IValue eval(Environment env) {
		return env.find(id);
	}

}
