package LanguageComponents.Types;

public class ASTFunType extends ASTType {
private static ASTType singleton;
	
	public static ASTType getInstance() {
		if(singleton == null)
			singleton = new ASTFunType();
		
		return singleton;
	}
}
