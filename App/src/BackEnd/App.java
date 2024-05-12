package BackEnd;
public class App {
    public static void main(String[] args){
        run();
    }

    
    private static void run() {
        FileHandling FileHandlingGeneral = new FileHandling("App/src/Data/input.txt", "App/src/Data/outputG.txt");
        FileHandling FileHandlingBinary = new FileHandling("App/src/Data/input.txt", "App/src/Data/outputB.txt");

        Tree tree = FileHandlingGeneral.loadTree();
        tree.buildBinary();
        
        tree.saveTreeToFile(FileHandlingGeneral, false);
        tree.saveTreeToFile(FileHandlingBinary, true);
        // tree.printGeneralTreeToConsole();
        // tree.printBinaryTreeToConsole();

        close(FileHandlingGeneral, FileHandlingBinary);        
    }
    
    private static void close(FileHandling FileHandlingGeneral, FileHandling FileHandlingBinary) {
        FileHandlingGeneral.close();
        FileHandlingBinary.close();
    }
}