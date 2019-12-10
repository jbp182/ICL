package LanguageComponents.Environments;
import java.util.HashMap;
import java.util.Map;

import Exceptions.IdAlreadyExistsException;
import Exceptions.NoSuchIdException;


public class InterpreterEnvironment<E> {

    InterpreterEnvironment<E> ancestor;
    Map<String,E> envMap;

    public InterpreterEnvironment(InterpreterEnvironment<E> env) {
        this.ancestor = env;
        envMap = new HashMap<String, E>();
    }

    public E find(String id){
        E val = envMap.get(id);
        
        if(val == null && ancestor != null)
            val = ancestor.find(id);
        
        if(val == null) 
        	throw new NoSuchIdException("Error: There is no such id ( " + id + " ).");
        
        return val;
    }
    
    public void assoc(String id, E val){
    	if (envMap.putIfAbsent(id, val) != null)
    			throw new IdAlreadyExistsException("Error: Id " + id + " already exists.");
    }
    
    public InterpreterEnvironment<E> beginScope(){
        return new InterpreterEnvironment<E>(this);
    }
    
    public InterpreterEnvironment<E> endScope(){
        return ancestor;
    }

}
