package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;

public class ASTPrint implements ASTNode {
	
	private ASTNode node;
	
	public ASTPrint(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v = node.eval(env);
		v.show();
		return v;
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		return node.typeCheck(env);
	}
	

}
