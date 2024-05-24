package App2;

public class RunQ2 {
    public static void run() {
        FileHandler2 fileHandlerGeneral = new FileHandler2("App/src/Data/input1.txt", "App/src/Data/outputG2.txt");
        FileHandler2 fileHandlerBinary = new FileHandler2("App/src/Data/input1.txt", "App/src/Data/outputB2.txt");

        Tree2 tree = fileHandlerGeneral.loadTree();
        tree.buildBinary();
        
        tree.saveTreeToFile(fileHandlerGeneral, false);
        tree.saveTreeToFile(fileHandlerBinary, true);
        // tree.printGeneralTreeToConsole();
        // tree.printBinaryTreeToConsole();

        close(fileHandlerGeneral);        
        close(fileHandlerBinary);        
    }
    
    public static void close(FileHandler2 fh) {
        fh.close();
    }

    
}
