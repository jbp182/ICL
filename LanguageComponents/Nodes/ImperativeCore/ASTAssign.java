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


public class ASTAssign implements ASTNode {

    private static final String TYPE_CHECKING_ERROR = "No Reference found";
    private static final String I_TYPE_CHECK_ERROR = "Assign should have and Id";
    private static final String TYPE_MISMATCH = "Right Side of assign type mismatch";

    private ASTNode left;
    private ASTType leftType;

    private ASTNode right;
    private ASTType rightType;

    public ASTAssign(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue ref = left.eval(env);

        if (ref instanceof VRef) {
            IValue val = right.eval(env);
            ((VRef) ref).set(val);

            return val;
        }
        throw new TypeError(I_TYPE_CHECK_ERROR);
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {


        left.compile(env,codeBlock);
        codeBlock.emit("checkcast "+leftType);
        codeBlock.emit("dup");
        right.compile(env,codeBlock);
        if(rightType instanceof ASTRefType){
            codeBlock.emit("putfield "+leftType+"/v L"+rightType+";");
            codeBlock.emit("getfield "+leftType+"/v L"+ rightType+";");
        }else{
            codeBlock.emit("putfield "+leftType+"/v "+rightType);
            codeBlock.emit("getfield "+leftType+"/v "+ rightType);
        }

    }

    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
        leftType = left.typeCheck(env);
        rightType = right.typeCheck(env);

        if(!(leftType instanceof ASTRefType))
            throw new TypeError(TYPE_CHECKING_ERROR);

        ASTRefType ref = (ASTRefType)leftType;

        if(!ref.checkTypeReference(rightType)){
            throw new TypeError(TYPE_MISMATCH);
        }

        return ref.getType();
    }
}
