package App.Logic.App2;

public class RunQ2 {
    public static void run() {
        FileHandler2 fileHandlerGeneral = new FileHandler2("src/App/src/Data/input2.txt", "src/App/src/Data/outputG2.txt");
        FileHandler2 fileHandlerBinary = new FileHandler2("src/App/src/Data/input2.txt", "src/App/src/Data/outputB2.txt");

        Tree2 tree = fileHandlerGeneral.loadTree();
        tree.buildBinary();

        tree.saveTreeToFile(fileHandlerGeneral, false);
        tree.saveTreeToFile(fileHandlerBinary, true);
        tree.calculatePositions(0, 0, 10, 10, 5, true);
        // tree.printGeneralTreeToConsole();
        tree.printBinaryTreeToConsole();


        close(fileHandlerGeneral);
        close(fileHandlerBinary);
    }


    public static void close(FileHandler2 fh) {
        fh.close();
    }

}
