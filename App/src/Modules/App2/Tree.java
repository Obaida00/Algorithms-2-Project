package App2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    public GeneralNode generalRoot;
    BinaryNode binaryRoot;

    Tree(char data) {
        this.generalRoot = new GeneralNode(data);
    }

    Tree(GeneralNode root) {
        this.generalRoot = root;
    }

    // converts from List format: A,B,C,D to a node with the root as A and the
    // children as the rest
    public static GeneralNode convertCharListToNode(ArrayList<Character> arr) {
        GeneralNode root = new GeneralNode(arr.get(0));
        for (int i = 1; i < arr.size(); i++) {
            GeneralNode child = new GeneralNode(arr.get(i));
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
        this.binaryRoot = toBinaryTree(this.generalRoot);
    }

    public BinaryNode toBinaryTree(GeneralNode node) {
        if (node == null)
            return null;
        BinaryNode binary = new BinaryNode(node.value);

        if (node.hasChild()) {
            binary.right = toBinaryTree(node.children.getLast());
        }

        BinaryNode current = binary.right;
        for (int i = node.children.size() - 2; i >= 0; i--) {
            if (current == null)
                continue;
            current.left = toBinaryTree(node.children.get(i));
            current = current.left;
        }

        return binary;
    }

    public void buildGeneral() {
        this.generalRoot = toGeneralTree(this.binaryRoot);
    }

    public GeneralNode toGeneralTree(BinaryNode node) {
        if (node == null)
            return null;
        GeneralNode general = new GeneralNode(node.value);

        if (node.right != null) {
            general.children.addLast(toGeneralTree(node.right));

            BinaryNode current = node.right;
            while (current.left != null) {
                general.children.addFirst(toGeneralTree(current.left));
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

    private void printGeneralTree(GeneralNode node, String prefix, boolean isLastChild) {
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
            GeneralNode child = node.children.get(i);
            boolean lastChild = (i == numChildren - 1);
            printGeneralTree(child, prefix, lastChild);
        }
    }

    public GeneralNode findGeneralNode(GeneralNode newNode) {
        return findGeneralNode(this.generalRoot, newNode);
    }

    public void printBinaryTreeToConsole() {
        this.printBinaryTree(this.binaryRoot);
    }

    private void printBinaryTree(BinaryNode root) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            StringBuilder line = new StringBuilder();
            BinaryNode currentNode = queue.remove();
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

    private GeneralNode findGeneralNode(GeneralNode root, GeneralNode node) {
        if (root == null)
            return null;
        if (root.value == node.value)
            return root;

        for (GeneralNode child : root.children) {
            GeneralNode found = findGeneralNode(child, node);
            if (found != null)
                return found;
        }
        return null;
    }

    // saves the version for the tree specified by the binaryVersion and returns if
    // the process was succeeded
    public boolean saveTreeToFile(FileHandler fileHandler, boolean binaryVersion) {
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

}
