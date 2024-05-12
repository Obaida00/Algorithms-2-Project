package App2;

public class runQ1 {
    public static void run() {
        FileHandler fileHandlerGeneral = new FileHandler("App/src/Data/input1.txt", "App/src/Data/outputG.txt");
        FileHandler fileHandlerBinary = new FileHandler("App/src/Data/input1.txt", "App/src/Data/outputB.txt");

        Tree tree = fileHandlerGeneral.loadTree();
        tree.buildBinary();
        
        tree.saveTreeToFile(fileHandlerGeneral, false);
        tree.saveTreeToFile(fileHandlerBinary, true);
        // tree.printGeneralTreeToConsole();
        // tree.printBinaryTreeToConsole();

        close(fileHandlerGeneral);        
        close(fileHandlerBinary);        
    }
    
    public static void close(FileHandler fh) {
        fh.close();
    }
}
