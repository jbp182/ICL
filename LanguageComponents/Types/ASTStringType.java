package LanguageComponents.Types;

public class ASTStringType extends CompostType {
	private static ASTType singleton;
	
	public static ASTType getInstance() {
		if (singleton == null)
			singleton = new ASTStringType();
		
		return singleton;
	}
	
	@Override
	public String toString() {
		return "java/lang/String";
	}

}
