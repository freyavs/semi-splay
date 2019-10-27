package semisplay;

import java.util.Iterator;

public class SemiSplayTree<E extends Comparable<E>> implements SearchTree<E>{

    private Node<E> root;
    private int size;
    private int splaysize; //werkt nog niet met splayen

    public SemiSplayTree(Integer splaysize){
        this.splaysize = splaysize;
    }

    /** Voeg de gegeven sleutel toe aan de boom als deze er nog niet in zit.
     * @return true als de sleutel effectief toegevoegd werd. */
    public boolean add(E n){
        if (root == null){
            root = new Node<>(null, n);
            size++;
            return true;
        }
        else {
            Node<E> node = search(n);
            if (node.getValue().compareTo(n) < 0) {
                node.addRight(new Node<>(node, n));
                size++;
            } else if (node.getValue().compareTo(n) > 0) {
                node.addLeft(new Node<>(node, n));
                size++;
            }
            return node.getValue() != n;
        }
    }

    /**
     * @return wortel van de zoekboom, methode wordt gebruikt in tests*/
    public Node<E> getRoot() {
        return root;
    }

    /**
     * Hulpfunctie voor andere methodes
     * @param n sleutel die we in de boom zoeken
     * @return Als node met sleutel gelijk aan n is gevonden, dan returnen we die node, anders returnen we
     * de node die n als kind zou kunnen hebben. Als de boom leeg is (wortel is null) return null;*/
    public Node<E> search(E n){
        Node<E> node = null;
        Node<E> next = root;
        if (root != null) {
            while (node == null || node.getValue() != next.getValue()) {
                node = next;
                if (n.compareTo(next.getValue()) < 0 && next.hasLeft()) {
                    next = node.getLeft();
                } else if (n.compareTo(next.getValue()) > 0 && next.hasRight()) {
                    next = node.getRight();
                }
            }
        }
        return next;
    }

    /** Zoek de gegeven sleutel op in de boom.
     * @return true als de sleutel gevonden werd. */
    public boolean contains(E n){
        Node<E> node = search(n);
        return node != null && node.getValue().compareTo(n) == 0;
    }

    /** Verwijder de gegeven sleutel uit de boom.
     * @return true als de sleutel gevonden en verwijderd werd. */
    public boolean remove(E n){
        Node<E> toRemove = search(n);
        if (toRemove != null && toRemove.getValue().compareTo(n) == 0) {
            Node<E> replacement = findReplacement(toRemove);
            //replacement is null als de te verwijderen top een blad is
            if (replacement != null){
                //de vervangende top heeft linkerkind
                if (replacement.hasLeft()){
                    Node<E> p = replacement.getParent();
                    replacement.getLeft().setParent(p);
                }
                //de vervangende top heeft rechterkind
                else if (replacement.hasRight()) {
                    Node<E> p = replacement.getParent();
                    replacement.getRight().setParent(p);
                }
                //start met vervangen van de te verwijderen top door de vervangende top
                replacement.getParent().removeChild(replacement); //als de replacement al verwijderd was hierboven zal dit niets doen
                Node<E> left = toRemove.getLeft();
                Node<E> right = toRemove.getRight();
                replacement.setParent(toRemove.getParent());

                //als de parent van de verwijderde top null was, was dit de wortel
                if (toRemove.getParent() == null){
                    root = replacement;
                }
                if (right != null) {
                    right.setParent(replacement);
                }
                if (left != null) {
                    left.setParent(replacement);
                }
                replacement.addRight(right);
                replacement.addLeft(left);
            }
            else {
                toRemove.getParent().removeChild(toRemove);
            }
            size--;
        }
        return toRemove != null && toRemove.getValue().compareTo(n) == 0;
    }


    /**
     * Gebruik deze methode wanneer een node moet verwijderd worden uit de boom, en we hiervoor zijn vervanger
     * willen vinden.
     * @param node de node waarvoor we een vervanger moeten vinden
     * @return null als de node geen kinderen heeft, anders oftewel de kleinste node in de grote deelboom of
     * grootste node in de kleine deelboom*/
    public Node<E> findReplacement(Node<E> node){
        Node<E> replacement = null;
        Node<E> tmp;
        //zoek kleinste in grote deelboom
        if (node.hasRight()){
            replacement = node.getRight();
            while (replacement.hasLeft()){
                tmp = replacement;
                replacement = tmp.getLeft();
            }
        }
        //zoek grootste in kleine deelboom
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

    /** @return de diepte van de boom. Returnt -1 als boom leeg is. */
    public int depth(){
        return depthPart(root) - 1;
    }

    /**
     * Methode zodat we recursief kunnen zoeken naar de diepte van linker en rechterboom, en de grootste diepte
     * teruggeven.
     * @param n de node die de wortel is van de deelboom die we aan het doorzoeken zijn
     * @return de diepte van de langste deelboom
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
    public Iterator<E> iterator() {
       return new TreeIterator<>(root);
    }
}