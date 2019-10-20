package semisplay;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class SemiSplayTreeTest {

    private Random RG = new Random();

    @Test
    public void checkRootAndChildrenAdd() {
        SemiSplayTree tree = new SemiSplayTree(7);
        tree.add(5);
        tree.add(7);
        tree.add(2);
        assertEquals(5, tree.getRoot().getValue());
        assertEquals(2, tree.getRoot().getLeft().getValue());
        assertEquals(7, tree.getRoot().getRight().getValue());
    }

    @Test
    public void checkAdd() {
        SemiSplayTree tree = new SemiSplayTree(7);
        tree.add(5);
        tree.add(7);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(6);
        for (Integer val : tree) {
            System.out.println(val);
        }
        assertEquals(4, tree.getRoot().getLeft().getRight().getRight().getValue());
        assertEquals(6, tree.getRoot().getRight().getLeft().getValue());
    }

    @Test
    public void checkContains() {
        SemiSplayTree tree = new SemiSplayTree(7);
        tree.add(5);
        tree.add(7);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(6);
        for (Integer val : tree) {
            System.out.println(val);
        }
        assertTrue(tree.contains(3));
        assertFalse(tree.contains(10));
    }

    @Test
    public void checkSize() {
        SemiSplayTree tree = new SemiSplayTree(7);
        for (int i = 0; i < 320; i++) {
            tree.add(i);
        }
        assertEquals(320, tree.size());
    }

    @Test
    public void removeLeaf() {
        SemiSplayTree tree = new SemiSplayTree(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        Boolean bool = tree.remove(3);
        assertEquals(5, tree.size());
        assertTrue(bool);
    }

    @Test
    public void removeNodeNotInTree() {
        SemiSplayTree tree = new SemiSplayTree(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        Boolean bool = tree.remove(70);
        assertEquals(6, tree.size());
        assertFalse(bool);
    }

    @Test
    public void findReplacementSuccess() {
        SemiSplayTree tree = new SemiSplayTree(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        tree.add(13); tree.add(21);
        Node f = tree.getRoot().getRight();
        Node replace = tree.findReplacement(f);
        assertEquals(22, replace.getValue());
    }

    @Test
    public void findReplacementFail() {
        SemiSplayTree tree = new SemiSplayTree(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        tree.add(13); tree.add(21);
        Node f = tree.getRoot().getLeft().getRight();
        Node replace = tree.findReplacement(f);
        assertNull(replace);
    }

    @Test
    public void depthTest() {
        SemiSplayTree tree = new SemiSplayTree(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        tree.add(13); tree.add(21);
        assertEquals(4, tree.depth());
    }

    @Test
    public void depthTest2() {
        SemiSplayTree tree = new SemiSplayTree(7);
        assertEquals(0, tree.depth());
    }

    @Test
    public void depthTest3() {
        SemiSplayTree tree = new SemiSplayTree(7);
        for (int i = 0; i < 320; i++) {
            tree.add(i);
        }
        assertEquals(320, tree.depth());
    }


}