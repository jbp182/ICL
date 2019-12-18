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

public class ASTDeref implements ASTNode {

    private ASTNode right;
    private ASTType rightType;

    public ASTDeref(ASTNode right) {
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue val = right.eval(env);

        if(val instanceof VRef){
            return ((VRef) val).get();
        }
        throw new TypeError("Reference incorrect");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
        right.compile(env,codeBlock);
        codeBlock.emit("checkcast " + rightType);
        codeBlock.emit("getfield "+ rightType +"/v "+((ASTRefType)rightType).getType());
    }

    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
        rightType = right.typeCheck(env);

        if(!(rightType instanceof ASTRefType)){
            throw new TypeError("Not a reference");
        }

        return rightType;
    }
}
