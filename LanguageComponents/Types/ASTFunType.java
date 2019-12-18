package LanguageComponents.Types;

import java.util.List;

public class ASTFunType extends ASTType {
private static ASTType singleton;
	
	public static ASTType getInstance(List<ASTType> paramTypes,ASTType returnType) {
		if(singleton == null)
			singleton = new ASTFunType();
		
		return singleton;
	}

	//TODO
}
