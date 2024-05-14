package BackEnd.App1;

public class TreeNode1 {
    boolean isRectangle;
    char value;
    char name;
    float width = 0;
    float height = 0;

    TreeNode1 right;
    TreeNode1 left;

    public TreeNode1(char value){
        this.isRectangle = false;
        this.value = value;
    }

    public TreeNode1(char name, float width, float height){
        this.isRectangle = true;
        this.name = name;
        this.height = height;
        this.width = width;
    }
}
