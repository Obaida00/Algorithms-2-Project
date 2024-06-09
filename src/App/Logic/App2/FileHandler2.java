package App.Logic.App2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class FileHandler2 {
    String inputPath;
    String outputPath;
    File inputFile;
    File outputFile;
    BufferedReader reader;
    BufferedWriter writer;

    public FileHandler2(String inputPath, String outputPath) {
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

    // reads a single line from the input file and returns it as a string
    private String readLineToString() {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    // reads a single line from the input and returns it as an array containing ONLY
    // alphabiticals
    public ArrayList<Character> readLineToCharList() {
        String line = this.readLineToString();
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

    // appends a single line to the output file
    private void saveLine(String line) {
        try {
            writer.append(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // saves the General tree to the output file with the required formatting: root -> ch1,
    // ch2, ch3
    //
    // eg. A -> B, C, D
    // C -> E, G
    public boolean saveGeneralTreeToFile(GeneralNode2 root) {
        this.clear();
        Queue<GeneralNode2> queue = new LinkedList<>();
        if (root == null)
            return false;
        queue.add(root);

        while (!queue.isEmpty()) {
            StringBuilder line = new StringBuilder();
            GeneralNode2 current = queue.remove();
            if (!current.hasChild())
                continue;

            line.append(current.value).append(" -> ");

            boolean firstChild = true;
            int childCount = current.children.size();

            for (int i = 0; i < childCount; i++) {
                GeneralNode2 node = current.children.get(i);
                if (node == null)
                    continue;

                queue.add(node);
                if (!firstChild) {
                    line.append(", ");
                }
                line.append(node.value);
                firstChild = false;
            }
            saveLine(line.toString());
        }
        this.flush();
        return true;
    }

    private void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // saves the Binary tree to the output file with the required formatting: root -> ch1, ch2, ch3 and returns if the process is succeeded
    //
    // eg. A -> B, C, D
    // C -> E, G
    public boolean saveBinaryTreeToFile(BinaryNode2 root) {
        this.clear();
        Queue<BinaryNode2> queue = new LinkedList<>();
        if (root == null)
            return false;
        queue.add(root);

        while (!queue.isEmpty()) {
            StringBuilder line = new StringBuilder();
            BinaryNode2 current = queue.remove();
            if (current.left == null && current.right == null)
                continue;

            line.append(current.value).append(" -> ");

            if (current.left == null) {
                line.append("null,");
            } else {
                line.append(current.left.value).append(", ");
                queue.add(current.left);
            }

            if (current.right == null) {
                line.append("null");
            } else {
                line.append(current.right.value);
                queue.add(current.right);
            }
            saveLine(line.toString());
        }
        return true;
    }

    private void clear() {
        try {
            this.writer = new BufferedWriter(new FileWriter(this.outputFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // loads a tree form the input file with the correct formatting and converts it to a Tree instance
    public Tree2 loadTree() {
        this.resetReader();
        Tree2 tree = null;
        ArrayList<Character> line;
        while (!(line = this.readLineToCharList()).isEmpty()) {
            GeneralNode2 newNode = Tree2.convertCharListToNode(line);
            if (tree == null) {
                tree = new Tree2(newNode);// for now.. the nodes are given in order
            }
            tree.findGeneralNode(newNode).children = newNode.children;
        }
        return tree;
    }

    private void resetReader() {
        try {
            this.reader = new BufferedReader(new FileReader(this.inputFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void migrateOutput() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.outputFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.inputFile));

            //... Loop as long as there are input lines.
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();   // Write system dependent end of line.
            }

            //... Close reader and writer.
            bufferedReader.close();  // Close to unlock.
            bufferedWriter.close();  // Close to unlock and flush to disk.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // closes the opening reading/writing streams
    public void close() {
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}