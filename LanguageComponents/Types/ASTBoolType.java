package LanguageComponents.Types;

public class ASTBoolType implements ASTType {
	private static ASTType singleton;
	
	public static ASTType getInstance() {
		if(singleton == null)
			singleton = new ASTBoolType();
		
		return singleton;
	}

	@Override
	public String toString() {
		return "I";
	}
}
