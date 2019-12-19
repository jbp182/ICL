package LanguageComponents.Types;

import java.util.Iterator;
import java.util.List;

public class ASTFunType extends CompostType {
	
	private List<ASTType> paramTypes;
	private ASTType returnType;
	
	public ASTFunType(List<ASTType> paramTypes,ASTType returnType) {
		this.paramTypes = paramTypes;
		this.returnType = returnType;
	}
	
	public static ASTType getInstance(List<ASTType> paramTypes,ASTType returnType) {
		return new ASTFunType(paramTypes, returnType);
	}
	
	public List<ASTType> getParamTypes() {
		return paramTypes;
	}
	
	public ASTType getReturnType() {
		return returnType;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ASTFunType))
			return false;
		
		ASTFunType other = (ASTFunType) obj;
		if (paramTypes.size() != other.paramTypes.size()) {
			return false;
		} else {
			Iterator<ASTType> it = paramTypes.iterator();
			Iterator<ASTType> itOther = other.paramTypes.iterator();
			while(it.hasNext() && itOther.hasNext()) {
				if (!it.next().equals(itOther.next()))
					return false;
			}
		}
		
		if (returnType == null) {
			if (other.returnType != null)
				return false;
		} else if (!returnType.equals(other.returnType))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for(ASTType type : paramTypes){
			builder.append(type);
		}

		builder.append("_"+this.returnType);

		return builder.toString();
	}
}
