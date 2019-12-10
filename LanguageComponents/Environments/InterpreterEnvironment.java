package LanguageComponents.Environments;
import java.util.HashMap;
import java.util.Map;

import Exceptions.IdAlreadyExistsException;
import Exceptions.NoSuchIdException;
import LanguageComponents.Values.IValue;


public class InterpreterEnvironment {

    InterpreterEnvironment ancestor;
    Map<String,IValue> envMap;

    public InterpreterEnvironment(InterpreterEnvironment env) {
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
    	if (envMap.putIfAbsent(id, val) != null)
    			throw new IdAlreadyExistsException("Error: Id " + id + " already exists.");
    }
    
    public InterpreterEnvironment beginScope(){
        return new InterpreterEnvironment(this);
    }
    
    public InterpreterEnvironment endScope(){
        return ancestor;
    }

}
