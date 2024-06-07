package App.Logic.App1;

public class Tree1 {
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

}
