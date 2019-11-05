package LanguageComponents.Envirements;

public class CompilerPair {

    public int offset;
    public String variableId;
    public String frameId;

    public CompilerPair(String variableId,String frameId) {
        this.offset = 0;
        this.variableId = variableId;
        this.frameId = frameId;
    }
}
