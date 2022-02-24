import java.io.PrintStream;
import java.util.NoSuchElementException; 

public class  IntQueueImpl<T> implements IntQueue<T> {
	private Node<T> head = null;
    private Node<T> tail = null;
	int size=0;
	
	public IntQueueImpl(){}
	@Override
	public boolean isEmpty(){
		return head == null;
	}
	public void put(T data){ //insertAtBack
		Node<T> n = new Node<>(data);
        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
		size++;
    }
	public T get() throws NoSuchElementException{
		if (isEmpty()){
            throw new NoSuchElementException("NoSuchElementException");
		}
        T data = head.getData();

        if (head == tail)
            head = tail = null;
        else
            head = head.getNext();
		size--;
        return data;
    }
	public T peek() throws NoSuchElementException{
		if (isEmpty()){
            throw new NoSuchElementException("NoSuchElementException");
		}
        T data = head.getData();
        return data;
	}
	public void printQueue(PrintStream stream){
		if (isEmpty()){
			stream.println("EMPTY LIST");}
		else{
			Node  current = head;
			while(current != null) {
				stream.println(current.getData());
				current =current.getNext();
				}	
			}
		}
	public int size(){
		return size;
	}
}


