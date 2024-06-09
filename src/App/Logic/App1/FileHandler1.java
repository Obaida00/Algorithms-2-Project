package App.Logic.App1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class FileHandler1 {
    int RATIO_DIV = 1;
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
        try {
            line = reader.nextLine();
        } catch (Exception e) {
            System.out.println("no line to read from input file");
        }
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
    public Tree1 loadTree(String line) {
        TreeNode1 root = load(null, line);
        return new Tree1(root);
    }

    public Tree1 loadTree() {
        String line = this.readLine();
        TreeNode1 root = load(null, line);
        if (root != null)
            return new Tree1(root);
        return null;
    }

    private TreeNode1 load(TreeNode1 parent, String str) {
        if (str == null) {
            return null;
        }
        TreeNode1 root = null;
        TreeNode1 leftSubNode = null;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
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
                            leftSubNode = load(root, str.substring(i + 1, end));
                            i = end;
                            end = str.length() + 1;
                        }
                    }
                }
            }

            if (Character.isAlphabetic(c)) {
                if (leftSubNode == null) {
                    ArrayList<Integer> dim = getDimensions(str, i);
                    int width = dim.get(0) / RATIO_DIV;
                    int height = dim.get(1) / RATIO_DIV;

                    leftSubNode = new TreeNode1(root, c, width, height);
                }
            }

            if (c == '|' || c == '-') {
                root = new TreeNode1(null, c);
                if (leftSubNode != null){
                    leftSubNode.parent = root;
                    root.left = leftSubNode;
                }

                root.right = load(null, str.substring(i + 1));
                root.right.parent = root;
                return root;
            }
        }
        return leftSubNode;
    }

    private ArrayList<Integer> getDimensions(String str, int index) {
        ArrayList<Integer> dim = new ArrayList<>();
        int ln = str.length();

        int start;
        for (start = index; start < ln; start++) {
            int end = ln;
            char c = str.charAt(start);

            // sets the value of start to be the index of the first int
            // its okay to increment the start for the first time because I assigned index
            // to it, not index+1
            do {
                start++;
                c = str.charAt(start);
                if (Character.isAlphabetic(c)) {
                    System.out.println("WARINING: Dimensions are not specified.");
                }
            } while (!Character.isDigit(c) && start < end);

            // update end to get the end index for the current int kinda same as start but
            // inverted conditions
            end = start;
            do {
                end++;
                c = str.charAt(end);
            } while (Character.isDigit(c) && end < ln);

            // parse and add the current int
            // dim.add(Integer.parseInt(str, start, end, 0));
            dim.add(Integer.parseInt(str.substring(start, end)));

            if (dim.size() == 2)
                break;

        }

        if (dim.size() < 2) {
            System.out.println("WARINING: Reading less than 2 dimensions.");
        }
        return dim;

    }

    public void saveTreeToFileDrawFormatted(Tree1 tree) {
        if (tree.rootPaper == null) {
            tree.buildPaper();
        }
        var arr = tree.rootPaper;

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < tree.height; i++) {
            for (int j = 0; j < tree.width; j++) {
                if (arr[i][j] != null) {
                    str.append(arr[i][j]);
                } else {
                    str.append(" ");
                }
            }
            str.append('\n');
        }

        this.saveLine(str.toString());
    }

    public void saveTreeToFileLineFormatted(Tree1 tree) {
        StringBuilder line = new StringBuilder();
        saveTreeLineFormatted(tree.root, line);
        this.saveLine(line.toString());
    }

    private void saveTreeLineFormatted(TreeNode1 root, StringBuilder line) {
        if (root == null)
            return;
        if (!root.isPaper && !root.isRoot)
            line.append('(');
        // visit left
        saveTreeLineFormatted(root.left, line);

        // do root
        if (root.isPaper) {
            line.append(root.value);
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
        saveTreeLineFormatted(root.right, line);
        if (!root.isPaper && !root.isRoot)
            line.append(')');

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