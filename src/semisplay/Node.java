package semisplay;

/**
 * Klasse voor een node, houdt telkens zijn ouder, de sleutel en zijn rechter- en linkerkind bij.
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

    public void changeParent(Node p) {
        if (this.getValue() < p.getValue()){
            p.removeLeft();
            p.addLeft(this);
        }
        else {
            p.removeRight();
            p.addRight(this);
        }
        parent = p;
    }

    public void setParent(Node p){
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
        c.setParent(this);
    }

    public void addRight(Node c){
        right = c;
        c.setParent(this);
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
