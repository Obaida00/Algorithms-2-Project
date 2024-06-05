package App1;

public class Tree1 {
    public TreeNode1 root;
    int width;
    int height;

    public Tree1(TreeNode1 root){
        this.root = root;
        root.isRoot = true;
        this.updateTreeDim();
    }

    public boolean saveTreeToFile(FileHandler1 fileHandler, boolean drawingVersion) {
        if(this.root == null){
            System.out.println("No tree to save.");
        }else if (drawingVersion) {
            fileHandler.saveTreeToFileDrawFormatted(this.root);
            return true;
        } else if (!drawingVersion) {
            fileHandler.saveTreeToFileLineFormatted(this.root);
            return true;
        }
        return false;
    }

    private void updateTreeDim(){
        root.updateNodeDim();
        this.width = root.width;
        this.height = root.height;
    }

}
