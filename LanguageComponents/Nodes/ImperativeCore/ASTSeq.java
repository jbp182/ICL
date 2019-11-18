package LanguageComponents.Nodes.ImperativeCore;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;

//TODO
public class ASTSeq implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTSeq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
    	env = env.beginScope();
    	// TODO
    	env = env.endScope();
        return null;
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {

    }
}
