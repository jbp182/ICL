package LanguageComponents.Nodes.ExtendedCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTStringType;
import LanguageComponents.Types.ASTStructType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VStruct;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//TODO
public class ASTStruct implements ASTNode {


    private Map<String,ASTNode> structAsMap;
    private Map<String,ASTType> structTypes;
    private ASTType structType;

    public ASTStruct(Map<String,ASTNode> structAsMap, Map<String,ASTType> structTypes){
        this.structAsMap = structAsMap;
        this.structTypes = structTypes;
    }
    
    // TODO - choose constructor
//    public ASTStruct(List<String> ids, List<ASTType> types, List<ASTNode> inits) {
//    	
//    	Iterator<String> itId = ids.iterator();
//    	Iterator<ASTType> itType = types.iterator();
//    	Iterator<ASTNode> itV = inits.iterator();
//    	String id;
//    	while (itId.hasNext() && itType.hasNext() && itV.hasNext()) {
//    		id = itId.next();
//    		structAsMap.put(id, itV.next());
//    		structTypes.put(id, itType.next());
//    	}
//    	
//    }

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
        
    	List<ASTType> types = new LinkedList();
    	
    	for(Map.Entry<String,ASTType> t : structTypes.entrySet()){
            types.add(t.getValue());
        }
    	
    	this.structType = ASTStructType.getInstance(types);
    	return structType;
    }

}
