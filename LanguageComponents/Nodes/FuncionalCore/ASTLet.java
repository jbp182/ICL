package LanguageComponents.Nodes.FuncionalCore;

import LanguageComponents.Nodes.ImperativeCore.ASTNewRef;
import LanguageComponents.Values.IValue;

import java.util.LinkedList;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;


public class ASTLet implements ASTNode {
	
	private LinkedList<String> ids;
	private LinkedList<ASTNode> inits;
	private ASTNode body;
	
	public ASTLet(LinkedList<String> ids, LinkedList<ASTNode> inits, ASTNode body) {
		this.ids = ids;
		this.inits = inits;
		this.body = body;
	}

	@Override
	public IValue eval(InterpreterEnvironment<IValue> env) {

		InterpreterEnvironment<IValue> newEnv = env.beginScope();
		
		while (ids.size() > 0 && inits.size() > 0) {
			IValue v1 = inits.poll().eval(env);
			newEnv.assoc(ids.poll(), v1);
		}
		
		IValue v2 = body.eval(newEnv);
		env = newEnv.endScope();
		
		return v2;
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		env = env.beginScope(codeBlock);
		genStoreValues(codeBlock,env);
		body.compile(env,codeBlock);
		env = env.endScope(codeBlock);
	}

	private void genStoreValues(CodeBlock codeBlock,CompilerEnvironment env) {
		while( ids.size() > 0 && inits.size() > 0 ) {
			codeBlock.emit("aload 4");
			ASTNode node = inits.poll();
			String id = ids.poll();
			node.compile(env.getAncestor(), codeBlock);

			String compileId;
			if(isRef(node)) {
				compileId =  IdGenerator.genRefName();
				codeBlock.emit("putfield " + env + "/" +compileId + " Lref_int;");
			}else{
				compileId =  IdGenerator.genVariableName();
				codeBlock.emit("putfield " + env + "/" + compileId + " I");
			}
			env.assoc(id, compileId);
		}
	}

	private boolean isRef(ASTNode node){
		return node instanceof ASTNewRef;
	}

}
