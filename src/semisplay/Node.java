package semisplay;

/**
 * Klasse voor een top in de boom, houdt telkens zijn ouder, de sleutel en zijn rechter- en linkerkind bij.
 */
public class Node {

    private Node parent;
    private Node left;
    private Node right;
    private int value;

    public Node(Node parent, int value) {
        this.parent = parent;
        this.value = value;
    }

    /**
     * Functie die als de ouder verandert van deze node verandert, ook zal zorgen dat bij die ouder node het juiste
     * kind wordt ingesteld als nieuw kind
     * @param p de (nieuwe) ouder van deze node
     */
    public void setParent(Node p){
        if (parent != null){
            if (value < p.getValue()){
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

    public void removeChild(Node n){
        if (n.getValue() == left.getValue()){
            removeLeft();
        }
        else {
            removeRight();
        }
    }

    public void addLeft(Node c){
        left = c;
    }

    public void addRight(Node c){
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

    public Node getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getValue() {
        return value;
    }
}
