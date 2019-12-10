package LanguageComponents.Nodes.RelationalOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTBoolType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

//TODO
public class ASTEquals implements ASTNode {

    private ASTNode left;
    private ASTNode right;
    private ASTType leftType;
    private ASTType rightType;

    public ASTEquals(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
        this.leftType = null;
        this.rightType = null;
    }

    @Override
    public IValue eval(InterpreterEnvironment<IValue> env) {
    	boolean res = left.eval(env) == right.eval(env);		// implementamos um equals nos no's ??
    	return new VBool( res );
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	left.compile(env, codeBlock);
    	right.compile(env, codeBlock);
        String l1 = IdGenerator.genLabels();
        String l2 = IdGenerator.genLabels();
    	codeBlock.emit("isub");
    	codeBlock.emit("ifeq "+l1);
    	codeBlock.emit("sipush 0");
    	codeBlock.emit("goto "+l2);
    	codeBlock.emit(l1+":");
    	codeBlock.emit("sipush 1");
    	codeBlock.emit(l2+":");
    }
    
	@Override
	public ASTType typecheck(InterpreterEnvironment<ASTType> env) throws TypeError {
		leftType = left.typecheck(env);
		rightType = right.typecheck(env);
		if (leftType.equals(rightType))
			return ASTBoolType.getInstance();
		throw new TypeError("Types do not match.");
	}
}
