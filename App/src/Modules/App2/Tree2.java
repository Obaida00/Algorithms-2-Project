package App2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class Tree2 {
    private float offsetX = 20, offsetY = 20;
    public GeneralNode2 generalRoot;
    BinaryNode2 binaryRoot;

    Tree2(char data) {
        this.generalRoot = new GeneralNode2(null, data);
    }

    Tree2(GeneralNode2 root) {
        this.generalRoot = root;
    }

    // converts from List format: A,B,C,D to a node with the root as A and the
    // children as the rest
    public static GeneralNode2 convertCharListToNode(ArrayList<Character> arr) {
        GeneralNode2 root = new GeneralNode2(null, arr.get(0));
        for (int i = 1; i < arr.size(); i++) {
            GeneralNode2 child = new GeneralNode2(root, arr.get(i));
            root.children.add(child);
        }
        return root;
    }

    // private GeneralNode findNode(GeneralNode root, GeneralNode node) {
    // if (root == null)
    // return null;
    // if (root.getValue() == node.getValue())
    // return root;

    // for (GeneralNode n : root.getChildren()) {
    // GeneralNode child;
    // if ((child = findNode(n, node)) != null) {
    // return child;
    // }
    // }
    // return null;
    // }

    public void buildBinary() {
        this.binaryRoot = toBinaryTree(null, this.generalRoot);
    }

    public BinaryNode2 toBinaryTree(BinaryNode2 newParent, GeneralNode2 node) {
        if (node == null)
            return null;
        BinaryNode2 binary = new BinaryNode2(newParent, node.value);

        if (node.hasChild()) {
            binary.right = toBinaryTree(binary, node.children.getLast());
        }

        BinaryNode2 current = binary.right;
        for (int i = node.children.size() - 2; i >= 0; i--) {
            if (current == null)
                continue;
            current.left = toBinaryTree(current, node.children.get(i));
            current = current.left;
        }

        return binary;
    }

    public void buildGeneral() {
        this.generalRoot = toGeneralTree(null, this.binaryRoot);
    }

    public GeneralNode2 toGeneralTree(GeneralNode2 newParent, BinaryNode2 node) {
        if (node == null)
            return null;
        GeneralNode2 general = new GeneralNode2(newParent, node.value);

        if (node.right != null) {
            general.children.addLast(toGeneralTree(general, node.right));

            BinaryNode2 current = node.right;
            while (current.left != null) {
                general.children.addFirst(toGeneralTree(general, current.left));
                current = current.left;
            }
        }

        return general;
    }
    // public void toBinaryTree() { //doesn't work
    // binaryRoot = new TreeNode(null, this.treeRoot.getValue());
    // // Tree binaryTree = new Tree(newRoot, true);

    // Stack<TreeNode> genStack = new Stack<>();
    // Stack<TreeNode> binStack = new Stack<>();

    // genStack.push(this.treeRoot);
    // binStack.push(binaryRoot);

    // // iterator and creator
    // int lefti = 0, righti = 1;
    // while (!genStack.isEmpty() && !binStack.isEmpty()) {
    // TreeNode general = genStack.pop();
    // TreeNode binary = binStack.pop();

    // int genChildCount;
    // int iChild = 0;

    // List<TreeNode> children = general.getChildren();
    // List<TreeNode> siblings = null;

    // genChildCount = children.size();

    // if (general.getParent() != null) {
    // siblings = general.getParent().getChildren();
    // iChild = siblings.indexOf(general);
    // }

    // TreeNode right = null;
    // TreeNode left = null;

    // if (genChildCount > 0)
    // right = children.get(genChildCount - 1);// right child is the first right
    // child
    // if (iChild != 0)
    // left = siblings.get(iChild - 1);// left child is the first left sibling of
    // the current node

    // binary.addChildAt(lefti, left);
    // binary.addChildAt(righti, right);

    // // updating stacks
    // for (int i = 0; i < genChildCount; i++)
    // genStack.push(children.get(i));
    // if (left != null)
    // binStack.push(left);
    // if (right != null)
    // binStack.push(right);
    // }
    // // return binaryRoot;
    // }

    // public void insertToNode(TreeNode parent, char data){
    // TreeNode newNode = new TreeNode(data);
    // parent.addChild(newNode);
    // }
    // public void insertToNode(TreeNode parent, TreeNode node){
    // parent.addChild(node);
    // }

    // public boolean containsNode(TreeNode node){
    // boolean exists = searchNode(root, node);
    // return exists;
    // }
    // public boolean containsNode(char data){
    // TreeNode node = new TreeNode(data);
    // boolean exists = searchNode(root, node);
    // return exists;
    // }

    // public boolean searchNode(TreeNode root, TreeNode node){
    // if(root == null)
    // return false;
    // if(root.getValue() == node.getValue())
    // return true;

    // for (TreeNode n : root.getChildren()) {
    // if(searchNode(n, node))
    // return true;
    // }
    // return false;
    // }

    public void printGeneralTreeToConsole() {
        this.printGeneralTree(this.generalRoot, "", false);
    }

    private void printGeneralTree(GeneralNode2 node, String prefix, boolean isLastChild) {
        if (node == null) {
            return;
        }

        StringBuilder sb = new StringBuilder(prefix);

        if (isLastChild) {
            sb.append("└─ ");
            prefix += "   ";
        } else {
            sb.append("├─ ");
            prefix += "│  ";
        }

        sb.append(node.value);
        System.out.println(sb.toString());

        int numChildren = node.children.size();

        for (int i = 0; i < numChildren; i++) {
            GeneralNode2 child = node.children.get(i);
            boolean lastChild = (i == numChildren - 1);
            printGeneralTree(child, prefix, lastChild);
        }
    }

    public GeneralNode2 findGeneralNode(GeneralNode2 newNode) {
        return findGeneralNode(this.generalRoot, newNode);
    }

    public void printBinaryTreeToConsole() {
        this.printBinaryTree(this.binaryRoot);
    }

    private void printBinaryTree(BinaryNode2 root) {
        Queue<BinaryNode2> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            StringBuilder line = new StringBuilder();
            BinaryNode2 currentNode = queue.remove();
            if (currentNode.left == null && currentNode.right == null)
                continue;

            line.append(currentNode.value + " -> ");

            if (currentNode.left == null) {
                line.append("null,");
            } else {
                line.append(currentNode.left.value + ", ");
                queue.add(currentNode.left);
            }

            if (currentNode.right == null) {
                line.append("null");
            } else {
                line.append(currentNode.right.value);
                queue.add(currentNode.right);
            }

            System.out.println(line.toString());
        }
    }

    private GeneralNode2 findGeneralNode(GeneralNode2 root, GeneralNode2 node) {
        if (root == null)
            return null;
        if (root.value == node.value)
            return root;

        for (GeneralNode2 child : root.children) {
            GeneralNode2 found = findGeneralNode(child, node);
            if (found != null)
                return found;
        }
        return null;
    }

    // saves the version for the tree specified by the binaryVersion and returns if
    // the process was succeeded
    public boolean saveTreeToFile(FileHandler2 fileHandler, boolean binaryVersion) {
        if (binaryVersion && this.binaryRoot != null) {
            fileHandler.saveBinaryTreeToFile(this.binaryRoot);
            return true;
        } else if (!binaryVersion && this.generalRoot != null) {
            fileHandler.saveGeneralTreeToFile(this.generalRoot);
            return true;
        } else {
            return false;
        }

    }

    // work in progress
    // public static void calculatePositions(TreeNode root, int x, int y, int level,
    // int horizontalGap, int verticalGap) {
    // if (root == null) {
    // return;
    // }

    // int nodeWidth = 30; // Adjust this value according to the width of your nodes

    // // Calculate the positions of the child nodes
    // calculatePositions(root.left, x, y + verticalGap, level + 1, horizontalGap,
    // verticalGap);
    // calculatePositions(root.right, x + root.left.width + nodeWidth +
    // horizontalGap, y + verticalGap, level + 1, horizontalGap, verticalGap);

    // // Update the current node's position based on the positions of the child
    // nodes
    // if (root.left != null) {
    // root.x = root.left.x + root.left.width + nodeWidth / 2;
    // } else {
    // root.x = x;
    // }
    // root.y = y;

    // // Update the width of the subtree rooted at the current node
    // if (root.left == null && root.right == null) {
    // root.width = nodeWidth;
    // } else if (root.left != null && root.right == null) {
    // root.width = root.left.width + nodeWidth;
    // } else if (root.left == null && root.right != null) {
    // root.width = root.right.width + nodeWidth;
    // } else {
    // root.width = root.left.width + nodeWidth + root.right.width;
    // }

    // public void initializeCoordinates(BinaryNode2 node, float x, float y) {
    // if (node == null) {
    // return;
    // }
    // node.y = y;

    // // if(x - get())
    // node.x = x;

    // initializeCoordinates(node.left, x - offsetX, y + offsetY);
    // initializeCoordinates(node.right, x + offsetX, y + offsetY);
    // }

    // public void initializeCoordinates(GeneralNode2 node, float x, float y) {

    // }
}
