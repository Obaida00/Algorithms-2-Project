package App.Logic.App2;
import java.util.ArrayList;

public class GeneralNode2 extends TreeNode2 {
    GeneralNode2 parent;
    public ArrayList<GeneralNode2> children;
    public int width;

    public GeneralNode2(GeneralNode2 parent, char value) {
        this.parent = parent;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public boolean hasChild() {
        for (GeneralNode2 node : this.children) {
            if (node != null)
                return true;
        }
        return false;
    }

}
