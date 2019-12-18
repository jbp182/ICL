package LanguageComponents.Nodes.ImperativeCore;

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


public class ASTIf implements ASTNode {

	private static final String TYPE_ERROR_MESSAGE = "Conditional expression must return true or false.";
	private static final String IF_BODY_S_MUST_BE_SOMETYPE = "Both if body's must be sometype";


	private ASTNode cond;
    private ASTType condType;

    private ASTNode body1;
    private ASTType bodyType1;

    private ASTNode body2;
	private ASTType bodyType2;


    public ASTIf(ASTNode cond, ASTNode body1, ASTNode body2) {
        this.cond = cond;
        this.body1 = body1;
        this.body2 = body2;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
    	IValue bool = cond.eval(env);
    	if (bool instanceof VBool) {
    		if ( ((VBool)bool).isTrue() )
    			return body1.eval(env);
    		else
    			return body2.eval(env);
    	}
        throw new TypeError("Conditional expression must return true or false.");
    }

    @Override
    public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
    	cond.compile(env, codeBlock);

    	String l1 = IdGenerator.genLabels();
    	String l2 = IdGenerator.genLabels();
    	codeBlock.emit("ifeq "+l1);
    	body1.compile(env, codeBlock);
    	codeBlock.emit("goto "+l2);
    	codeBlock.emit(l1+":");
    	body2.compile(env, codeBlock);
    	codeBlock.emit(l2+":");
    }

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
    	condType = cond.typeCheck(env);

    	if(!(condType instanceof ASTBoolType)){
			throw new TypeError(TYPE_ERROR_MESSAGE);
		}

    	bodyType1 = body1.typeCheck(env);
    	bodyType2 = body2.typeCheck(env);

    	if(!bodyType1.equals(bodyType2)){
    		throw new TypeError(IF_BODY_S_MUST_BE_SOMETYPE);
		}

		return bodyType1;
	}
}
