package LanguageComponents.Nodes.ArithmeticOperators;
import Exceptions.TypeError;
import LanguageComponents.Environments.IdGenerator;
import LanguageComponents.Nodes.ExtendedCore.ASTStruct;
import LanguageComponents.Types.ASTIntType;
import LanguageComponents.Types.ASTStructType;
import LanguageComponents.Types.ASTType;
import LanguageComponents.Types.CompostType;
import LanguageComponents.Values.IValue;
import LanguageComponents.Values.VInt;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Values.VStruct;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ASTPlus implements ASTNode {
	
	private ASTNode left;
	private ASTType leftType;

	private ASTNode right;
	private ASTType rightType;

	private ASTType resultType;

	public ASTPlus(ASTNode v1, ASTNode v2) {
		left = v1;
		right = v2;
	}
	
	public IValue eval(Environment<IValue> env) {
		IValue v1 = left.eval(env);
		if (v1 instanceof VInt) {
			IValue v2 = right.eval(env);
			if (v2 instanceof VInt)
				return new VInt( ((VInt)v1).getval() + ((VInt)v2).getval() );
		}
		else if (v1 instanceof VStruct){
			IValue v2 = right.eval(env);
			if(v2 instanceof VStruct){
				return ((VStruct)v1).sumStruct((VStruct)v2);
			}
		}
		throw new TypeError("illegal arguments to + operator");
	}

	@Override
	public void compile(CompilerEnvironment env, CodeBlock codeBlock) {
		if(leftType instanceof ASTIntType){
			compileInt(env, codeBlock);
		}else{
			compileStruct(env,codeBlock);
		}
	}

	private void compileInt(CompilerEnvironment env, CodeBlock codeBlock){
		left.compile(env,codeBlock);
		right.compile(env,codeBlock);
		codeBlock.emit("iadd");
	}

	private void compileStruct(CompilerEnvironment env, CodeBlock codeBlock){
		ASTStructType sumType = (ASTStructType)resultType;
		ASTStructType leftType = (ASTStructType)this.leftType;
		ASTStructType rightType = (ASTStructType)this.rightType;

		codeBlock.buildStruct(sumType,sumType.getStructTypes());

		codeBlock.emit("new " + sumType);
		codeBlock.emit("dup");
		codeBlock.emit("invokespecial "+sumType+"/<init>()V");
		codeBlock.emit("astore 8");

		left.compile(env,codeBlock);

		copyStruct(codeBlock,leftType,sumType);

		right.compile(env,codeBlock);

		copyStruct(codeBlock,rightType,sumType);

		codeBlock.emit("aload 8");
	}

	private void copyStruct(CodeBlock codeBlock, ASTStructType type, ASTType sumType){
		for(Map.Entry<String,ASTType> e : type.getStructTypes().entrySet()){
			codeBlock.emit("dup");

			if(e.getValue() instanceof CompostType){
				codeBlock.emit("getfield "+ type+"/"+e.getKey()+" L"+e.getValue()+";");
			} else{
				codeBlock.emit("getfield "+ type+"/"+e.getKey()+" "+e.getValue());
			}

			codeBlock.emit("aload 8");
			codeBlock.emit("swap");

			if(e.getValue() instanceof CompostType){
				codeBlock.emit("putfield "+ sumType+"/"+e.getKey()+" L"+e.getValue()+";");
			} else{
				codeBlock.emit("putfield "+ sumType+"/"+e.getKey()+" "+e.getValue());
			}
		}
		codeBlock.emit("pop");
	}

	@Override
	public ASTType typeCheck(Environment<ASTType> env) throws TypeError {
		leftType = left.typeCheck(env);
		rightType = right.typeCheck(env);

		if( leftType instanceof ASTIntType 
				&& rightType instanceof ASTIntType ){

			resultType = leftType;

			return leftType;
		}
		else if(leftType instanceof ASTStructType && rightType instanceof ASTStructType){
			List<ASTType> param = new LinkedList<>();
			ASTStructType structType1 = (ASTStructType)leftType;
			ASTStructType structType2 = (ASTStructType)rightType;
			param.addAll(structType1.getParamTypes());
			param.addAll(structType2.getParamTypes());
			Map<String,ASTType> structTypes = new HashMap<>();

			structTypes.putAll(structType1.getStructTypes());
			structTypes.putAll(structType2.getStructTypes());

			resultType = new ASTStructType(param, IdGenerator.genStructId(),structTypes);

			return resultType;
		}

		
		throw new TypeError("Cannot add different types.");
	}
}
