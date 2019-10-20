package semisplay;

import java.util.Iterator;
import java.util.Stack;

public class SemiSplayTree implements SearchTree<Integer>{

    private Node root;
    private int splaysize;

    public SemiSplayTree(Integer splaysize){
        this.splaysize = splaysize;
    }

    /** Voeg de gegeven sleutel toe aan de boom als deze er nog niet in zit.
     * @return true als de sleutel effectief toegevoegd werd. */
    public boolean add(Integer n){
        if (root == null){
            root = new Node(null, n);
            return true;
        }
        else {
            Node node = search(n);
            if (node.getValue() == n) {
                return false;
            } else if (node.getValue() > n) {
                node.addLeft(new Node(node, n));
                return true;
            } else {
                node.addRight(new Node(node, n));
                return true;
            }
        }
    }


    /**
     * @return wortel van de zoekboom */
    public Node getRoot() {
        return root;
    }

    /**
     * @param n sleutel die we in de boom zoeken
     * @return Als node met sleutel gelijk aan n is gevonden, dan returnen we die node, anders returnen we
     * de node die n als kind zou kunnen hebben. */
    public Node search(Integer n){
        Node node = new Node(null, -1);
        Node next = root;
        while (node.getValue() != next.getValue() && next.hasChild() ){
            node = next;
            if (n < next.getValue() && next.hasLeft()){
                next = node.getLeft();
            }
            else if (n > next.getValue() && next.hasRight()){
                next = node.getRight();
            }
        }
        return next;
    }


    /** Zoek de gegeven sleutel op in de boom.
     * @return true als de sleutel gevonden werd. */
    public boolean contains(Integer n){
        return search(n).getValue() == n;
    }

    /** Verwijder de gegeven sleutel uit de boom.
     * @return true als de sleutel gevonden en verwijderd werd. */
    public boolean remove(Integer n){
        Node remove = search(n);
        if (remove.getValue() == n) {
            Node replacement = findReplacement(remove);
            if (replacement != null){
                replacement.changeParent(remove.getParent());
            }
            else {
                remove.getParent().removeChild(remove);
            }
            return true;
        }
        return false;
    }


    /**
     * @param node de node waarvoor we een vervanger moeten vinden
     * @return null als de node geen kinderen heeft, anders oftewel de kleinste node in de grote deelboom of
     * grootste node in de kleine deelboom*/
    public Node findReplacement(Node node){
        Node replacement = null;
        Node tmp;
        if (node.hasRight()){
            replacement = node.getRight();
            while (replacement.hasLeft()){
                tmp = replacement;
                replacement = tmp.getLeft();
            }
        }
        else if (node.hasLeft()){
            replacement = node.getLeft();
            while (replacement.hasRight()){
                tmp = replacement;
                replacement = tmp.getRight();
            }
        }
        return replacement;
    }

    /** @return het aantal sleutels in de boom. */
    public int size() {
        int i = 0;
        for (Integer val: this){
            i++;
        }
        return i;
    }

    /** @return de diepte van de boom. */
    public int depth(){
        return depthHelper(root);
    }


    /**
     * Methode zodat we recursief kunnen zoeken naar de diepte van linker en rechterboom
     * @param n de node die de wortel is van de deelboom die we aan het doorzoeken zijn
     * @return de diepte van de deelboom
     */
    public int depthHelper(Node n){
        if (n != null){
            int depthLeft = depthHelper(n.getLeft());
            int depthRight = depthHelper(n.getRight());
            if (depthRight < depthLeft){
                return depthLeft + 1;
            }
            else {
                return depthRight + 1;
            }
        }
        else {
            return 0;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
       return new TreeIterator(root);
    }
}

//todo: veld size ipv 0(n) overlopen, kan parent veld niet weg uit constructor van node?
// todo: lege bomen testen, grotere inputs en random inputs testen