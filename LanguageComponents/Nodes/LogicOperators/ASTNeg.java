package LanguageComponents.Nodes.LogicOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

//TODO
public class ASTNeg implements ASTNode {

    private ASTNode node;

    public ASTNeg(ASTNode node) {
        this.node = node;
    }

    @Override
    public IValue eval(InterpreterEnvironment env) {
    	IValue bool = node.eval(env);
    	if (bool instanceof VBool)
    		return new VBool( !((VBool)bool).isTrue() );
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
        String l1 = IdGenerator.genLabels();
        String l2 = IdGenerator.genLabels();
        
    	node.compile(env, codeBlock);
    	codeBlock.emit("ifeq "+l1);
    	codeBlock.emit("sipush 0");
    	codeBlock.emit("goto "+l2);
    	codeBlock.emit(l1+":");
    	codeBlock.emit("sipush 1");
    	codeBlock.emit(l2 + ":");
    }
}
