package App1;

public class TreeNode1 {
    boolean isRectangle;
    boolean isRoot = false;
    char value;
    char name;
    int width = 0;
    int height = 0;

    TreeNode1 right;
    TreeNode1 left;

    public TreeNode1(char value){
        this.isRectangle = false;
        this.value = value;
    }

    public TreeNode1(char name, int width, int height){
        this.isRectangle = true;
        this.name = name;
        this.height = height;
        this.width = width;
    }
}
