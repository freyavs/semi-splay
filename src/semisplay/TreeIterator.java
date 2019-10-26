package semisplay;

import java.util.Iterator;
import java.util.Stack;

/**
 * Klasse die wordt gebruikt als iterator voor SemiSplayTree, houdt een stack bij om de boom
 * m.b.v. DFS te overlopen.
 */
public class TreeIterator<E extends Comparable<E>> implements Iterator<E> {
    private Node<E> visiting;
    private Stack<Node<E>> stack;

    public TreeIterator(Node<E> root){
        visiting = root;
        stack = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return ! (visiting == null && stack.isEmpty());
    }

    @Override
    public E next() {
        while (visiting != null) {
            stack.push(visiting);
            visiting = visiting.getLeft();
        }
        visiting = stack.pop();
        E value = visiting.getValue();
        visiting = visiting.getRight();
        return value;
    }
}
