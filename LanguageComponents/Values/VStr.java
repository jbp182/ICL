package LanguageComponents.Values;

public class VStr implements IValue {
	
	String value;
	
	public VStr(String s) {
		value = s;
	}
	
	public String getval() {
		return value;
	}

	@Override
	public void show() {
		System.out.println(value);
	}

}
