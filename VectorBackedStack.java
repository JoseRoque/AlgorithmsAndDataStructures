import java.util.*;
import java.lang.*;

public class VectorBackedStack<T> implements Stack {
  private Vector<T> stack;

  public VectorBackedStack(int capacity) {
    if(capacity < 0){
      throw new IllegalArgumentException("Stack's capacity must be positive");
    }
    this.stack = new Vector(capacity);
  }

  @Override
  public T peek() throws StackException {
    if( stack.isEmpty() ){ throw new StackException("stack is empty"); }
    return (T) stack.elementAt( stack.size() - 1 );
  }

  @Override
  public void push(Object t) throws StackException{
    if( stack.size() == stack.capacity() ){ throw new StackException("stack is full"); }
    stack.add((T) t); // added to end
  }

  @Override
  public T pop() throws StackException {
    if( isEmpty() ){ throw new StackException("stack is empty"); }
    return (T) stack.remove( stack.size() - 1); // remove from end
  }

  @Override
  public int size() {
    return stack.size();
  }

  @Override
  public boolean isEmpty() {
    return stack.size() == 0 ? true : false;
  }
}
