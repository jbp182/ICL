package LanguageComponents.Values;

public class VBool implements IValue {

    private boolean value;

    public VBool(boolean value) {
        this.value = value;
    }

    @Override
    public void show() {
        System.out.println(value);
    }

    public boolean isTrue(){
        return this.value;
    }
    
}
