package LanguageComponents.Nodes.LogicOperators;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;

//TODO
public class ASTNeg implements ASTNode {

    private ASTNode right;

    public ASTNeg(ASTNode right) {
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
        return null;
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {

    }
}
