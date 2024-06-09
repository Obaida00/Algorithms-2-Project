package App.Logic.App1;

public class TreeNode1 {
    public TreeNode1 parent;
    boolean isPaper;
    boolean isRoot = false;
    public char value;
    public int width = 0;
    int height = 0;

    public int x;
    public int y;

    public TreeNode1 right;
    public TreeNode1 left;

    public TreeNode1(TreeNode1 parent, char value) {
        this.parent = parent;
        this.isPaper = false;
        this.value = value;
    }

    public TreeNode1(TreeNode1 parent, char value, int width, int height) {
        this.parent = parent;
        this.isPaper = true;
        this.value = value;
        this.height = height;
        this.width = width;
    }

    public void updateNodeDim() {
        if (this.isPaper)
            return;

        // there is no need to check if left or right are null because the tree is
        // complete so any nonRectangler node is gonna have left and right
        this.left.updateNodeDim();
        this.right.updateNodeDim();

        if(this.value == '-'){
            this.width = this.left.width;// = this.right.width
            this.height = this.left.height + this.right.height;
        }

        if(this.value == '|'){
            this.width = this.left.width + this.right.width;
            this.height = this.left.height;// = this.right.height
        }
    }
}
