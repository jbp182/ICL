package LanguageComponents.Nodes;
import LanguageComponents.Envirements.CodeBlock;
import LanguageComponents.Envirements.CompilerEnvirement;
import LanguageComponents.Envirements.Environment;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VInt;

public class ASTNum implements ASTNode {
	
	IValue value;
	
	public ASTNum(int v) {
		value = new VInt(v);
	}

	@Override
	public IValue eval(Environment env) {
		return value;
	}

	@Override
	public void compile(CompilerEnvirement env, CodeBlock codeBlock) {
		codeBlock.emit("sipush "+ ((VInt)value).getval());
	}

}
