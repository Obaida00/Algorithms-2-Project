public class App {
    public static void main(String[] args){
        run();
    }

    
    private static void run() {
        FileHandler fileHandlerGeneral = new FileHandler("App/src/Data/input.txt", "App/src/Data/outputG.txt");
        FileHandler fileHandlerBinary = new FileHandler("App/src/Data/input.txt", "App/src/Data/outputB.txt");

        Tree tree = fileHandlerGeneral.loadTree();
        tree.buildBinary();
        tree.generalRoot = null;
        
        tree.buildGeneral();
        tree.saveTreeToFile(fileHandlerGeneral, false);
        tree.saveTreeToFile(fileHandlerBinary, true);
        // tree.printGeneralTreeToConsole();
        // tree.printBinaryTreeToConsole();

        close(fileHandlerGeneral, fileHandlerBinary);        
    }
    
    private static void close(FileHandler fileHandlerGeneral, FileHandler fileHandlerBinary) {
        fileHandlerGeneral.close();
        fileHandlerBinary.close();
    }
}