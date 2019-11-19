package LanguageComponents.Nodes.RelationalOperators;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

//TODO
public class ASTEquals implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTEquals(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
    	boolean res = left.eval(env) == right.eval(env);		// implementamos um equals nos no's ??
    	return new VBool( res );
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	left.compile(env, codeBlock);
    	right.compile(env, codeBlock);
    	codeBlock.emit("isub");
    	codeBlock.emit("ifeq L1");
    	codeBlock.emit("sipush 0");
    	codeBlock.emit("goto L2");
    	codeBlock.emit("L1:");
    	codeBlock.emit("sipush 1");
    	codeBlock.emit("L2:");
    }
}
