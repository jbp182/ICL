package LanguageComponents.Nodes.FuncionalCore;

import Exceptions.TypeError;
import LanguageComponents.Nodes.ImperativeCore.ASTNewRef;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;

import java.util.Iterator;
import java.util.List;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;


public class ASTLet implements ASTNode {
	
	private List<String> ids;
	private List<ASTType> types;
	private List<ASTNode> inits;
	private ASTNode body;
	
	public ASTLet(List<String> ids, List<ASTType> types, List<ASTNode> inits, ASTNode body) {
		this.ids = ids;
		this.types = types;
		this.inits = inits;
		this.body = body;
	}

	@Override
	public IValue eval(Environment<IValue> env) {

		Environment<IValue> newEnv = env.beginScope();
		Iterator<String> itId = ids.iterator();
		Iterator<ASTNode> itInit = inits.iterator();
		while ( itId.hasNext() && itInit.hasNext() ) {
			IValue v1 = itInit.next().eval(env);
			newEnv.assoc(itId.next(), v1);
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
		env = env.endScope(codeBlock,ids,types);
	}

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		Environment<ASTType> newEnv = env.beginScope();
		Iterator<String> itId = ids.iterator();
		Iterator<ASTType> itType = types.iterator();
		Iterator<ASTNode> itNodes = inits.iterator();

		while ( itId.hasNext() && itType.hasNext() && itNodes.hasNext()) {
			String id = itId.next();
			ASTType type = itType.next();
			ASTType nodeType = itNodes.next().typeCheck(env);

			if(!type.equals(nodeType)){
				throw new TypeError("Type Error");
			}

			newEnv.assoc(id, type);
		}
		
		return body.typeCheck(newEnv);
	}

	private void genStoreValues(CodeBlock codeBlock, CompilerEnvironment env) {
		Iterator<String> itId = ids.iterator();
		Iterator<ASTNode> itInit = inits.iterator();
		Iterator<ASTType> itType = types.iterator();

		while( itId.hasNext() && itInit.hasNext() && itType.hasNext()) {
			codeBlock.emit("aload 4");
			ASTNode node = itInit.next();
			ASTType type = itType.next();
			String id = itId.next();
			node.compile(env.getAncestor(), codeBlock);

			String compileId;
			if(isRef(node)) {
				compileId =  IdGenerator.genRefName();
				codeBlock.emit("putfield " + env + "/" +compileId + " L"+type+";");
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
