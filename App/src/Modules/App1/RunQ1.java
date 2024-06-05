package App1;

import java.util.LinkedList;
import java.util.Queue;

public class RunQ1 {
    public static void run() {
        FileHandler1 fileHandler1 = new FileHandler1("App/src/Data/input1.txt", "App/src/Data/output1.txt");
        Tree1 tree = fileHandler1.loadTree();
        tree.saveTreeToFile(fileHandler1, false);
        printTreeToConsole(tree);
        System.out.println(tree.width);
        System.out.println(tree.height);

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

        if (!root.isRectangle && !root.isRoot)
            System.out.print('(');
        // visit left
        printTreeToConsoleFormatted(root.left);

        // do root
        if (root.isRectangle) {
            System.out.print(root.value);
            System.out.print('[');
            System.out.print(root.width);
            System.out.print(',');
            System.out.print(root.height);
            System.out.print(']');
        } else {
            System.out.print(' ');
            System.out.print(root.value);
            System.out.print(' ');
        }
        // visit right
        printTreeToConsoleFormatted(root.right);
        if (!root.isRectangle && !root.isRoot)
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

            line.append(currentNode.value + "[" + currentNode.width + "," + currentNode.height + "]" + " -> ");

            if (currentNode.left == null) {
                line.append("null,");
            } else {
                line.append(currentNode.left.value + "[" + currentNode.left.width + "," + currentNode.left.height + "]" + ", ");

                queue.add(currentNode.left);
            }

            if (currentNode.right == null) {
                line.append("null");
            } else {
                line.append(currentNode.right.value+ "[" + currentNode.right.width + "," + currentNode.right.height + "]");

                queue.add(currentNode.right);
            }

            System.out.println(line.toString());
        }
    }

}