package App.Logic.App1;

import java.util.LinkedList;
import java.util.Queue;

public class RunQ1 {
    public static void run() {
        FileHandler1 fileHandler1 = new FileHandler1("src/App/Data/input1.txt", "src/App/Data/output1.txt");
        Tree1 tree = fileHandler1.loadTree();
        // tree.saveTreeToFile(fileHandler1, false);
        printTreeToConsole(tree);
        System.out.println(tree.width);
        System.out.println(tree.height);
        System.out.println();

        tree.buildPaper();
        tree.saveTreeToFile(fileHandler1, true);
        
        fileHandler1.close();
    
    
    }

    // for debugging perposes
    private static void printTreeToConsole(Tree1 tree) {
        printTreeToConsoleFormatted(tree.root);
        System.out.println();
        System.out.println();
        printTreeToConsoleStructured(tree.root);
    }

    private static void printTreeToConsoleFormatted(TreeNode1 root) {
        if (root == null)
            return;

        if (!root.isPaper && !root.isRoot)
            System.out.print('(');
        // visit left
        printTreeToConsoleFormatted(root.left);

        // do root
        if (root.isPaper) {
            System.out.print(root.value);
            System.out.print('[');
            System.out.print(root.height);
            System.out.print(',');
            System.out.print(root.width);
            System.out.print(']');
        } else {
            System.out.print(' ');
            System.out.print(root.value);
            System.out.print(' ');
        }
        // visit right
        printTreeToConsoleFormatted(root.right);
        if (!root.isPaper && !root.isRoot)
            System.out.print(')');
    }

    // not working
    private static void printTreeToConsoleStructured(TreeNode1 root) {
        Queue<TreeNode1> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            StringBuilder line = new StringBuilder();
            TreeNode1 currentNode = queue.remove();
            if (currentNode.left == null && currentNode.right == null)
                continue;

            line.append(currentNode.value + "[" + currentNode.height + "," + currentNode.width + "]" + " -> ");

            if (currentNode.left == null) {
                line.append("null,");
            } else {
                line.append(currentNode.left.value + "[" + currentNode.left.height + "," + currentNode.left.width + "]" + ", ");

                queue.add(currentNode.left);
            }

            if (currentNode.right == null) {
                line.append("null");
            } else {
                line.append(currentNode.right.value+ "[" + currentNode.right.height + "," + currentNode.right.width + "]");

                queue.add(currentNode.right);
            }

            System.out.println(line.toString());
        }
    }

}