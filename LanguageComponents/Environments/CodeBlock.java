package LanguageComponents.Environments;

import LanguageComponents.Types.ASTType;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CodeBlock {
    private StringBuilder builder;
    public static boolean debug = true;

    public CodeBlock() {
        this.builder = new StringBuilder();
        createTargetDir();
    }


    public void emit(String instruction){
        builder.append("\t"+instruction+"\n");
    }

    void createFileWithInstructions(String instruction,String fileName) throws IOException {
        File f = new File(fileName);
        f.createNewFile();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
        out.write(instruction);
        out.flush();
        out.close();
    }

    public void genClass(CompilerEnvironment env, List<String> ids, List<ASTType> types) {
        try {
            genClassWithException(env,ids,types);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void genClassWithException(CompilerEnvironment env, List<String> ids, List<ASTType> types) throws IOException {
        File f = new File("./target/"+ env + ".j");
        f.createNewFile();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
        out.flush();

        out.write(".class " + env + "\n");
        out.write(".super java/lang/Object\n");

        if(env.toString().equals("f0"))
            out.write(".field public sl Ljava/lang/Object;\n");
        else
            out.write(".field public sl L"+env.getAncestor() + ";\n");

        Iterator<String> idsIt = ids.iterator();
        Iterator<ASTType> typesIt = types.iterator();

        while(idsIt.hasNext() && typesIt.hasNext()){
            String id = idsIt.next();
            ASTType astType = typesIt.next();

            if(isRef(id)){
                out.write(".field public " +id+" L"+astType+";\n");
            }else{
                out.write(".field public " +id+" I\n");
            }
        }

        out.write(".method public <init>()V\n");
        out.write("\taload_0\n");
        out.write("\tinvokenonvirtual java/lang/Object/<init>()V\n");
        out.write("\treturn\n");
        out.write(".end method\n");
        out.flush();
        out.close();

        if(debug)
            System.out.println("Generated: " + f.getPath());
    }

    private boolean isRef(String id){
        return id.charAt(0) == 'r';
    }

    private void genClassForRef(String name, String subtype) throws IOException {
        File f = new File("./target/"+subtype+".j");
        f.createNewFile();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
        out.flush();


        out.write(".class "+name+"\n");
        out.write(".super java/lang/Object\n");

        out.write(".field public v "+subtype+"\n");


        out.write(".method public <init>()V\n");
        out.write("\taload_0\n");
        out.write("\tinvokenonvirtual java/lang/Object/<init>()V\n");
        out.write("\treturn\n");
        out.write(".end method\n");
        out.flush();
        out.close();

        if(debug)
            System.out.println("Generated: " + f.getPath());
    }

    public void dump(String fileName) throws IOException {
        File f = new File("./target/" + fileName);
        f.createNewFile();

        createJFile(f);
        compileJ();
    }

    private void createTargetDir(){
        File f = new File("./target/");
        f.mkdirs();
        for(File file: f.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    private void createJFile(File f){
        try {
            createJFileWithException(f);
        } catch (IOException e) {
            System.err.println("Error creating J File");
            e.printStackTrace();
        }
    }

    private void createJFileWithException(File f) throws IOException {
        BufferedReader inInit = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./src/ResourceFiles/init.j"))));
        BufferedReader inEnd = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./src/ResourceFiles/end.j"))));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
        //Dump init.j
        String line;
        while (( line = inInit.readLine()) != null){
            out.write(line+"\n");
        }
        out.write(builder.toString());
        while (( line = inEnd.readLine()) != null){
            out.write(line+"\n");
        }
        out.flush();
        
        inInit.close(); inEnd.close(); out.close();

        if(debug){
            System.out.println("Generated: " + f.getPath());
        }
    }

    private void compileJ(){
        try {
            for (final File fileEntry : (new File("./target")).listFiles()) {
                String comm = "java -jar ./src/ResourceFiles/jasmin.jar -d ./target ./target/"+fileEntry.getName();
                if(debug)
                    runProcess(comm);
                else
                    runProcessWithoutOutput(comm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(pro.getInputStream());
        printLines(pro.getErrorStream());
        pro.waitFor();
    }

    private static void runProcessWithoutOutput(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        pro.waitFor();
    }

    private static void printLines(InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }

    public String toString(){
        return builder.toString();
    }

    public void buildRefIfDoesNotExist(String name) {
        try {
            genClassForRef(name,name.replaceFirst("ref_",""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runJava() throws Exception {
        runProcess("java -cp ./target Main");
    }

}
