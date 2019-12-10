package LanguageComponents.Types;

public class ASTRefType extends ASTType {
	
	private ASTType type;
	
	public ASTRefType(ASTType type) {
		this.type = type;
	}
	
	public static ASTType getInstance(ASTType type) {
		return new ASTRefType(type);
	}
}
