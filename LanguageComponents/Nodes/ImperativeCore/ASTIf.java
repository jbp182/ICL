package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Environments.IdGenerator;
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
    public IValue eval(InterpreterEnvironment env) {
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

    	String l1 = IdGenerator.genLabels();
    	String l2 = IdGenerator.genLabels();
    	codeBlock.emit("ifeq "+l1);
    	body1.compile(env, codeBlock);
    	codeBlock.emit("goto "+l2);
    	codeBlock.emit(l1+":");
    	body2.compile(env, codeBlock);
    	codeBlock.emit(l2+":");
    }
}
