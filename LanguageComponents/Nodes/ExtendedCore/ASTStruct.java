package LanguageComponents.Nodes.ExtendedCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VStruct;

import java.util.HashMap;
import java.util.Map;

//TODO
public class ASTStruct implements ASTNode {


    private Map<String,ASTNode> structAsMap;

    public ASTStruct(Map<String,ASTNode> structAsMap){
        this.structAsMap = structAsMap;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        Map<String,IValue> result = new HashMap<>();

        for(Map.Entry<String,ASTNode> s : structAsMap.entrySet()){
            result.put(s.getKey(),s.getValue().eval(env));
        }

        return new VStruct(result);
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {

    }

    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
        return null;
    }

}
