public class Tree {
    TreeNode root;
    
    Tree(char data){
        this.root = new TreeNode(data);
    }
    
    Tree(TreeNode root){
        this.root = root;
    }
    
    //Searches the entire tree and returns the first accurence of the given node (not the same 
    // object but the same value)
    public TreeNode findNode(TreeNode node){
        return findNode(root, node);
    }

    private TreeNode findNode(TreeNode root, TreeNode node){
        if(root == null)
            return null;
        if(root.getValue() == node.getValue())
            return root;
        
        for (TreeNode n : root.getChildren()) {
            TreeNode child;
            if((child=findNode(n, node)) != null)
                return child;
        }
        return null;
    }

    public TreeNode getRoot(){
        return root;
    }

    // public void insertToNode(TreeNode parent, char data){
        //     TreeNode newNode = new TreeNode(data);
    //     parent.addChild(newNode);
    // }
    // public void insertToNode(TreeNode parent, TreeNode node){
    //     parent.addChild(node);
    // }

    // public boolean containsNode(TreeNode node){
    //     boolean exists = searchNode(root, node);
    //     return exists;
    // }
    // public boolean containsNode(char data){
    //     TreeNode node = new TreeNode(data);
    //     boolean exists = searchNode(root, node);
    //     return exists;
    // }

    // public boolean searchNode(TreeNode root, TreeNode node){
    //     if(root == null)
    //         return false;
    //     if(root.getValue() == node.getValue())
    //         return true;
        
    //     for (TreeNode n : root.getChildren()) {
    //         if(searchNode(n, node))
    //             return true;
    //     }
    //     return false;
    // }


    // public void printTreeToConsole() {
    //     printTree(root,"", false);
    // }

    // private void printTree(TreeNode node, String prefix, boolean isLastChild) {
    //     if (node == null) {
    //         return;
    //     }

    //     StringBuilder sb = new StringBuilder(prefix);

    //     if (isLastChild) {
    //         sb.append("└─ ");
    //         prefix += "   ";
    //     } else {
    //         sb.append("├─ ");
    //         prefix += "│  ";
    //     }

    //     sb.append(node.getValue());
    //     System.out.println(sb.toString());

    //     int numChildren = node.getChildren().size();
    //     for (int i = 0; i < numChildren; i++) {
    //         TreeNode child = node.getChildren().get(i);
    //         boolean lastChild = (i == numChildren - 1);
    //         printTree(child, prefix, lastChild);
    //     }
    // }
}
