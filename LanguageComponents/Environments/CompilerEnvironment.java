package LanguageComponents.Environments;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Exceptions.IdAlreadyExistsException;
import Exceptions.NoSuchIdException;

public class CompilerEnvironment {
    private String frameName;
    private CompilerEnvironment ancestor;
    private CompilerEnvironment predecessor;
    private Map<String, String> envMap;

    public CompilerEnvironment(CompilerEnvironment env) {
        this.frameName = IdGenerator.genFrameName();
        this.ancestor = env;
        envMap = new HashMap<>();
    }

    public CompilerEnvironment getAncestor() {
        return ancestor;
    }

    public CompilerEnvironment getPredecessor() {
        return predecessor;
    }

    public CompilerPair find(String id){
        String val = envMap.get(id);
        CompilerPair pair;

        if(val == null) {
            if(ancestor == null)
                throw new NoSuchIdException("Error: There is no such id ( " + id + " ).");
            pair = ancestor.find(id);
            if(pair == null)
                throw new NoSuchIdException("Error: There is no such id ( " + id + " ).");
            pair.offset++;
        }
        else{
            pair = new CompilerPair(envMap.get(id),frameName);
        }

        return pair;
    }

    public void assoc(String id, String node){
    	try {
    		if (ancestor != null && envMap.get(id) != null)
        		throw new IdAlreadyExistsException("Error: Id " + id + " already exists.");
    	} catch(NoSuchIdException e) {}	
    	
    	envMap.put(id, node);
    }


    public CompilerEnvironment beginScope(CodeBlock blk){
        CompilerEnvironment env = new CompilerEnvironment(this);
        this.predecessor = env;
        genNewObject(blk,env);
        return env;
    }

    private void genNewObject(CodeBlock codeBlock, CompilerEnvironment env){
        codeBlock.emit("new "+env);
        codeBlock.emit("dup");
        codeBlock.emit("invokespecial "+env+"/<init>()V");
        codeBlock.emit("dup");
        codeBlock.emit("aload 4");


        if(env.toString().equals("f0"))
            codeBlock.emit("putfield "+  env+"/sl Ljava/lang/Object;");
        else
            codeBlock.emit("putfield "+  env+"/sl L"+ env.ancestor + ";");

        codeBlock.emit("astore 4");

    }

    public CompilerEnvironment endScope(CodeBlock blk){
        blk.genClass(this,new HashSet<>(this.envMap.values()));

        blk.emit("aload 4");
        if(frameName.equals("f0"))
            blk.emit("getfield "+frameName + "/sl Ljava/lang/Object;");
        else
            blk.emit("getfield "+frameName + "/sl L"+ancestor.frameName + ";");

        blk.emit("astore 4");
        ancestor.predecessor = null;
        return ancestor;
    }

    @Override
    public String toString() {
        return frameName;
    }
}
