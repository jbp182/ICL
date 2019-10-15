package Nodes;
import Values.IValue;
import Values.VInt;

public class ASTNum implements ASTNode {
	
	IValue value;
	
	public ASTNum(int v) {
		value = new VInt(v);
	}

	@Override
	public IValue eval(Environment env) {
		return value;
	}

}
