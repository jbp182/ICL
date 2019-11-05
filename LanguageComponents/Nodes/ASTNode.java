package LanguageComponents.Nodes;
import LanguageComponents.Envirements.CodeBlock;
import LanguageComponents.Envirements.CompilerEnvirement;
import LanguageComponents.Envirements.Environment;
import LanguageComponents.Values.IValue;

public interface ASTNode {
	
	IValue eval(Environment env);

	void compile(CompilerEnvirement env, CodeBlock codeBlock);

}
