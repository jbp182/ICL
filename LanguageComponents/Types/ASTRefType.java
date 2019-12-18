package LanguageComponents.Types;


public class ASTRefType extends ASTType {
	
	private ASTType type;
	
	public ASTRefType(ASTType type) {
		this.type = type;
	}
	
	public static ASTType getInstance(ASTType type) {
		return new ASTRefType(type);
	}

	public ASTType getType() {
		return type;
	}

	public boolean checkTypeReference(ASTType type){
		return this.type.equals(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if ( !(obj instanceof ASTRefType))
			return false;
		
		ASTRefType other = (ASTRefType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "ref_"+type;
	}
}
