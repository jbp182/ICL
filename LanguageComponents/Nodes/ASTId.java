package LanguageComponents.Nodes;
import LanguageComponents.Types.ASTFunType;
import LanguageComponents.Types.ASTRefType;
import LanguageComponents.Types.CompostType;
import LanguageComponents.Values.IValue;
import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.CompilerPair;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Types.ASTType;

public class ASTId implements ASTNode {
	
	private String id;
	private ASTType type;
	
	public ASTId(String id) {
		this.id = id;
		this.type = null;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return env.find(id);
	}

	public String toString(){
		return id;
	}
	

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		CompilerPair pair = env.find(id);

		codeBlock.emit("aload 4");
		if(env.getPredecessor() != null)
			imitGetField(env.getPredecessor(),codeBlock);

		CompilerEnvironment tmp = env;
		for(int i = 0 ; i < pair.offset;i++){
			imitGetField(tmp,codeBlock);
			tmp = tmp.getAncestor();
		}

		if(type instanceof CompostType) {
			codeBlock.emit("getfield " + pair.frameId + "/" + pair.variableId + " L"+type+";");
		}
		else
			codeBlock.emit("getfield "+pair.frameId+"/"+pair.variableId+" I");
	}

	private void imitGetField(CompilerEnvironment env, CodeBlock codeBlock){
		codeBlock.emit("getfield "+env+"/sl L"+env.getAncestor()+";");
	}

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		if (type == null)
			type = env.find(this.id);
		return type;
	}
}
