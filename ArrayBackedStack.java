public class ArrayBackedStack<T extends Comparable<T>> implements Stack {
    private Object[] stack;
    private int capacity;
    private int size;

    public ArrayBackedStack(int capacity) {
      this.stack = new Object[capacity];
      this.size = 0;
      this.capacity = capacity;
    }

    @Override
    public T peek() {
      if( isEmpty() ){ throw new EmptyStackException(); }
      return (T) stack[size-1];
    }

    @Override
    public void push(Object t) {
      if( size == capacity ){ throw new Exception(); }
      stack[++size] = t;
    }

    @Override
    public T pop() {
      if( isEmpty() ){ throw new EmptyStackException(); }
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
    public boolean isEmpty() {
      return size == 0 ? true : false;
    }
}
