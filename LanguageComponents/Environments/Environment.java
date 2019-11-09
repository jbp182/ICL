package LanguageComponents.Environments;
import java.util.HashMap;
import java.util.Map;

import Exceptions.IdAlreadyExistsException;
import Exceptions.NoSuchIdException;
import LanguageComponents.Values.IValue;


public class Environment {

    Environment ancestor;
    Map<String,IValue> envMap;

    public Environment(Environment env) {
        this.ancestor = env;
        envMap = new HashMap<String, IValue>();
    }

    public IValue find(String id){
        IValue val = envMap.get(id);
        
        if(val == null && ancestor != null)
            val = ancestor.find(id);
        
        if(val == null) 
        	throw new NoSuchIdException("Error: There is no such id ( " + id + " ).");
        
        return val;
    }
    
    public void assoc(String id, IValue val){
    	try {
    		if (this.envMap.get(id) != null)
        		throw new IdAlreadyExistsException("Error: Id " + id + " already exists.");
    	} catch(NoSuchIdException e) {}	
    	
    	envMap.put(id, val);
    }
    
    public Environment beginScope(){
        return new Environment(this);
    }
    
    public Environment endScope(){
        return ancestor;
    }

}
