import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FileHandler {
    String inputPath;
    String outputPath;
    File inputFile;
    File outputFile;
    BufferedReader reader;
    BufferedWriter writer;

    FileHandler(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        inputFile = new File(inputPath);
        outputFile = new File(outputPath);
        
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //reads a single line from the input file and returns it as a string
    private String readLineToStirng() {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    //reads a single line from the input and returns it as an array containing ONLY alphabiticals
    public ArrayList<Character> readLineToCharList() {
        String line = this.readLineToStirng();
        ArrayList<Character> charList = new ArrayList<>();

        if (line != null) {
            for (char c : line.toCharArray()) {
                if (c == '\n')
                    break;
                if (Character.isLetter(c))
                    charList.add(c);
            }
        }
        return charList;
    }

    //appends a single line to the output file
    private void saveLine(String line){
        try {
            writer.append(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //saves the tree to the output file with the required formatting: root -> ch1, ch2, ch3
    //
    //eg. A -> B, C, D
    //    C -> E, G
    public void saveTree(Tree tree) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree.getRoot());

        while (!queue.isEmpty()) {
            StringBuilder line = new StringBuilder();
            TreeNode currentNode = queue.remove();
            if (currentNode.getChildren().isEmpty())
                continue;
            line.append(currentNode.getValue() + " -> ");

            boolean firstChild = true;
            for (TreeNode node : currentNode.getChildren()) {
                queue.add(node);
                if (!firstChild)
                    line.append(", ");
                    line.append(node.getValue());
                firstChild = false;
            }
            saveLine(line.toString());
        }
    }

    //loads a tree form the input file with the correct formatting and converts it to a Tree instence
    public Tree loadTree(){
        Tree tree = null;
        ArrayList<Character> line;
        while (!(line = this.readLineToCharList()).isEmpty()){
            TreeNode newNode = TreeNode.convertCharListToNode(line);
            if(tree==null)
                tree = new Tree(newNode);//for now.. the nodes are given in order
            
            tree.findNode(newNode).setNode(newNode);
        }
        return tree;
    }

    //closes the opening reading/writing streams
    public void close(){
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}