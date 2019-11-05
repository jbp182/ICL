package LanguageComponents.Envirements;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CompilerEnvirement {
    private String frameName;
    private CompilerEnvirement ancestor;
    private CompilerEnvirement predecessor;
    private Map<String, String> envMap;

    public CompilerEnvirement(CompilerEnvirement env) {
        this.frameName = IdGenerator.genFrameName();
        this.ancestor = env;
        envMap = new HashMap<>();
    }

    public CompilerEnvirement getAncestor() {
        return ancestor;
    }

    public CompilerEnvirement getPredecessor() {
        return predecessor;
    }

    public CompilerPair find(String id){
        String val = envMap.get(id);
        CompilerPair pair;

        if(val == null) {
            pair = ancestor.find(id);
            if(pair == null)
                throw new RuntimeException(); //TODO
            pair.offset++;
        }
        else{
            pair = new CompilerPair(envMap.get(id),frameName);
        }

        return pair;
    }

    public void assoc(String id, String node){
        if (envMap.putIfAbsent(id, node) != null)
            throw new RuntimeException();	//TODO
    }


    public CompilerEnvirement beginScope(CodeBlock blk){
        CompilerEnvirement env = new CompilerEnvirement(this);
        this.predecessor = env;
        genNewObject(blk,env);
        return env;
    }

    private void genNewObject(CodeBlock codeBlock, CompilerEnvirement env){
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

    public CompilerEnvirement endScope(CodeBlock blk){
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
