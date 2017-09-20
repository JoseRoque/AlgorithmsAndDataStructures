public class Node<K extends Comparable, V> implements Comparable<Node<K,V>>{
  K key;
  V value;
  Node<K, V> parent;
  Node<K, V> left;
  Node<K, V> right;

  public Node(K key, V value) {
    this.key = key;
    this.value = value;
    this.parent = null;
    this.left = null;
    this.right = null;
  }

  @Override
  public int compareTo(Node<K, V> o) {
    if(o == null) {
        return 0;
    }
    if( o.getClass() != this.getClass() ) {
        return 0;
    }
    return this.key.compareTo(o.key);
  }

}
