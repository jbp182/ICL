package LanguageComponents.Nodes.ExtendedCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VStruct;
import sun.tools.tree.Vset;

public class ASTStructFieldOp implements ASTNode {

    public static final String TYPE_CHECK_I = "Can check values different to struct";

    private ASTNode struct;
    private String id;

    public ASTStructFieldOp(ASTNode node,String id){
        this.struct = node;
        this.id = id;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue value = struct.eval(env);

        if(!(value instanceof VStruct))
            throw new TypeError(TYPE_CHECK_I);

        VStruct vStruct = (VStruct)value;


        return vStruct.getValue(id);
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {

    }

    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
        return null;
    }
}
