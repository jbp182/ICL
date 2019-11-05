package LanguageComponents.Values;

public class VStr implements IValue {
	
	String value;
	
	public VStr(String s) {
		value = s;
	}
	

	@Override
	public void show() {
		System.out.println(value);
	}

}
