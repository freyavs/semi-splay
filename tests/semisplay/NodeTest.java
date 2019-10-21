package semisplay;

import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void hasNoChildren() {
        Node node = new Node(null, 7);
        assertFalse(node.hasChild());
    }

    @Test
    public void hasRightChild() {
        Node node = new Node(null, 7);
        node.addRight(new Node(node, 5));
        assertTrue(node.hasChild());
        assertTrue(node.hasRight());
    }

    @Test
    public void getParent(){
        Node node = new Node(null, 7);
        Node child = new Node(node, 5);
        node.addLeft(child);
        assertEquals(7, child.getParent().getValue());
        assertEquals(5, node.getLeft().getValue());
    }

    @Test
    public void changeParent(){
        Node node = new Node(null, 7);
        Node newparent = new Node (null, 10);
        Node child = new Node(node, 5);
        Node child2 = new Node(node, 9);
        child.setParent(newparent);
        assertNull(node.getLeft());
        assertEquals(5, newparent.getLeft().getValue());
    }
}