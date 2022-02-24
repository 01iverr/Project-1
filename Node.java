public class Node<T> {
    protected T data;
    protected Node next = null;

    Node(T data) {
        this.data = data;
    }

    public T getData() {
        // return data stored in this node
        return data;
    }

    public Node<T> getNext() {
        // get next node
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
