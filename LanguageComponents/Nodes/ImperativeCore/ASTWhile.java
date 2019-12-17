package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

public class ASTWhile implements ASTNode {

    private ASTNode cond;
    private ASTNode body;

    public ASTWhile(ASTNode cond, ASTNode body) {
        this.cond = cond;
        this.body = body;
    }

    //TODO while conveção devolve falso
    @Override
    public IValue eval(Environment<IValue> env) {
    	IValue bool = cond.eval(env);
    	if (bool instanceof VBool) {
        	IValue res = null;
        	env = env.beginScope();
        	
    		while ( ((VBool)bool).isTrue() ) {
    			res = body.eval(env);
    			bool = cond.eval(env);
    		}
    		
    		env = env.endScope();
    		return res;
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
    	codeBlock.emit("pop");
    	codeBlock.emit("goto " + l1);
    	codeBlock.emit(l2 + ":");
    	codeBlock.emit("sipush 0");
    }

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		return null;//TODO
	}
}
