package LanguageComponents.Nodes;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Types.ASTStringType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VStr;

public class ASTString implements ASTNode {
	
	private VStr str;
	
	public ASTString(String str) {
		this.str = new VStr(str);
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return str;
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		codeBlock.emit("ldc " + str.getval());
	}

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		return ASTStringType.getInstance();
	}

}
