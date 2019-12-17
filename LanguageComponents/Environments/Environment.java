package LanguageComponents.Environments;
import java.util.HashMap;
import java.util.Map;

import Exceptions.IdAlreadyExistsException;
import Exceptions.NoSuchIdException;


public class Environment<E> {

    Environment<E> ancestor;
    Map<String,E> envMap;

    public Environment(Environment<E> env) {
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
    
    public Environment<E> beginScope(){
        return new Environment<E>(this);
    }
    
    public Environment<E> endScope(){
        return ancestor;
    }

}
