package App1;

public class TreeNode1 {
    boolean isRectangle;
    boolean isRoot = false;
    char value;
    int width = 0;
    int height = 0;

    TreeNode1 right;
    TreeNode1 left;

    public TreeNode1(char value) {
        this.isRectangle = false;
        this.value = value;
    }

    public TreeNode1(char value, int width, int height) {
        this.isRectangle = true;
        this.value = value;
        this.height = height;
        this.width = width;
    }

    public void updateNodeDim() {
        if (this.isRectangle)
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
