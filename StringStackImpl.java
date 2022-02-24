import java.util.NoSuchElementException;
import java.io.PrintStream;
public class StringStackImpl<T> implements StringStack<T> {
	private Node<T> head = null;
    private Node<T> tail = null;
	int size=0;


	public StringStackImpl(){}
@Override
	public boolean isEmpty(){
		return head == null;
	}
	//@Override	
	public void push(T data){ //insertAtFront
		Node<T> n = new Node<>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head = n;
        }
		size++;
	}
	//@Override
	public T pop() throws NoSuchElementException{ //removeFromFront
		if (isEmpty())
            throw new NoSuchElementException("NoSuchElementException");

        T data = head.getData();

        if (head == tail){
            head = tail = null;
        }else{
            head = head.getNext();
		}
		size--;
        return data;
	}
	//@Override
	public T peek() throws NoSuchElementException{
		if (isEmpty()){
            throw new NoSuchElementException("NoSuchElementException");
		}
        T data = head.getData();
        return data;
	}

	//@Override
	public void printStack(PrintStream stream){
		if (isEmpty()){
			stream.println("EMPTY LIST");}
		else{
			Node  current = head;
			while(current != null) {
				stream.println(current.getData().toString());
				current =current.getNext();
				}	
			}
		}
	
	@Override
	public int size(){
		return size;
	}

}