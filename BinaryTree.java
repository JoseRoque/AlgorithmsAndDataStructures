import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

public class BinaryTree<K extends Comparable, V> {
  BinaryTreeNode<K,V> root;

  public BinaryTree() {
    this.root = null;
  }

  public void addBinaryTreeNode( K key, V value ) {
    BinaryTreeNode<K,V> n = new BinaryTreeNode<K,V>(key, value);
    if( root == null ) {
      root = n;
      n.parent = null;
      return;
    }
    addBinaryTreeNode( root, n );
  }

  public void addBinaryTreeNode( BinaryTreeNode<K,V> node, BinaryTreeNode<K,V> data ) {
    if(node != null && data.key.compareTo(node.key) < 0) {
      if( node.left == null ){
        node.left = data;
        data.parent = node;
      } else {
        addBinaryTreeNode(node.left, data);
      }
    } else if(node != null && data.key.compareTo(node.key) > 0){
      if( node.right == null ){
        node.right = data;
        data.parent = node;
      } else {
        addBinaryTreeNode(node.right, data);
      }
    }
  }

  public BinaryTreeNode<K,V> min() {
    BinaryTreeNode<K,V> n  = root;
    while( n != null ){
      n = root.left;
    }
    return root;
  }

  public BinaryTreeNode<K,V> max() {
    BinaryTreeNode<K,V> n  = root;
    while( n != null ){
      n = root.right;
    }
    return root;
  }

  public BinaryTreeNode<K,V> search( K key ){
    if( root == null ){
      return null;
    }
    return search(root, key);
  }

  public BinaryTreeNode<K,V> search(BinaryTreeNode<K,V> node, K key) {
    if( node == null ){
      return null;
    } else if ( node.key.compareTo( key ) == 0 ) {
      return node;
    } else if( node.key.compareTo( key ) > 0 ) {
      return search(node.left, key);
    } else {
      return search(node.right, key);
    }
  }

  public BinaryTreeNode<K,V> successor( K key ) {
    BinaryTreeNode<K,V> node = search(key);
    if( node == null ){ return null; }
    if( node.right != null ){
      return node.right;
    }

    BinaryTreeNode<K,V> parent = node.parent;
    while( parent != null && parent.right == node ) {
      node = parent;
      parent = parent.parent;
    }
    return parent;
  }

  public BinaryTreeNode<K,V> predecessor( K key ) {
    BinaryTreeNode<K,V> node = search(key);
    if( node == null ){ return null; }
    if( node.left != null ){
      return node.left;
    }

    BinaryTreeNode<K,V> parent = node.parent;
    while( parent != null && parent.left == node ) {
      node = parent;
      parent = parent.parent;
    }
    return parent;
  }
  
  private boolean isParentNull(BinaryTreeNode<K,V> node) {
    return node.parent == null ? false : true;
  }

  private boolean hasLeftChild(BinaryTreeNode<K,V> node) {
    return node.left == null ? false : true;
  }
  private boolean hasRightChild(BinaryTreeNode<K,V> node) {
    return node.right == null ? false : true;
  }

  public void printBinaryTree() {
    printBinaryTree(root);
  }

  private void printBinaryTree( BinaryTreeNode<K,V> p ) {
    if( p == null) {
      return;
    }
    printBinaryTree(p.left);
    System.out.println(p.value);
    printBinaryTree(p.right);
  }

  // Depth First Search
  public void DFS() {
    HashSet<BinaryTreeNode<K,V>> visited =
      new HashSet<BinaryTreeNode<K,V>>();
    DFS(root, visited);
  }

  private void DFS(BinaryTreeNode<K,V> node, HashSet<BinaryTreeNode<K,V>> visited ) {
    if( node == null ) {
      return;
    }
    // did we already visit node
    if( visited.contains(node)) {
      return;
    }
    // visit node
    System.out.println(node.value);
    // add to visited
    visited.add(node);
    // for each children, go deep
    for(BinaryTreeNode<K,V> child : Arrays.asList(node.left, node.right)) {
      if( !visited.contains(child) ){
        DFS(child, visited);
      }
    }
  }

  // Breadth First Search
  public void BFS(){
    ArrayList<BinaryTreeNode<K,V>> nextNode =
      new ArrayList<BinaryTreeNode<K,V>>();
    HashSet<BinaryTreeNode<K,V>> visited =
      new HashSet<BinaryTreeNode<K,V>>();

    nextNode.add(root);

    while( !nextNode.isEmpty() ) {
      // get next node to visited
      BinaryTreeNode<K,V> currentNode = nextNode.remove(0);

      // check if we visited already
      if( visited.contains(currentNode) ) {
        continue;
      }

      // visit
      System.out.println(currentNode.value);

      // add to visited
      visited.add(currentNode);

      // add its children to queue
      nextNode.addAll(
      Arrays.asList(currentNode.left, currentNode.right)
      .stream()
      .filter(a -> !(a == null))
      .collect(Collectors.toList()));
    }
  }
}
