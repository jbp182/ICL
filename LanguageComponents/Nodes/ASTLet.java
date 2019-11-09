package LanguageComponents.Nodes;

import LanguageComponents.Values.IValue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Environments.IdGenerator;


public class ASTLet implements ASTNode {
	
	LinkedList<String> ids;
	LinkedList<ASTNode> inits;
	ASTNode body;
	
	public ASTLet(LinkedList<String> ids, LinkedList<ASTNode> inits, ASTNode body) {
		this.ids = ids;
		this.inits = inits;
		this.body = body;
	}

	@Override
	public IValue eval(Environment env) {

		Map<String,IValue> resultsMap = new HashMap<>();
		while (ids.size() > 0 && inits.size() > 0) {
			resultsMap.put(ids.poll(),inits.poll().eval(env));
		}
		env = env.beginScope();
		for(Map.Entry<String,IValue> entry : resultsMap.entrySet()){
			env.assoc(entry.getKey(),entry.getValue());
		}
		IValue v2 = body.eval(env);
		env = env.endScope();		
		
		return v2;
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		env = env.beginScope(codeBlock);
		genStoreValues(codeBlock,env);
		body.compile(env,codeBlock);
		env = env.endScope(codeBlock);
	}


//	private void genStoreValues(CodeBlock codeBlock,CompilerEnvironment env) {
//		codeBlock.emit("aload 4");
//		node.compile(env.getAncestor(),codeBlock);
//		env.assoc(ids, IdGenerator.genVariableName());
//		codeBlock.emit("putfield "+env+"/"+env.find(ids).variableId+" I");
//	}
	
	private void genStoreValues(CodeBlock codeBlock,CompilerEnvironment env) {
		while( ids.size() > 0 && inits.size() > 0 ) {
			codeBlock.emit("aload 4");
			inits.poll().compile(env.getAncestor(), codeBlock);
			String id = ids.poll();
			String compileId = IdGenerator.genVariableName();
			env.assoc(id, compileId);
			codeBlock.emit("putfield " + env + "/" + compileId + " I");
		}
	}


}
