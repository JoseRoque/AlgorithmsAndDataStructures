import java.util.*;
import java.lang.*;

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

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Value: " + value + "\t" );
    sb.append("Left: " + ( left == null ? "NULL" : left.value) + "\t" );
    sb.append("Right: " + ( right == null ? "NULL" : right.value) );

    return sb.toString();
  }
}
