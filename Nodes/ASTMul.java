package Nodes;
import Exceptions.TypeError;
import Values.IValue;
import Values.VInt;

import java.util.LinkedList;
import java.util.List;

public class ASTMul implements ASTNode {

	ASTNode left;
	ASTNode right;
	
	public ASTMul(ASTNode v1, ASTNode v2) {
		left = v1;
		right = v2;
	}
	
	public IValue eval(Environment env) {
		IValue v1 = left.eval(env);
		if (v1 instanceof VInt) {
			IValue v2 = right.eval(env);
			if (v2 instanceof VInt)
				return new VInt( ((VInt)v1).getval() * ((VInt)v2).getval() );
		}
		throw new TypeError("illegal arguments to + operator");
	}

	@Override
	public List<String> compile(Environment env) {
		List<String> leftC = left.compile(env);
		List<String> rightC = right.compile(env);
		leftC.addAll(rightC);
		leftC.add("imul");
		return leftC;
	}

}
