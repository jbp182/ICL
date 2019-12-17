package LanguageComponents.Nodes;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VInt;
import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Types.ASTIntType;
import LanguageComponents.Types.ASTType;

public class ASTNum implements ASTNode {
	
	VInt value;
	
	public ASTNum(int v) {
		value = new VInt(v);
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		return value;
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		codeBlock.emit("sipush "+ value.getval());
	}

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		return ASTIntType.getInstance();
	}

}
