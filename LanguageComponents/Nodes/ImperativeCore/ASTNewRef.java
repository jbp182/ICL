package LanguageComponents.Nodes.ImperativeCore;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
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
    public IValue eval(InterpreterEnvironment env) {
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

}
