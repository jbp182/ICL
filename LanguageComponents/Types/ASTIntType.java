package LanguageComponents.Types;

public class ASTIntType extends ASTType {

	private static ASTType singleton;
	
	public static ASTType getInstance() {
		if(singleton == null)
			singleton = new ASTIntType();
		
		return singleton;
	}

	@Override
	public String toString() {
		return "I";
	}
}
