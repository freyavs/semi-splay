package semisplay;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class SemiSplayTreeTest {

    private static Random RG = new Random();

    @Test
    public void checkRootAndChildrenAdd() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        tree.add(5);
        tree.add(7);
        tree.add(2);
        assertEquals((Integer)5, tree.getRoot().getValue());
        assertEquals((Integer)2, tree.getRoot().getLeft().getValue());
        assertEquals((Integer)7, tree.getRoot().getRight().getValue());
    }

    @Test
    public void checkAdd() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        tree.add(5);
        tree.add(7);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(6);
        boolean bool = tree.add(3);
        for (Integer val : tree) {
            System.out.println(val);
        }
        assertEquals((Integer)4, tree.getRoot().getLeft().getRight().getRight().getValue());
        assertEquals((Integer)6, tree.getRoot().getRight().getLeft().getValue());
        assertFalse(bool);
    }

    @Test
    public void checkContains() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
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
    public void containsEmptyTree() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        assertFalse(tree.contains(10));
    }

    @Test
    public void checkSize() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        for (int i = 0; i < 320; i++) {
            tree.add(i);
        }
        tree.remove(4);
        tree.remove(100);
        assertEquals(318, tree.size());
    }

    @Test
    public void removeLeaf() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        boolean bool = tree.remove(3);
        assertEquals(5, tree.size());
        assertTrue(bool);
    }

    @Test
    public void removeNodeNotInTree() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        boolean bool = tree.remove(70);
        assertEquals(6, tree.size());
        assertFalse(bool);
    }

    @Test
    public void removeWhenReplacementHasChild1() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        tree.add(40); tree.add(20); tree.add(60);
        tree.add(10); tree.add(5);
        boolean bool = tree.remove(20);
        assertEquals(4, tree.size());
        assertTrue(bool);
        assertEquals((Integer)10, tree.getRoot().getLeft().getValue());
        assertEquals((Integer)5, tree.getRoot().getLeft().getLeft().getValue());
    }

    @Test
    public void removeWhenReplacementHasChild2() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        tree.add(40); tree.add(20); tree.add(60);
        tree.add(10); tree.add(5);tree.add(30); tree.add(90);
        tree.add(100); tree.add(120);
        boolean bool = tree.remove(90);
        assertTrue(bool);
        assertEquals(8, tree.size());
        assertEquals((Integer)100, tree.getRoot().getRight().getRight().getValue());
        assertEquals((Integer)120, tree.getRoot().getRight().getRight().getRight().getValue());
    }

    @Test
    public void removeFromEmptyTree() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        boolean bool = tree.remove(10);
        assertFalse(bool);
    }

    @Test
    public void findReplacementSuccess() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        tree.add(13); tree.add(21);
        Node<Integer> f = tree.getRoot().getRight();
        Node<Integer> replace = tree.findReplacement(f);
        assertEquals((Integer)22, replace.getValue());
    }

    @Test
    public void findReplacementFail() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        tree.add(13); tree.add(21);
        Node<Integer> f = tree.getRoot().getLeft().getRight();
        Node<Integer> replace = tree.findReplacement(f);
        assertNull(replace);
    }

    @Test
    public void depthTest() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        tree.add(20); tree.add(9); tree.add(6);
        tree.add(34); tree.add(3); tree.add(22);
        tree.add(13); tree.add(21);
        assertEquals(4, tree.depth());
    }

    @Test
    public void depthTest2() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        assertEquals(0, tree.depth());
    }

    @Test
    public void depthTest3() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        for (int i = 0; i < 320; i++) {
            tree.add(i);
        }
        assertEquals(320, tree.depth());
    }

    @Test
    public void depthTestPrintWithIterator() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        for (int i = 0; i < 20; i++) {
            tree.add(RG.nextInt());
        }
        for (Integer val: tree){
            System.out.println(val);
        }
    }


    @Test
    public void treeWithStrings() {
        SemiSplayTree<String> tree = new SemiSplayTree<>(7);
        tree.add("hallo"); tree.add("dit"); tree.add("negen");
        tree.add("boom"); tree.add("na");tree.add("eenheellangwoordofmssnietzolang"); tree.add("d");
        tree.add("negen"); tree.add("");
        for (String v: tree){
            System.out.println(v);
        }

    }


    @Test
    public void bigTree() {
        SemiSplayTree<Integer> tree = new SemiSplayTree<>(7);
        int value = 0;
        for (int i = 0; i < 20000; i++) {
            int rand = RG.nextInt();
            tree.add(rand);
            value = rand;
        }
        int size = tree.size();
        tree.remove(value);
        tree.remove(value);
        assertEquals(size - 1, tree.size());
        assertFalse(tree.contains(value));
    }


}