package LanguageComponents.Nodes.RelationalOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTBoolType;
import LanguageComponents.Types.ASTIntType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;
import LanguageComponents.Values.VInt;

//TODO
public class ASTLower implements ASTNode {

    private ASTNode left;
    private ASTNode right;
    private ASTType leftType;
    private ASTType rightType;

    public ASTLower(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
        this.leftType = null;
        this.rightType = null;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
    	IValue nr_left = left.eval(env);
    	if (nr_left instanceof VInt) {
    		IValue nr_right = right.eval(env);
    		if (nr_right instanceof VInt) {
    			boolean res = ((VInt)nr_left).getval() < ((VInt)nr_right).getval();
    			return new VBool( res );
    		}
    	}
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	left.compile(env, codeBlock);
    	right.compile(env, codeBlock);

		String l1 = IdGenerator.genLabels();
		String l2 = IdGenerator.genLabels();;
    	codeBlock.emit("isub");
    	codeBlock.emit("iflt "+l1);
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
		if (leftType instanceof ASTIntType && rightType instanceof ASTIntType)
			return ASTBoolType.getInstance();
		throw new TypeError("Type mismatch, expecting int.");
	}
}
