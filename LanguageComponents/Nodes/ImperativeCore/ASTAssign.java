package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;
import LanguageComponents.Values.VStr;

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
        IValue id = left.eval(env);

        if ((id instanceof VStr)) {
            env.assoc(id.toString(),right.eval(env));
            return null; //TODO caires
        }
        throw new TypeError("Assign should have and Id");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
            //TODO
    }
}
