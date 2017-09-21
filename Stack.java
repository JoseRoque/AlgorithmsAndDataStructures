public interface Stack {

    void push(Object item) throws StackException;

    Object pop() throws StackException ;

    Object peek() throws StackException;

    int size();

    boolean isEmpty();
}
