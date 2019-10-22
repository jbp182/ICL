package Nodes;
import Values.IValue;

import java.util.LinkedList;
import java.util.List;

public class ASTId implements ASTNode {
	
	String id;
	
	public ASTId(String id) {
		this.id = id;
	}

	@Override
	public IValue eval(Environment env) {
		return env.find(id);
	}

	@Override
	public List<String> compile(Environment env) {
		return null;
	}
}
