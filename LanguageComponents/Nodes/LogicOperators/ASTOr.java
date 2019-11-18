package LanguageComponents.Nodes.LogicOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

//TODO
public class ASTOr implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTOr(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
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

    }
}
