package Nodes;
import java.util.HashMap;
import java.util.Map;

import Values.IValue;


public class Environment {

    Environment ancestor;
    Map<String,IValue> envMap;

    public Environment(Environment env) {
        this.ancestor = env;
        envMap = new HashMap<String, IValue>();
    }

    public IValue find(String id){
        IValue val = envMap.get(id);
        
        if(val == null)
            val = ancestor.find(id);
        
        if(val == null)
            throw new RuntimeException(); //TODO
        
        
        return val;
    }
    
    public void assoc(String id, IValue val){
        if (envMap.putIfAbsent(id, val) != null)
        	throw new RuntimeException();	//TODO
    }
    
    public Environment beginScope(){
        return new Environment(this);
    }
    
    public Environment endScope(){
        return ancestor;
    }

}
