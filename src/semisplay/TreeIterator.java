package semisplay;

import java.util.Iterator;
import java.util.Stack;

/**
 * Klasse die wordt gebruikt als iterator voor SemiSplayTree, houdt een stack bij om de boom
 * m.b.v. DFS te overlopen.
 */
public class TreeIterator<E extends Comparable<E>> implements Iterator<E> {
    private Node<E> visit;
    private Stack<Node<E>> s;

    public TreeIterator(Node<E> root){
        visit = root;
        s = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return ! (visit == null && s.isEmpty());
    }

    @Override
    public E next() {
        while (visit != null) {
            s.push(visit);
            visit = visit.getLeft();
        }
        visit = s.pop();
        E value = visit.getValue();
        visit = visit.getRight();
        return value;
    }
}
