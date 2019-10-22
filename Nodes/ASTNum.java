package Nodes;
import Values.IValue;
import Values.VInt;

import java.util.LinkedList;
import java.util.List;

public class ASTNum implements ASTNode {
	
	IValue value;
	
	public ASTNum(int v) {
		value = new VInt(v);
	}

	@Override
	public IValue eval(Environment env) {
		return value;
	}

	@Override
	public List<String> compile(Environment env) {
		List<String> stack = new LinkedList<>();
		stack.add("sipush "+ ((VInt)value).getval());
		return stack;
	}

}
