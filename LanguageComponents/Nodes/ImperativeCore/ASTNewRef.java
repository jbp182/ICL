package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTRefType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VRef;


public class ASTNewRef implements ASTNode {

    private ASTNode right;
    private ASTType rightType;

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
        codeBlock.buildRefIfDoesNotExist("ref_"+rightType.toString());

        codeBlock.emit("new ref_"+rightType);
        codeBlock.emit("dup");
        codeBlock.emit("invokespecial ref_"+ rightType +"/<init>()V");
        codeBlock.emit("dup");
        right.compile(env,codeBlock);
        codeBlock.emit("putfield ref_"+rightType+"/v "+rightType);
    }

    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
        rightType = right.typeCheck(env);
        return ASTType.build(ASTType.REF,rightType);
    }
}
