package semisplay;

import java.util.Iterator;

public class SemiSplayTree implements SearchTree<Integer>{

    private Node root;
    private int size;
    private int splaysize; //werkt nog niet met splayen

    public SemiSplayTree(Integer splaysize){
        this.splaysize = splaysize;
    }

    /** Voeg de gegeven sleutel toe aan de boom als deze er nog niet in zit.
     * @return true als de sleutel effectief toegevoegd werd. */
    public boolean add(Integer n){
        if (root == null){
            root = new Node(null, n);
            size++;
            return true;
        }
        else {
            Node node = search(n);
            if (node.getValue() < n) {
                node.addRight(new Node(node, n));
                size++;
            } else if (node.getValue() > n) {
                node.addLeft(new Node(node, n));
                size++;
            }
            return node.getValue() == n;
        }
    }

    /**
     * @return wortel van de zoekboom */
    public Node getRoot() {
        return root;
    }

    /**
     * Hulpfunctie voor andere methodes
     * @param n sleutel die we in de boom zoeken
     * @return Als node met sleutel gelijk aan n is gevonden, dan returnen we die node, anders returnen we
     * de node die n als kind zou kunnen hebben. Als de boom leeg is return null;*/
    public Node search(Integer n){
        Node node = new Node(null, -1);
        Node next = root;
        if (root != null) {
            while (node.getValue() != next.getValue() && next.hasChild()) {
                node = next;
                if (n < next.getValue() && next.hasLeft()) {
                    next = node.getLeft();
                } else if (n > next.getValue() && next.hasRight()) {
                    next = node.getRight();
                }
            }
        }
        return next;
    }

    /** Zoek de gegeven sleutel op in de boom.
     * @return true als de sleutel gevonden werd. */
    public boolean contains(Integer n){
        Node node = search(n);
        return node != null && node.getValue() == n;
    }

    /** Verwijder de gegeven sleutel uit de boom.
     * @return true als de sleutel gevonden en verwijderd werd. */
    public boolean remove(Integer n){
        Node toRemove = search(n);
        if (toRemove != null && toRemove.getValue() == n) {
            Node replacement = findReplacement(toRemove);
            if (replacement != null){
                if (replacement.hasChild()){
                    if (replacement.hasLeft()){
                        Node p = replacement.getParent();
                        replacement.getLeft().setParent(p);
                    }
                    else {
                        Node p = replacement.getParent();
                        replacement.getRight().setParent(p);
                    }
                }
                replacement.setParent(toRemove.getParent());
            }
            else {
                toRemove.getParent().removeChild(toRemove);
            }
            size--;
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
        return size;
    }

    /** @return de diepte van de boom. */
    public int depth(){
        return depthPart(root);
    }

    /**
     * Methode zodat we recursief kunnen zoeken naar de diepte van linker en rechterboom
     * @param n de node die de wortel is van de deelboom die we aan het doorzoeken zijn
     * @return de diepte van de deelboom
     */
    public int depthPart(Node n){
        int maxDepth = 0;
        if (n != null){
            int depthRight = depthPart(n.getRight());
            int depthLeft = depthPart(n.getLeft());
            if (depthRight < depthLeft){
                maxDepth = depthLeft + 1;
            }
            else {
                maxDepth = depthRight + 1;
            }
        }
        return maxDepth;
    }

    @Override
    public Iterator<Integer> iterator() {
       return new TreeIterator(root);
    }
}