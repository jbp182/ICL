package LanguageComponents.Nodes;
import LanguageComponents.Values.IValue;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;

public interface ASTNode {
	
	IValue eval(Environment env);

	void compile(CompilerEnvironment env, CodeBlock codeBlock);

}
