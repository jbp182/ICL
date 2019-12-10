package LanguageComponents.Nodes.ImperativeCore;

import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VClosure;

import java.util.List;


//TODO
public class ASTFun implements ASTNode {

    private List<ASTNode> param;
    private ASTNode body;
    
    public ASTFun(List<ASTNode> left, ASTNode body) {
        this.param = left;
        this.body = body;
    }

    @Override
    public IValue eval(InterpreterEnvironment<IValue> env) {
       return new VClosure(param,body,env);
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {

    }

}
