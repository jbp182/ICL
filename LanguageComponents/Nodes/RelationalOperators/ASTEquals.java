package LanguageComponents.Nodes.RelationalOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTBoolType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;
import LanguageComponents.Values.VInt;

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
    public IValue eval(Environment<IValue> env) {
    	IValue v1 = left.eval(env);
    	IValue v2 = right.eval(env);
    	boolean res;
    	if (v1 instanceof VInt && v2 instanceof VInt)
    		res = ((VInt)v1).getval() == ((VInt)v2).getval();
    	else if (v1 instanceof VBool && v2 instanceof VBool)
    		res = ((VBool)v1).isTrue() == ((VBool)v2).isTrue();
    	else
    		throw new TypeError("Error: operation '=='. Only accepts int or bool type.");
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
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		leftType = left.typeCheck(env);
		rightType = right.typeCheck(env);
		if (leftType.equals(rightType))
			return ASTBoolType.getInstance();
		throw new TypeError("Types do not match.");
	}
}
