package LanguageComponents.Environments;

public class IdGenerator {
    static int variableCounter = 0;
    static int frameCounter = -1;
    static int labelCounter = 1;
    static int refCounter = 0;
    static int functionCounter = 0;
    static int structCounter = 0;

    public static String genFrameName() {
        return "f"+frameCounter++;
    }

    public static String genVariableName(){
        return "x"+variableCounter++;
    }

    public static String genRefName(){
        return "r"+refCounter++;
    }

    public static String genLabels(){
        return "L"+labelCounter++;
    }

    public static String genFunctionId(){
        return "closure_f"+functionCounter++;
    }

    public static String genStructId() { return "struct" + structCounter++;}
}
