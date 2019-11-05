package LanguageComponents.Nodes;

import LanguageComponents.Envirements.CodeBlock;
import LanguageComponents.Envirements.CompilerEnvirement;
import LanguageComponents.Envirements.Environment;
import LanguageComponents.Envirements.IdGenerator;
import LanguageComponents.Values.IValue;

import java.util.HashSet;

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
	public void compile(CompilerEnvirement env, CodeBlock codeBlock) {
		env = env.beginScope(codeBlock);
		genStoreValues(codeBlock,init,env);
		body.compile(env,codeBlock);
		env = env.endScope(codeBlock);
	}


	private void genStoreValues(CodeBlock codeBlock,ASTNode node,CompilerEnvirement env) {
		codeBlock.emit("aload 4");
		node.compile(env.getAncestor(),codeBlock);
		env.assoc(id, IdGenerator.genVariableName());
		codeBlock.emit("putfield "+env+"/"+env.find(id).variableId+" I");
	}


}
