package semisplay;

import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void hasNoChildren() {
        Node<Integer> node = new Node<>(null, 7);
        assertFalse(node.hasChild());
    }

    @Test
    public void hasRightChild() {
        Node<Integer> node = new Node<>(null, 7);
        node.addRight(new Node<>(node, 5));
        assertTrue(node.hasChild());
        assertTrue(node.hasRight());
    }

    @Test
    public void getParent(){
        Node<Integer> node = new Node<>(null, 7);
        Node<Integer> child = new Node<>(node, 5);
        node.addLeft(child);
        assertEquals((Integer)7, child.getParent().getValue());
        assertEquals((Integer)5, node.getLeft().getValue());
    }

    @Test
    public void changeParent(){
        Node<Integer> node = new Node<>(null, 7);
        Node<Integer> newparent = new Node<> (null, 10);
        Node<Integer> child = new Node<>(node, 5);
        Node<Integer> child2 = new Node<>(node, 9);
        child.setParent(newparent);
        assertNull(node.getLeft());
        assertEquals((Integer)5, newparent.getLeft().getValue());
    }
}