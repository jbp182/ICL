package LanguageComponents.Nodes;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VInt;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;

public class ASTNum implements ASTNode {
	
	VInt value;
	
	public ASTNum(int v) {
		value = new VInt(v);
	}

	@Override
	public IValue eval(InterpreterEnvironment env) {
		return value;
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		codeBlock.emit("sipush "+ value.getval());
	}

}
