package LanguageComponents.Values;

import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Nodes.ASTNode;

import java.util.List;

public class VClosure implements IValue {

	private List<ASTNode> param;
	private ASTNode body;

	private CompilerEnvironment compilerEnvironment;
	private InterpreterEnvironment environment;

	private VClosure(List<ASTNode> param, ASTNode body) {
		this.param = param;
		this.body = body;
	}

	public VClosure(List<ASTNode> param, ASTNode body,CompilerEnvironment compilerEnvironment){
		this(param,body);
		this.compilerEnvironment = compilerEnvironment;
	}

	public VClosure(List<ASTNode> param, ASTNode body, InterpreterEnvironment environment){
		this(param,body);
		this.environment = environment;
	}

	@Override
	public void show() {

	}

}
