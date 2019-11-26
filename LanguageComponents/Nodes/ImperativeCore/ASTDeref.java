package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VRef;

//TODO
public class ASTDeref implements ASTNode {

    private ASTNode right;

    public ASTDeref(ASTNode right) {
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
        IValue val = right.eval(env);

        if(val instanceof VRef){
            return ((VRef) val).get();
        }
        throw new TypeError("Reference incorrect");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {

    }
}
