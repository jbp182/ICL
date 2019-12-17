package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VClosure;

import java.util.List;



public class ASTFun implements ASTNode {

    private List<ASTNode> param;
    private ASTNode body;
    
    public ASTFun(List<ASTNode> left, ASTNode body) {
        this.param = left;
        this.body = body;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
       return new VClosure(param,body,env);
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
        //TODO
    }

    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
        return null;//TODO
    }
}
