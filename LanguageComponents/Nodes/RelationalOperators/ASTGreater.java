package LanguageComponents.Nodes.RelationalOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;
import LanguageComponents.Values.VInt;

//TODO
public class ASTGreater implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTGreater(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
    	IValue nr_left = left.eval(env);
    	if (nr_left instanceof VInt) {
    		IValue nr_right = right.eval(env);
    		if (nr_right instanceof VInt) {
    			boolean res = ((VInt)nr_left).getval() > ((VInt)nr_right).getval();
    			return new VBool( res );
    		}
    	}
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	left.compile(env, codeBlock);
    	right.compile(env, codeBlock);
    	codeBlock.emit("isub");
    	codeBlock.emit("ifgt L1");
    	codeBlock.emit("sipush 0");
    	codeBlock.emit("goto L2");
    	codeBlock.emit("L1:");
    	codeBlock.emit("sipush 1");
    	codeBlock.emit("L2:");
    }
}
