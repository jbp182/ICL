package Nodes;

import Values.IValue;

import java.util.List;

public class ASTLet implements ASTNode {
	
	String id;
	ASTNode init;
	ASTNode body;
	
	public ASTLet(String id, ASTNode init, ASTNode body) {
		this.id = id;
		this.init = init;
		this.body = body;
	}

	@Override
	public IValue eval(Environment env) {
		
		IValue v1 = init.eval(env);
		env = env.beginScope();
		env.assoc(id, v1);
		IValue v2 = body.eval(env);
		env = env.endScope();		
		
		return v2;
	}

	@Override
	public List<String> compile(Environment env) {
		return null;
	}


}
