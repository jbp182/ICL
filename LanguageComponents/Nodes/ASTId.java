package LanguageComponents.Nodes;
import LanguageComponents.Values.IValue;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.CompilerPair;
import LanguageComponents.Environments.Environment;

public class ASTId implements ASTNode {
	
	String id;
	
	public ASTId(String id) {
		this.id = id;
	}

	@Override
	public IValue eval(Environment env) {
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
			imitGetField(env,codeBlock);
			tmp = tmp.getAncestor();
		}

		if(isRef(pair.variableId)) {
			codeBlock.emit("getfield " + pair.frameId + "/" + pair.variableId + " Lref_int;");
		}
		else
			codeBlock.emit("getfield "+pair.frameId+"/"+pair.variableId+" I");
	}

	private void imitGetField(CompilerEnvironment env, CodeBlock codeBlock){
		codeBlock.emit("getfield "+env+"/sl L"+env.getAncestor()+";");
	}

	private boolean isRef(String id){
		return id.charAt(0) == 'r';
	}
}
