package LanguageComponents.Nodes.ImperativeCore;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTFunType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VClosure;

import java.util.Iterator;
import java.util.List;



public class ASTFun implements ASTNode {

    private List<String> paramIds;
    private List<ASTType> paramTypes;
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
        //TODO
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
		
		return ASTFunType.getInstance(paramTypes, bodyType);
    }
}
