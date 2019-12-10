package LanguageComponents.Values;

import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.InterpreterEnvironment;
import LanguageComponents.Nodes.ASTNode;

import java.util.List;
import java.util.stream.Collectors;

public class VClosure implements IValue {

	private List<ASTNode> param;
	private ASTNode body;

	private CompilerEnvironment compilerEnvironment;
	private InterpreterEnvironment<IValue> environment;

	private VClosure(List<ASTNode> param, ASTNode body) {
		this.param = param;
		this.body = body;
	}

	public VClosure(List<ASTNode> param, ASTNode body,CompilerEnvironment compilerEnvironment){
		this(param,body);
		this.compilerEnvironment = compilerEnvironment;
	}

	public VClosure(List<ASTNode> param, ASTNode body, InterpreterEnvironment<IValue> environment){
		this(param,body);
		this.environment = environment;
	}
	
	public List<ASTNode> getParam() {
		return param.stream().collect(Collectors.toList());
	}
	
	public ASTNode getBody() {
		return body;
	}
	
	public InterpreterEnvironment<IValue> getIntEnv() {
		return environment;
	}

	@Override
	public void show() {

	}

}
