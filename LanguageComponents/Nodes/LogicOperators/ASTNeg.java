package LanguageComponents.Nodes.LogicOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
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
    public IValue eval(Environment env) {
    	IValue bool = node.eval(env);
    	if (bool instanceof VBool)
    		return new VBool( !((VBool)bool).isTrue() );
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	node.compile(env, codeBlock);
    	codeBlock.emit("ifeq L1:");
    	codeBlock.emit("sipush 0");
    	codeBlock.emit("goto L2");
    	codeBlock.emit("L1:");
    	codeBlock.emit("sipush 1");
    	codeBlock.emit("L2:");
    }
}
