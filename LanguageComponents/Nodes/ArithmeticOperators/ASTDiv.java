package LanguageComponents.Nodes.ArithmeticOperators;
import Exceptions.TypeError;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VInt;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;

public class ASTDiv implements ASTNode {

	ASTNode left;
	ASTNode right;
	
	public ASTDiv(ASTNode v1, ASTNode v2) {
		left = v1;
		right = v2;
	}
	
	public IValue eval(Environment env) {
		IValue v1 = left.eval(env);
		if (v1 instanceof VInt) {
			IValue v2 = right.eval(env);
			if (v2 instanceof VInt)
				return new VInt( ((VInt)v1).getval() / ((VInt)v2).getval() );
		}
		throw new TypeError("illegal arguments to / operator");
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		left.compile(env,codeBlock);
		right.compile(env,codeBlock);
		codeBlock.emit("idiv");
	}

}
