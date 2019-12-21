package LanguageComponents.Values;

import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;

import java.util.List;
import java.util.stream.Collectors;

public class VClosure implements IValue {

	private List<String> param;
	private ASTNode body;

	private CompilerEnvironment compilerEnvironment;
	private Environment<IValue> environment;

	private VClosure(List<String> param, ASTNode body) {
		this.param = param;
		this.body = body;
	}

	public VClosure(List<String> param, ASTNode body,CompilerEnvironment compilerEnvironment){
		this(param,body);
		this.compilerEnvironment = compilerEnvironment;
	}

	public VClosure(List<String> param, ASTNode body, Environment<IValue> environment){
		this(param,body);
		this.environment = environment;
	}
	
	public List<String> getParam() {
		return param.stream().collect(Collectors.toList());
	}
	
	public ASTNode getBody() {
		return body;
	}
	
	public Environment<IValue> getIntEnv() {
		return environment;
	}

	@Override
	public void show() {
		StringBuilder builder = new StringBuilder();

		builder.append("fun(");

		for(String param : this.param){
			builder.append(" "+param);
		}

		builder.append(")");

		System.out.println(builder.toString());
	}

}
