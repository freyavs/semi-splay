package semisplay;

/**
 * Klasse voor een top in de boom, houdt telkens zijn ouder, de sleutel en zijn rechter- en linkerkind bij.
 */
public class Node<E extends Comparable<E>> {

    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;
    private E value;

    public Node(Node<E> parent, E value) {
        this.parent = parent;
        this.value = value;
    }

    /**
     * Functie die als de ouder verandert van deze node verandert, ook zal zorgen dat bij die ouder node het juiste
     * kind wordt ingesteld als nieuw kind
     * @param p de (nieuwe) ouder van deze node
     */
    public void setParent(Node<E> p){
        if (parent != null && p != null){
            if (value.compareTo(p.getValue()) < 0){
                p.removeLeft();
                p.addLeft(this);
            }
            else {
                p.removeRight();
                p.addRight(this);
            }
        }
        parent = p;
    }

    public void removeChild(Node<E> n){
        if (left != null && n.getValue().compareTo(left.getValue()) == 0){
            removeLeft();
        }
        else if (right != null && n.getValue().compareTo(right.getValue()) == 0){
            removeRight();
        }
    }


    public E getValue() {
        return value;
    }

    public void addLeft(Node<E> c){
        left = c;
    }

    public void addRight(Node<E> c){
        right = c;
    }

    public void removeLeft(){
        left = null;
    }

    public void removeRight(){
        right = null;
    }

    public Boolean hasLeft(){
        return left != null;
    }

    public Boolean hasRight(){
        return right != null;
    }

    public Boolean hasChild(){
        return left != null || right != null;
    }

    public Node<E> getParent() {
        return parent;
    }

    public Node<E> getLeft() {
        return left;
    }

    public Node<E> getRight() {
        return right;
    }
}
