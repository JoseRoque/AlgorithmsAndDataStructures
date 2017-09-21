public class ArrayBackedStack<T extends Comparable<T>> implements Stack {
    private Object[] stack;
    private int capacity;
    private int size;

    public ArrayBackedStack(int capacity) {
      if(capacity < 0){
        throw new IllegalArgumentException("Stack's capacity must be positive");
      }
      this.stack = new Object[capacity];
      this.size = 0;
      this.capacity = capacity;
    }

    @Override
    public T peek() throws StackException {
      if( isEmpty() ){ throw new StackException("stack is empty"); }
      return (T) stack[size-1];
    }

    @Override
    public void push(Object t) throws StackException{
      if( size == capacity ){ throw new StackException("stack is full"); }
      stack[++size] = t;
    }

    @Override
    public T pop() throws StackException {
      if( isEmpty() ){ throw new StackException("stack is empty"); }
      return (T) stack[--size];
    }

    public int search(Object o) {
      int distance = 1;
      while( distance <= size ) {
        if( stack[distance-1].equals(o)) {
          break;
        }
      }
      return distance;
    }

    @Override
    public int size() {
      return size;
    }

    @Override
    public boolean isEmpty() {
      return size == 0 ? true : false;
    }
}
