package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTId;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VRef;


//TODO
public class ASTAssign implements ASTNode {

    private ASTNode left;
    private ASTNode right;

    public ASTAssign(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment env) {
        IValue ref = left.eval(env);

        if (ref instanceof VRef) {
            IValue val = right.eval(env);
            ((VRef) ref).set(val);

            return val;
        }
        throw new TypeError("Assign should have and Id");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
            //TODO
    }
}
