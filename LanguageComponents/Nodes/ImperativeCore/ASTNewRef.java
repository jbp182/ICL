package LanguageComponents.Nodes.ImperativeCore;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VRef;

//TODO
public class ASTNewRef implements ASTNode {

    private ASTNode right;

    public ASTNewRef(ASTNode right) {
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
        IValue val = right.eval(env);
        return new VRef(val);
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {

    }
}
