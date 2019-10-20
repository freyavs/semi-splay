package semisplay;

import java.util.Iterator;
import java.util.Stack;

/**
 * Klasse die wordt gebruikt als iterator voor SemiSplayTree, houdt een stack bij om de boom
 * m.b.v. DFS te overlopen.
 */
public class TreeIterator implements Iterator<Integer> {
    private Node visiting;
    private Stack<Node> stack;

    public TreeIterator(Node root){
        visiting = root;
        stack = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return ! (visiting == null && stack.isEmpty());
    }

    @Override
    public Integer next() {
        while (visiting != null) {
            stack.push(visiting);
            visiting = visiting.getLeft();
        }
        visiting = stack.pop();
        Integer value = visiting.getValue();
        visiting = visiting.getRight();
        return value;
    }
}
