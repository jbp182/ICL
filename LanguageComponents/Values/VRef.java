package LanguageComponents.Values;

public class VRef implements IValue {

    private IValue value;

    public VRef(IValue value) {
        this.value = value;
    }

    @Override
    public void show() {
        System.out.println();
    }

    public void set(IValue value){
        this.value = value;
    }

    public IValue get(){
        return value;
    }
}
