package LanguageComponents.Types;

import java.util.Iterator;
import java.util.List;

public class ASTStructType extends CompostType {
	
	private List<ASTType> paramTypes;
	
	public ASTStructType(List<ASTType> param) {
		this.paramTypes = param;
	}
	
	public static ASTType getInstance(List<ASTType> param) {
		return new ASTStructType(param);
	}
	
	public List<ASTType> getParamTypes() {
		return paramTypes;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ASTStructType))
			return false;
		
		ASTStructType other = (ASTStructType) obj;
		if (paramTypes.size() != other.paramTypes.size())
			return false;
		else {
			Iterator<ASTType> it = this.paramTypes.iterator();
			Iterator<ASTType> itOther = other.paramTypes.iterator();
			while(it.hasNext() && itOther.hasNext())
				if (!it.next().equals(itOther.next()))
					return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("_");
		for(ASTType type : paramTypes){
			builder.append(type);
		}
		
		return builder.toString();
	}

}
