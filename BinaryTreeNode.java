public class BinaryTreeNode<K extends Comparable, V> implements Comparable<BinaryTreeNode<K, V>> {
  K key;
  V value;
  BinaryTreeNode<K, V> parent;
  BinaryTreeNode<K, V> left;
  BinaryTreeNode<K, V> right;

  public BinaryTreeNode( K key, V value ) {
    this.key = key;
    this.value = value;
    this.parent = null;
    this.left = null;
    this.right = null;
  }

  @Override
  public int compareTo( BinaryTreeNode<K, V> o ) {
    if( o == null ) {
      return 0;
    }
    if( o.getClass() != this.getClass() ) {
      return 0;
    }
    return this.key.compareTo( o.key );
  }
}
