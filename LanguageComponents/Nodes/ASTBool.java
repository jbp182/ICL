package LanguageComponents.Nodes;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

public class ASTBool implements ASTNode {
	
	private VBool bool;
	
	public ASTBool(String bool) {
		this.bool = new VBool(Boolean.valueOf(bool));
	}

	@Override
	public IValue eval(InterpreterEnvironment env) {
		return bool;
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		codeBlock.emit("sipush "+ (bool.isTrue() ? 1 : 0 ));
	}

}
