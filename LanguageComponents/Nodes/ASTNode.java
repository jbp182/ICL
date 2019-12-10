package LanguageComponents.Nodes;
import LanguageComponents.Values.IValue;
import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Types.ASTType;

public interface ASTNode {
	
	IValue eval(InterpreterEnvironment<IValue> env);

	void compile(CompilerEnvironment env, CodeBlock codeBlock);
	
	ASTType typecheck(InterpreterEnvironment<ASTType> env) throws TypeError;

}
