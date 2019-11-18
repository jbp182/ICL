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
public class ASTGreaterEqual implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTGreaterEqual(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
    	IValue nr_left = left.eval(env);
    	if (nr_left instanceof VInt) {
    		IValue nr_right = right.eval(env);
    		if (nr_right instanceof VInt) {
    			boolean res = ((VInt)nr_left).getval() >= ((VInt)nr_right).getval();
    			return new VBool( res );
    		}
    	}
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {

    }
}
