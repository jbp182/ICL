package LanguageComponents.Nodes;
import LanguageComponents.Envirements.CodeBlock;
import LanguageComponents.Envirements.CompilerEnvirement;
import LanguageComponents.Envirements.CompilerPair;
import LanguageComponents.Envirements.Environment;
import LanguageComponents.Values.IValue;

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
	public void compile(CompilerEnvirement env, CodeBlock codeBlock) {
		CompilerPair pair = env.find(id);

		codeBlock.emit("aload 4");
		CompilerEnvirement tmp = env;
		for(int i = 0 ; i < pair.offset;i++){
			codeBlock.emit("getfield "+tmp+"/sl L"+tmp.getAncestor()+";");
			tmp = tmp.getAncestor();
		}
		codeBlock.emit("getfield "+pair.frameId+"/"+pair.variableId+" I");
	}
}
