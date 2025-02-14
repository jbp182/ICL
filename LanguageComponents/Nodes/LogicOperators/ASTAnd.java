package LanguageComponents.Nodes.LogicOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTBoolType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

public class ASTAnd implements ASTNode {

    private ASTNode left;
    private ASTNode right;
    private ASTType leftType;
    private ASTType rightType;

    public ASTAnd(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
        this.leftType = null;
        this.rightType = null;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
    	IValue bool1 = left.eval(env);
    	if (bool1 instanceof VBool) {
    		IValue bool2 = right.eval(env);
    		if (bool2 instanceof VBool)
    			return new VBool( ((VBool)bool1).isTrue() && ((VBool)bool2).isTrue() );
    	}
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	left.compile(env, codeBlock);
    	right.compile(env, codeBlock);
    	codeBlock.emit("iand");
    }

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		leftType = left.typeCheck(env);
		rightType = right.typeCheck(env);
		if (leftType instanceof ASTBoolType && rightType instanceof ASTBoolType)
			return ASTBoolType.getInstance();
		throw new TypeError("Type mismatch. Expecting boolean.");
	}
}
