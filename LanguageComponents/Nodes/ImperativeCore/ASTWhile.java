package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

//TODO
public class ASTWhile implements ASTNode {

    private ASTNode cond;
    private ASTNode body;

    public ASTWhile(ASTNode cond, ASTNode body) {
        this.cond = cond;
        this.body = body;
    }

    @Override
    public IValue eval(Environment env) {
    	IValue bool = cond.eval(env);
    	if (bool instanceof VBool) {
        	env = env.beginScope();
    		while ( ((VBool)bool).isTrue() ) {
    			body.eval(env); //nao eh bem isto
    			bool = cond.eval(env);
    		}
    		env = env.endScope();
    		return null;
    	}
    	else
    		throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	String l1 = IdGenerator.genLabels();
    	String l2 = IdGenerator.genLabels();
    	
    	codeBlock.emit(l1 + ":");
    	cond.compile(env, codeBlock);
    	codeBlock.emit("ifeq " + l2);
    	body.compile(env, codeBlock);
    	codeBlock.emit("goto " + l1);
    	codeBlock.emit(l2 + ":");
    }
}
