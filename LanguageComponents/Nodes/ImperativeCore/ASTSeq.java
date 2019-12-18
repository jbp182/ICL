package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;


public class ASTSeq implements ASTNode {

    private ASTNode left;
    private ASTType leftType;

    private ASTNode right;
    private ASTType rightType;

    public ASTSeq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
    	left.eval(env);
        return right.eval(env);
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
        left.compile(env,codeBlock);
        codeBlock.emit("pop");
        right.compile(env,codeBlock);
    }

    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
        leftType = left.typeCheck(env);
        rightType = right.typeCheck(env);
        return rightType;
    }
}
