
/**
 * The Node class for the AVL tree
 * Author: Lindokuhle Mdlalose
 * Date: 18 March 2024
 */
public class Node {
    Data data;
    Node left;
    Node right;
    int height;

    /**
     * Construct a new node with the provided data.
     * @param data The data to be stored in the node.
     */
    public Node(Data data){
        this.data = data;
    }

    /**
     * Retrieves the data stored in the node.
     * @return The data stored in the node.
     */
    public Data getData(){
        return data;
    }

    /**
     * Retrieves the left child node of the current node.
     * @return The left child node.
     */
    public Node getLeft(){
        return left;
    }

    /**
     * Retrieves the right child node of the current node.
     * @return The right child node.
     */
    public Node getRight(){
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
}
