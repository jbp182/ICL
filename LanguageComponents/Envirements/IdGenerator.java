package LanguageComponents.Envirements;

public class IdGenerator {
    static int variableCounter = 0;
    static int frameCounter = -1;
    public static String genFrameName() {
        return "f"+frameCounter++;
    }

    public static String genVariableName(){
        return "x"+variableCounter++;
    }
}
