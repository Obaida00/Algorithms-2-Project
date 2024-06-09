package App.Logic.App1;

import App.Logic.App2.BinaryNode2;
import App.Logic.App2.GeneralNode2;

public class Tree1 {
    private int NODE_WIDTH;// node width in pixels
    private int VERTICAL_GAP;// the vertical gap in the visual tree
    private int HORIZONTAL_GAP;// the horizontal gap in the visual tree

    public TreeNode1 root;
    public String[][] rootPaper;
    int width;
    int height;

    public Tree1(TreeNode1 root) {
        this.root = root;
        root.isRoot = true;
        this.updateTreeDim();
    }

    //work in progress
    public boolean saveTreeToFile(FileHandler1 fileHandler, boolean drawingVersion) {
        if (this.root == null) {
            System.out.println("No tree to save.");
        } else if (drawingVersion) {
            fileHandler.saveTreeToFileDrawFormatted(this);
            return true;
        } else if (!drawingVersion) {
            fileHandler.saveTreeToFileLineFormatted(this);
            return true;
        }
        return false;
    }

    private void updateTreeDim() {
        root.updateNodeDim();
        this.width = root.width;
        this.height = root.height;
    }

    public void buildPaper() {
        this.rootPaper = new String[this.height][this.width];
        this.buildArrayListPaper(this.root, 0, 0);
    }

    private void buildArrayListPaper(TreeNode1 root, int x, int y) {
        var arr = this.rootPaper;
        if (root == null)
            return;


        if (root.isPaper) {
            
        }else if(root.value == '-'){
            for (int i = x; i < root.width; i++) {
                this.rootPaper[x][y+root.left.height] = "-";
            }
            buildArrayListPaper(root.left, x, y);
            buildArrayListPaper(root.right, x, y+root.left.height);
        }else if(root.value == '|'){
            for (int i = y; i < root.height; i++) {
                this.rootPaper[x+root.left.width][y] = "|";
            }
            buildArrayListPaper(root.left, x, y);
            buildArrayListPaper(root.right, x+root.left.width, y);
        }
    }

    public boolean calculatePositions(int startX, int startY, int nodeWidth, int horizontalGap, int verticalGap) {
        this.NODE_WIDTH = nodeWidth;
        this.HORIZONTAL_GAP = horizontalGap;
        this.VERTICAL_GAP = verticalGap;

        if(this.root == null)
            return false;

        this.calculateBinaryPositioning(this.root, startX, startY);
        return true;
    }

    private void calculateBinaryPositioning(TreeNode1 root, int x, int y) {
        if (root == null) {
            return;
        }

        // Calculate the positions of the child nodes
        calculateBinaryPositioning(root.left, x, y + this.VERTICAL_GAP);
        int leftWidth = 0;
        if (root.left != null)
            leftWidth = root.left.width;
        calculateBinaryPositioning(root.right,
                x + leftWidth + NODE_WIDTH + /* i think this shouldnt be added */this.HORIZONTAL_GAP,
                y + this.VERTICAL_GAP);

        // Update the current node's position based on the positions of the child nodes
        if (root.left != null) {
            root.x = root.left.x + root.left.width + NODE_WIDTH / 2;
        } else {
            root.x = x;
        }
        root.y = y;

        // Update the width of the subtree rooted at the current node
        if (root.left == null && root.right == null) {
            root.width = NODE_WIDTH;
        } else if (root.left != null && root.right == null) {
            root.width = root.left.width + NODE_WIDTH;
        } else if (root.left == null && root.right != null) {
            root.width = root.right.width + NODE_WIDTH;
        } else {
            root.width = root.left.width + NODE_WIDTH + root.right.width;
        }
    }
}
