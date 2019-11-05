package LanguageComponents.Nodes;
import Exceptions.TypeError;
import LanguageComponents.Envirements.CodeBlock;
import LanguageComponents.Envirements.CompilerEnvirement;
import LanguageComponents.Envirements.Environment;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VInt;

public class ASTPlus implements ASTNode {
	
	ASTNode left;
	ASTNode right;
	
	public ASTPlus(ASTNode v1, ASTNode v2) {
		left = v1;
		right = v2;
	}
	
	public IValue eval(Environment env) {
		IValue v1 = left.eval(env);
		if (v1 instanceof VInt) {
			IValue v2 = right.eval(env);
			if (v2 instanceof VInt)
				return new VInt( ((VInt)v1).getval() + ((VInt)v2).getval() );
		}
		throw new TypeError("illegal arguments to + operator");
	}

	@Override
	public void compile(CompilerEnvirement env, CodeBlock codeBlock) {
		left.compile(env,codeBlock);
		right.compile(env,codeBlock);
		codeBlock.emit("iadd");
	}

}
