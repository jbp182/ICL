package LanguageComponents.Nodes.LogicOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTBoolType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

public class ASTOr implements ASTNode {

    private ASTNode left;
    private ASTNode right;
    private ASTType leftType;
    private ASTType rightType;

    public ASTOr(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
        this.leftType = null;
        this.rightType = null;
    }

    @Override
    public IValue eval(InterpreterEnvironment<IValue> env) {
    	IValue bool1 = left.eval(env);
    	if (bool1 instanceof VBool) {
    		IValue bool2 = right.eval(env);
    		if (bool2 instanceof VBool) {
    			boolean res = ((VBool)bool1).isTrue() || ((VBool)bool2).isTrue();
    			return new VBool( res );
    		}
    	}
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	left.compile(env, codeBlock);
    	right.compile(env, codeBlock);
    	codeBlock.emit("ior");
    }

	@Override
	public ASTType typecheck(InterpreterEnvironment<ASTType> env) throws TypeError {
		leftType = left.typecheck(env);
		rightType = right.typecheck(env);
		if (leftType instanceof ASTBoolType && rightType instanceof ASTBoolType)
			return ASTBoolType.getInstance();
		throw new TypeError("type mismatch. expected boolean.");
	}
}
