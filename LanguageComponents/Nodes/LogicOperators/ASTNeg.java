package LanguageComponents.Nodes.LogicOperators;

import Exceptions.TypeError;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTBoolType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VBool;

public class ASTNeg implements ASTNode {

    private ASTNode node;
    private ASTType type;

    public ASTNeg(ASTNode node) {
        this.node = node;
        this.type = null;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
    	IValue bool = node.eval(env);
    	if (bool instanceof VBool)
    		return new VBool( !((VBool)bool).isTrue() );
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
        String l1 = IdGenerator.genLabels();
        String l2 = IdGenerator.genLabels();
        
    	node.compile(env, codeBlock);
    	codeBlock.emit("ifeq "+l1);
    	codeBlock.emit("sipush 0");
    	codeBlock.emit("goto "+l2);
    	codeBlock.emit(l1+":");
    	codeBlock.emit("sipush 1");
    	codeBlock.emit(l2 + ":");
    }

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		type = node.typeCheck(env);
		if (type instanceof ASTBoolType)
			return ASTBoolType.getInstance();
		throw new TypeError("Type mismatch. Expected boolean.");
	}
}
