package LanguageComponents.Nodes;
import LanguageComponents.Values.IValue;
import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Types.ASTType;

public interface ASTNode {

	/**
	 * Evaluates ASTNode and returns the result
	 * for this ASTNode
	 * @param env
	 * @return Result of the INode (IValue)
	 */
	IValue eval(Environment<IValue> env) throws TypeError;

	/**
	 * Compiles the ASTNode and outputs the result to
	 * a specific provided codeBlock.
	 * @param env
	 * @param codeBlock
	 */
	void compile(CompilerEnvironment env, CodeBlock codeBlock);


	/**
	 * Used for checking if the AST Tree under this ASTNode
	 * is well types. In negative throws TypeError otherwise return ASTNode
	 * type
	 * @param env Environment for the type checking.
	 * @return Type of the ASTNode
	 * @throws TypeError Throws exception if the ASTTree is not well typed
	 */
	ASTType typeCheck(Environment<ASTType> env) throws TypeError;

}
