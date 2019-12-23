package LanguageComponents.Values;

import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTType;

import java.util.Iterator;
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof VClosure))
			return false;
		
		VClosure other = (VClosure)obj;
		if (param.size() != other.param.size())
			return false;
		else {
			Iterator<String> it = param.iterator();
			Iterator<String> itOther = other.param.iterator();
			while(it.hasNext() && itOther.hasNext()) {
				if (!it.next().equals(itOther.next()))
					return false;
			}
		}
		
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		
		return true;
	}

}
