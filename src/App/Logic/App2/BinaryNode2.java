package App.Logic.App2;
public class BinaryNode2 extends TreeNode2 {
    public BinaryNode2 parent;
    public BinaryNode2 left;
    public BinaryNode2 right;
    public int width;
    
    BinaryNode2(BinaryNode2 parent, char value) {
        this.value = value;
        this.parent = parent;
        left = null;
        right = null;
    }
}
