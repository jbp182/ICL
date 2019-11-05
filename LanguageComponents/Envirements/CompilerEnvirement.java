package LanguageComponents.Envirements;

import java.util.HashMap;
import java.util.Map;

public class CompilerEnvirement {
    private String frameName;
    public CompilerEnvirement ancestor;
    public Map<String, String> envMap;

    public CompilerEnvirement(CompilerEnvirement env) {
        this.frameName = IdGenerator.genFrameName();
        this.ancestor = env;
        envMap = new HashMap<>();
    }

    public CompilerEnvirement getAncestor() {
        return ancestor;
    }

    public CompilerPair find(String id){
        String val = envMap.get(id);
        CompilerPair pair = null;

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


    public CompilerEnvirement beginScope(){
        return new CompilerEnvirement(this);
    }

    public CompilerEnvirement endScope(CodeBlock blk){
        blk.emit("aload 4");
        if(frameName.equals("f0"))
            blk.emit("getfield "+frameName + "/sl Ljava/lang/Object;");
        else
            blk.emit("getfield "+frameName + "/sl L"+ancestor.frameName + ";");

        blk.emit("astore 4");
        return ancestor;
    }

    @Override
    public String toString() {
        return frameName;
    }
}
