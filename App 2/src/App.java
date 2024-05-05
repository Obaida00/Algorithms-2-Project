public class App {
    public static void main(String[] args) throws Exception {
        FileHandler fileHandler = new FileHandler("data.txt", "output.txt");

        Tree tree = fileHandler.loadTree();
        fileHandler.saveTree(tree);

        fileHandler.close();
    }

}