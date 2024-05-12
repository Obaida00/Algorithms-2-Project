package App2;
import java.util.ArrayList;

public class GeneralNode extends TreeNode {
    public ArrayList<GeneralNode> children;

    GeneralNode(char value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public boolean hasChild() {
        for (GeneralNode node : this.children) {
            if (node != null)
                return true;
        }
        return false;
    }

}
