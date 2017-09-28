import java.util.*;
import java.lang.*;

public class BinaryTreeNode<K extends Comparable, V extends Comparable> implements Comparable<BinaryTreeNode<K, V>> {
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

  @Override
  public boolean equals( Object o ) {
    if( o == this ) {
      return true;
    }
    if(!( o instanceof BinaryTreeNode )) {
      return false;
    }

    BinaryTreeNode<K,V> c = (BinaryTreeNode<K,V>) o;
    return key.compareTo(c.key)
      && value.compareTo(c.value)
      && parent = c.parent
      && left = c.left
      && right = c.right;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + key.hashCode();
    result = 31 * result + value.hashCode();
    result = 31 * result + (parent == null ? 0 : parent.hashCode());
    result = 31 * result + (left == null ? 0 : left.hashCode());
    result = 31 * result + (right == null ? 0 : right.hashCode());
    return result;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Value: " + value + "\t" );
    sb.append("Left: " + ( left == null ? "NULL" : left.value) + "\t" );
    sb.append("Right: " + ( right == null ? "NULL" : right.value) );

    return sb.toString();
  }
}
