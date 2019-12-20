package LanguageComponents.Values;


import java.util.Map;

//TODO
public class VStruct implements IValue {

    private Map<String, IValue> structAsMap;

    public VStruct(Map<String, IValue> structAsMap) {
        this.structAsMap = structAsMap;
    }

    @Override
    public void show() {
        StringBuilder builder = new StringBuilder();

        builder.append("{");

        for(Map.Entry<String,IValue> e : structAsMap.entrySet()){
            builder.append(e.getKey() + ": ");
            builder.append(e.getValue() + " ");
        }

        builder.append("}");
    }

    public IValue getValue(String id) {
        return structAsMap.get(id);
    }

}
