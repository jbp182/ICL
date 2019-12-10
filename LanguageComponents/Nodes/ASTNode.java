package LanguageComponents.Nodes;
import LanguageComponents.Values.IValue;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;

public interface ASTNode {
	
	IValue eval(InterpreterEnvironment env);

	void compile(CompilerEnvironment env, CodeBlock codeBlock);

}
