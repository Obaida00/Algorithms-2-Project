package App1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class FileHandler1 {
    String inputPath;
    String outputPath;
    File inputFile;
    File outputFile;
    // BufferedReader reader;
    Scanner reader;
    BufferedWriter writer;

    public FileHandler1(String inputPath, String outputPath) {
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
            reader = new Scanner(this.inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // reads a single line from the input file and returns it as a string
    private String readLine() {
        String line = null;
        line = reader.nextLine();
        return line;
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

    // loads a tree form the input file with the correct formatting and converts it
    // to a Tree instence
    public Tree1 loadTree() {
        String line = this.readLine();
        var root = load(line);
        Tree1 tree = new Tree1(root);

        // for (int i = 0; i < line.length(); i++) {
        // if(line.charAt(i) == '|' || line.charAt(i) == '-'){

        // // this.operandType1(line.substring(0, i), line.substring(i+1));
        // }

        // }

        return tree;
    }

    TreeNode1 load(String str) {
        TreeNode1 root = null;
        TreeNode1 leftSubNode = null;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                // recursive call for each section
                Stack<Integer> stack = new Stack<>();
                stack.push(1);

                for (int end = i + 1; end < str.length(); end++) {
                    char endc = str.charAt(end);
                    if (endc == '(') {
                        stack.push(1);
                    }
                    if (endc == ')') {
                        stack.pop();
                        if (stack.isEmpty()) {
                            leftSubNode = load(str.substring(i + 1, end));// shouldnt return
                            i = end;
                            end = str.length() + 1;
                        }
                    }
                }
            }

            if (Character.isAlphabetic(c)) {
                if (leftSubNode == null) {
                    leftSubNode = new TreeNode1(c, 10, 10);
                }
            }

            if (c == '|' || c == '-') {
                root = new TreeNode1(c);
                if (leftSubNode != null)
                    root.left = leftSubNode;
                root.right = load(str.substring(i + 1));
                return root;
            }
        }
        return leftSubNode;
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

    public void saveTree(Tree1 tree) {
        StringBuilder line = new StringBuilder();
        saveTree(tree.root, line);
        this.saveLine(line.toString());
    }

    private void saveTree(TreeNode1 root, StringBuilder line) {
        if (root == null)
            return;
        if (!root.isRectangle && !root.isRoot)
            line.append('(');
        // visit left
        saveTree(root.left, line);

        // do root
        if (root.isRectangle) {
            line.append(root.name);
            line.append('[');
            line.append(root.width);
            line.append(',');
            line.append(root.height);
            line.append(']');
        } else {
            line.append(' ');
            line.append(root.value);
            line.append(' ');
        }
        // visit right
        saveTree(root.right, line);
        if (!root.isRectangle && !root.isRoot)
            line.append(')');

    }

}