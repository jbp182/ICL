package LanguageComponents.Nodes.ImperativeCore;

import java.util.Iterator;
import java.util.List;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VClosure;

public class ASTApply implements ASTNode {
	
	private ASTNode funName;		// id que tem la dentro o VClosure
	private List<ASTNode> args;
	
	public ASTApply(ASTNode funName, List<ASTNode> args) {
		this.funName = funName;
		this.args = args;
	}

	@Override
	public IValue eval(InterpreterEnvironment<IValue> env) {
		IValue f = funName.eval(env);
		if (f instanceof VClosure) {
			VClosure fun = (VClosure) f;
			InterpreterEnvironment<IValue> iEnv = fun.getIntEnv();
			List<ASTNode> param = fun.getParam();
			
			iEnv = iEnv.beginScope();
			Iterator<ASTNode> it_param = param.iterator();
			Iterator<ASTNode> it_args = args.iterator();
			ASTNode p, a;
			IValue v;
			while (it_param.hasNext() && it_args.hasNext()) {
				p = it_param.next();
				a = it_args.next();
				v = a.eval(env);
				iEnv.assoc(p.toString(), v);
			}
			
			v = fun.getBody().eval(iEnv);
			iEnv = iEnv.endScope();		// useless
			return v;
		}
		throw new TypeError("Not a function.");
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		// TODO Auto-generated method stub

	}

	@Override
	public ASTType typecheck(InterpreterEnvironment<ASTType> env) throws TypeError {
		// TODO Auto-generated method stub
		return null;
	}

}
