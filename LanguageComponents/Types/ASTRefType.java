package LanguageComponents.Types;

import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ASTRefType that = (ASTRefType) o;
		return Objects.equals(type, that.type);
	}

	@Override
	public String toString() {
		return "ref_"+type;
	}
}
