package LanguageComponents.Environments;

import LanguageComponents.Nodes.ASTNode;
import LanguageComponents.Types.ASTFunType;
import LanguageComponents.Types.ASTRefType;
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

    private void genClassForRef(ASTType type) throws IOException {
        File f = new File("./target/"+type+".j");
        f.createNewFile();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
        out.flush();

        out.write(".class "+type+"\n");
        out.write(".super java/lang/Object\n");

        ASTType subtype = ((ASTRefType)type).getType();

        if(subtype instanceof ASTRefType){
            out.write(".field public v L"+subtype+";\n");
        }else{
            out.write(".field public v "+subtype+"\n");
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

    public void buildRefIfDoesNotExist(ASTType type) {
        //TODO if does not exist
        try {
            genClassForRef(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildFunInterfaceIfDoesNotExist(ASTFunType type){
        try {
            genClassForInterface(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void genClassForInterface(ASTFunType type) throws IOException {
        File f = new File("./target/"+type+".j");
        f.createNewFile();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
        out.flush();

        out.write(".interface "+type+"\n");

        StringBuilder builder = new StringBuilder();
        builder.append(".method (");
        Iterator<ASTType> it = type.getParamTypes().iterator();
        if(it.hasNext()){
            builder.append(it.next());
        }

        while(it.hasNext()){
            builder.append(";");
            builder.append(it.next());
        }

        builder.append(")"+type.getReturnType());

        out.write(builder.toString());
        out.write(".end");
        if(debug)
            System.out.println("Generated: " + f.getPath());
    }

    public void createFunClass(String id, ASTNode body,ASTFunType type,List<ASTType> typeList,CompilerEnvironment env){
        try {
            createFunClassWithException(id,body,type,typeList,env);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO
    private void createFunClassWithException(String id, ASTNode body, ASTFunType type ,List<ASTType> typeList,CompilerEnvironment env) throws IOException {
        File f = new File("./target/"+id+".j");
        f.createNewFile();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
        out.flush();

        out.write(".class " + id);
        out.write(".implements "+type);
        out.write(".field public SL "+env+";");
        out.write(".locals k + 1");

        StringBuilder builder = new StringBuilder();
        builder.append(".method call(");
        Iterator<ASTType> it = type.getParamTypes().iterator();
        if(it.hasNext()){
            builder.append(it.next());
        }
        while(it.hasNext()){
            builder.append(";");
            builder.append(it.next());
        }

        out.write(builder.toString());
        out.write("new "+id +"_frame");
        out.write("dup");

        int i = 0;
        for(ASTType t : typeList){
            out.write("aload "+i++);
            out.write("getfield " + id + "/SL "+t);
            out.write("putfield " + id +"_frame/sl" + t);
            out.write("dup");
        }

        out.write("astore SL");
        StringBuilder builderTmp = this.builder;
        this.builder = new StringBuilder();

        body.compile(env,this);

        out.write(this.builder.toString());

        this.builder = builderTmp;

        out.write("return");
        out.write(".end");
    }



    public static void runJava() throws Exception {
        runProcess("java -cp ./target Main");
    }

}
