package LanguageComponents.Nodes.ImperativeCore;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
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
    public IValue eval(InterpreterEnvironment<IValue> env) {
    	left.eval(env);
        return right.eval(env);
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
        left.compile(env,codeBlock);
        codeBlock.emit("pop");
        right.compile(env,codeBlock);
    }
}
