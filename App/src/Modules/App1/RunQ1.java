package App1;

import java.util.LinkedList;
import java.util.Queue;

public class RunQ1 {
    public static void run() {
        FileHandler1 fileHandler1 = new FileHandler1("App/src/Data/input1.txt", "App/src/Data/output1.txt");
        Tree1 tree = fileHandler1.loadTree();
        fileHandler1.saveTree(tree);
        // printTreeToConsole(tree);

        fileHandler1.close();
    }



    // for debugging perposes
    private static void printTreeToConsole(Tree1 tree) {
        printTree(tree.root);
    }

    private static void printTree(TreeNode1 root) {
        if (root == null)
            return;

        if (!root.isRectangle && !root.isRoot)
            System.out.print('(');
        // visit left
        printTree(root.left);

        // do root
        if (root.isRectangle) {
            System.out.print(root.name);
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
        printTree(root.right);
        if (!root.isRectangle && !root.isRoot)
            System.out.print(')');
    }
}