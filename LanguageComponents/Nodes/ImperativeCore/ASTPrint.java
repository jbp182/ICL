package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTBoolType;
import LanguageComponents.Types.ASTIntType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;

public class ASTPrint implements ASTNode {
	
	private ASTNode node;
	private ASTType type;
	
	public ASTPrint(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v = node.eval(env);
		v.show();
		return v;
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		node.compile(env, codeBlock);
		codeBlock.emit("dup");
		codeBlock.emit(" getstatic java/lang/System/out Ljava/io/PrintStream;");
		codeBlock.emit("swap");

		if (type instanceof ASTIntType || type instanceof ASTBoolType)
			codeBlock.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");

		codeBlock.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
	}

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		type = node.typeCheck(env);
		return type;
	}
	

}
