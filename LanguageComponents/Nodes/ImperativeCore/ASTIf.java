package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

//TODO
public class ASTIf implements ASTNode {

    private ASTNode cond;
    private ASTNode body1;
    private ASTNode body2;

    public ASTIf(ASTNode cond, ASTNode body1, ASTNode body2) {
        this.cond = cond;
        this.body1 = body1;
        this.body2 = body2;
    }

    @Override
    public IValue eval(Environment env) {
    	IValue bool = cond.eval(env);
    	if (bool instanceof VBool) {
    		if ( ((VBool)bool).isTrue() )
    			return body1.eval(env);
    		else
    			return body2.eval(env);
    	}
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	cond.compile(env, codeBlock);
    	codeBlock.emit("ifeq L1");
    	body1.compile(env, codeBlock);
    	codeBlock.emit("goto L2");
    	codeBlock.emit("L1:");
    	body2.compile(env, codeBlock);
    	codeBlock.emit("L2:");
    }
}
