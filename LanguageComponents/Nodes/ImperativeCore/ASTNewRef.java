package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VRef;


public class ASTNewRef implements ASTNode {

    private ASTNode right;

    public ASTNewRef(ASTNode right) {
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue val = right.eval(env);
        return new VRef(val);
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
        codeBlock.emit("new ref_int");
        codeBlock.emit("dup");
        codeBlock.emit("invokespecial ref_int/<init>()V");
        codeBlock.emit("dup");
        right.compile(env,codeBlock);
        codeBlock.emit("putfield ref_int/v I");
    }


    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
        return null;//TODO
    }
}
