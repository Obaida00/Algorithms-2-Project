public class App {
    public static void main(String[] args){
        FileHandler fileHandler = new FileHandler("../Data/input.txt", "../Data/output.txt");

        Tree tree = fileHandler.loadTree();
        fileHandler.saveTree(tree);

        fileHandler.close();
    }

}