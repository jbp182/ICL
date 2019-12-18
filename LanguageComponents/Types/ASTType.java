package LanguageComponents.Types;

import Exceptions.TypeError;

public abstract class ASTType {

	public static final String INT = "int";
	public static final String BOOL = "bool";
	public static final String REF = "ref";
	public static final String FUN = "fun";


	public static ASTType build(String type) {
		switch(type) {
			case INT:
				return ASTIntType.getInstance();
			case BOOL:
				return ASTBoolType.getInstance();
			case FUN:
				return ASTFunType.getInstance();
			default:
				throw new TypeError("No such type exception.");
		}
				
	}

	public static ASTType build(String type, ASTType subType) {
		switch(type) {
			case REF:
				return ASTRefType.getInstance(subType);
			default:
				return build(type);
		}

	}

}
