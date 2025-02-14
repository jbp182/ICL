package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTFunType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VClosure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



public class ASTFun implements ASTNode {

    private List<String> paramIds;
    private List<ASTType> paramTypes;
    private ASTFunType funType;
    private ASTNode body;
    
    public ASTFun(List<String> ids, List<ASTType> types, ASTNode body) {
        this.paramIds = ids;
        this.paramTypes = types;
        this.body = body;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
    	return new VClosure(paramIds,body,env);
	}

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
        List<String> genIds = new LinkedList<>();
        env = env.beginScope(codeBlock,false);
        for(String paramID : paramIds){
            String id = IdGenerator.genLabels();
            genIds.add(id);
            env.assoc(paramID,id);
        }
        String id = IdGenerator.genFunctionId();
        createClosure(codeBlock,id,env);
        codeBlock.createFunClass(id,body,funType,this.paramTypes,env,genIds);
        env = env.endScope(codeBlock,paramIds,paramTypes,false);

    }

    private void createClosure(CodeBlock codeBlock,String id,CompilerEnvironment env){
        codeBlock.emit("new " + id);
        codeBlock.emit("dup");
        codeBlock.emit("invokespecial "+id+"/<init>()V");
        codeBlock.emit("dup");
        codeBlock.emit("aload 9");
        if(env.toString().equals("f0"))
            codeBlock.emit("putfield "+id+"/sl Ljava/lang/Object;");
        else
        	codeBlock.emit("putfield "+id+"/sl L"+env.getAncestor()+";");
        codeBlock.buildFunInterfaceIfDoesNotExist(funType);
    }


    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
    	Environment<ASTType> newEnv = env.beginScope();
    	
		Iterator<String> itIds = paramIds.iterator();
		Iterator<ASTType> itTypes = paramTypes.iterator();
		
		while ( itIds.hasNext() && itTypes.hasNext() )
			newEnv.assoc(itIds.next(), itTypes.next());

		ASTType bodyType = body.typeCheck(newEnv);
		
		env = newEnv.endScope();
		this.funType = (ASTFunType)ASTFunType.getInstance(paramTypes, bodyType);
		return funType;
    }
}
