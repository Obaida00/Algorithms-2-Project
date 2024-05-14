package BackEnd.App2;

public class RunQ2 {
    public static void run() {
        FileHandling2 fileHandlerGeneral = new FileHandling2("App/src/Data/input1.txt", "App/src/Data/outputG.txt");
        FileHandling2 fileHandlerBinary = new FileHandling2("App/src/Data/input1.txt", "App/src/Data/outputB.txt");

        Tree2 tree = fileHandlerGeneral.loadTree();
        tree.buildBinary();
        
        tree.saveTreeToFile(fileHandlerGeneral, false);
        tree.saveTreeToFile(fileHandlerBinary, true);
        // tree.printGeneralTreeToConsole();
        // tree.printBinaryTreeToConsole();

        close(fileHandlerGeneral);        
        close(fileHandlerBinary);        
    }
    
    public static void close(FileHandling2 fh) {
        fh.close();
    }

    
}
