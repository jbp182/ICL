package LanguageComponents.Nodes.ImperativeCore;

import java.util.Iterator;
import java.util.List;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTFunType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VClosure;

public class ASTApply implements ASTNode {
	
	private ASTNode funName;
	private ASTType funType;

	private List<ASTNode> args;
	
	public ASTApply(ASTNode funName, List<ASTNode> args) {
		this.funName = funName;
		this.args = args;
	}

	@Override
	public IValue eval(Environment<IValue> env) {
		IValue f = funName.eval(env);
		if (f instanceof VClosure) {
			VClosure fun = (VClosure) f;
			Environment<IValue> iEnv = fun.getIntEnv();
			List<String> param = fun.getParam();
			
			iEnv = iEnv.beginScope();
			Iterator<String> itParam = param.iterator();
			Iterator<ASTNode> itArgs = args.iterator();
			IValue v;
			while (itParam.hasNext() && itArgs.hasNext()) {
				iEnv.assoc(itParam.next(), itArgs.next().eval(env));
			}
			
			v = fun.getBody().eval(iEnv);
			iEnv = iEnv.endScope();		// useless
			return v;
		}
		throw new TypeError("Not a function.");
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		ASTFunType fun = ((ASTFunType)funType);
		funName.compile(env,codeBlock);

		codeBlock.emit("checkcast "+funType);

		for(ASTNode node : args){
			node.compile(env,codeBlock);
		}


		StringBuilder builder = new StringBuilder();
		builder.append("/call(");
		Iterator<ASTType> it = fun.getParamTypes().iterator();
		if(it.hasNext()){
			builder.append(it.next());
		}
		while(it.hasNext()){
			builder.append(";");
			builder.append(it.next());
		}

		builder.append(")"+ fun.getReturnType() + " " + (fun.getParamTypes().size() + 1));

		codeBlock.emit("invokeinterface "+ funType + builder.toString());
	}

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		funType = funName.typeCheck(env);
		if (funType instanceof ASTFunType) {
			ASTFunType t = (ASTFunType) funType;
			
			env = env.beginScope();
			
			Iterator<ASTType> itTypes = t.getParamTypes().iterator();
			Iterator<ASTNode> itArgs = args.iterator();
			ASTType type;
			ASTNode arg;
			while( itTypes.hasNext() && itArgs.hasNext() ) {
				type = itTypes.next();
				arg = itArgs.next();
				if ( !type.equals(arg.typeCheck(env)) )
					throw new TypeError("Arguments types do not match with previously declared types.");
				
				return t.getReturnType();
			}			
		}
		throw new TypeError("Not a function.");
		
		
	}

}
