package LanguageComponents.Nodes.ExtendedCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTStringType;
import LanguageComponents.Types.ASTStructType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Types.CompostType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VStruct;

import java.util.*;

//TODO
public class ASTStruct implements ASTNode {

    private Map<String,ASTNode> structAsMap;
    private Map<String,ASTType> structTypes;
    private ASTType structType;


    public ASTStruct(Map<String,ASTNode> structAsMap, Map<String,ASTType> structTypes){
        this.structAsMap = structAsMap;
        this.structTypes = structTypes;
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
        codeBlock.emit("new " + structType);
        codeBlock.emit("dup");
        codeBlock.emit("invokespecial "+structType+"/<init>()V");

        Iterator<Map.Entry<String,ASTType>> typeIt = structTypes.entrySet().iterator();
        Iterator<Map.Entry<String,ASTNode>> nodeIt = structAsMap.entrySet().iterator();

        while(typeIt.hasNext() && nodeIt.hasNext()){
            Map.Entry<String,ASTType> t = typeIt.next();
            Map.Entry<String,ASTNode> n = nodeIt.next();

            codeBlock.emit("dup");

            n.getValue().compile(env, codeBlock);
            

            if(t.getValue() instanceof CompostType){
                codeBlock.emit("putfield "+ structType+"/"+t.getKey()+" L"+t.getValue()+";");
            } else{
                codeBlock.emit("putfield "+ structType+"/"+t.getKey()+" "+t.getValue());
            }

        }

        codeBlock.buildStruct(structType,this.structAsMap,this.structTypes);
    }

    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {

        List<ASTType> types = new LinkedList();
        Iterator<Map.Entry<String,ASTType>> typeIt = structTypes.entrySet().iterator();
        Iterator<Map.Entry<String,ASTNode>> nodeIt = structAsMap.entrySet().iterator();

    	while(typeIt.hasNext() && nodeIt.hasNext()){
            Map.Entry<String,ASTType> t = typeIt.next();
            Map.Entry<String,ASTNode> n = nodeIt.next();
            ASTType nType = n.getValue().typeCheck(env);

            if(!nType.equals(t.getValue()))
                throw new TypeError("Different Type Expected");

            types.add(t.getValue());
        }
    	
    	this.structType = ASTStructType.getInstance(types,IdGenerator.genStructId(),this.structTypes);
    	return structType;
    }

}
