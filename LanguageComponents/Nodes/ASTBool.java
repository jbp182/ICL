package LanguageComponents.Nodes;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Types.ASTBoolType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

public class ASTBool implements ASTNode {
	
	private VBool bool;
	
	public ASTBool(String bool) {
		this.bool = new VBool(Boolean.valueOf(bool));
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return bool;
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		codeBlock.emit("sipush "+ (bool.isTrue() ? 1 : 0 ));
	}

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		return ASTBoolType.getInstance();
	}

}
