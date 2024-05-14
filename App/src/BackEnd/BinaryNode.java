package BackEnd;
public class BinaryNode extends TreeNode {
    public BinaryNode left;
    public BinaryNode right;
    

    public BinaryNode() {}
    public BinaryNode(char value, float x, float y) {
        super(x, y);
        this.value = value;
        left = null;
        right = null;
    }
    public BinaryNode(char value) {
        this.value = value;
        left = null;
        right = null;
    }
}
