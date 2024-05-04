import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    
    private char value;
    private List<TreeNode> children;

    TreeNode(char value){
        this.value = value;
        this.children = new ArrayList<>();
    }

    //adds a node child to the current node
    public void addChild(TreeNode child){
        this.children.add(child);
    }
    
    //creates a new node then adds as a child to the current node
    public void addChild(char data){
        this.addChild(new TreeNode(data));
    }
    
    //sets a node as the current node/ replace/ equals/
    public void setNode(TreeNode node){
        this.children=node.children;
    }
    
    public char getValue(){
        return this.value;
    }
    public List<TreeNode> getChildren(){
        return this.children;
    }

    //converts from List format: A,B,C,D  to a node with the root as A and the children as the rest
    public static TreeNode convertCharListToNode(ArrayList<Character> arr){
        TreeNode root = new TreeNode(arr.get(0));
        for (int i = 1; i < arr.size(); i++) {
            TreeNode child = new TreeNode(arr.get(i));
            root.addChild(child);
        }
        return root;
    }
    
    //// this is wrong is should be checking for the values instead of nodes
    // public boolean containsChild(TreeNode node){
    //     return children.contains(node);
    // }
}
