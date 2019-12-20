package LanguageComponents.Types;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ASTStructType extends CompostType {

	private List<ASTType> paramTypes;
	private String id;
	private Map<String,ASTType> structTypes;

	public ASTStructType(List<ASTType> param,String id,Map<String,ASTType> structTypes) {
		this.paramTypes = param;
		this.id = id;
		this.structTypes = structTypes;
	}

	public ASTStructType(List<ASTType> param) {
		this.paramTypes = param;
		this.id = null;
	}

	public static ASTType getInstance(List<ASTType> param, String id, Map<String,ASTType> structTypes) {
		return new ASTStructType(param,id,structTypes);
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
		return id;
	}

	public ASTType getIdType(String id) {
		return this.structTypes.get(id);
	}
}
