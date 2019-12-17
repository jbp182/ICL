package LanguageComponents.Nodes;
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
	}
	
	public ASTId(String id, String type) {
		this.id = id;
		this.type = ASTType.build(type);
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return env.find(id);
	}

	public String toString(){
		return id;
	}
	
	public ASTType getType() {
		return type;
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

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		return type;
	}
}
