import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

public class BinaryTree<K extends Comparable, V extends Comparable> {
  BinaryTreeNode root;

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

  public void inOrder() {
    inOrder(root);
  }

  private void inOrder(BinaryTreeNode<K,V> node) {
    if(node != null) {
      inOrder(node.left);
      System.out.println(node.value);
      inOrder(node.right);
    }
  }

  public void preOrder() {
    preOrder(root);
  }

  private void preOrder(BinaryTreeNode<K,V> node) {
    if(node == null) {
      return;
    }
    System.out.println(node.value);
    preOrder(node.left);
    preOrder(node.right);
  }

  public void addBinaryTreeNodeIterive( K key, V value) {
    if( root == null ) {
      root = new BinaryTreeNode<K,V>(key, value);
      return;
    }

    BinaryTreeNode<K,V> currentNode = null;
    BinaryTreeNode<K,V> nextNode = root;

    while( nextNode != null ) {
      currentNode = nextNode;
      if( key.compareTo(currentNode.key) < 0 ) {
        nextNode = currentNode.left;
      } else {
        nextNode = currentNode.right;
      }
    }

    if( key.compareTo(currentNode.key) < 0 ){
      addChildToNode( currentNode.left ,new BinaryTreeNode<K,V>( key, value));
    } else {
      addChildToNode( currentNode.right ,new BinaryTreeNode<K,V>( key, value));
    }
  }

  public void addChildToNode(BinaryTreeNode<K,V> parent, BinaryTreeNode<K,V> child ){
    parent = child;
    child.parent = parent.parent;
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

  public BinaryTreeNode<K,V> min(BinaryTreeNode<K,V> n) {
    while( n != null ){
      n = root.left;
    }
    return root;
  }

  public BinaryTreeNode<K,V> min() {
    return min(root);
  }

  public BinaryTreeNode<K,V> max(BinaryTreeNode<K,V> n) {
    while( n != null ){
      n = root.right;
    }
    return root;
  }

  public BinaryTreeNode<K,V> max() {
    return max(root);
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
      return min(node.right);
    }

    BinaryTreeNode<K,V> parent = node.parent;
    while( parent != null && parent.right == node ) {
      node = parent;
      parent = parent.parent;
    }
    return parent;
  }

  public BinaryTreeNode<K,V> successor( BinaryTreeNode<K,V> node ) {
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

  public BinaryTreeNode<K,V> predecessor( BinaryTreeNode<K,V> node ) {
    if( node == null ){ return null; }
    if( node.left != null ){
      return max(node.left);
    }

    BinaryTreeNode<K,V> parent = node.parent;
    while( parent != null && parent.left == node ) {
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

  public boolean delete(K key) {

    BinaryTreeNode<K,V> nodeToReplace =search(key);
    if( nodeToReplace == null ){
      return false;
    }
    delete(nodeToReplace);
    return true;
  }

  private void delete(BinaryTreeNode<K,V> nodeToReplace) {
    if( !hasRightChild(nodeToReplace) && !hasLeftChild(nodeToReplace) ) {
      if(!isParentNull(nodeToReplace)
      && (nodeToReplace.parent.left.key.compareTo(nodeToReplace) == 0) ) {
        nodeToReplace.parent.left = null;
      } else if(!isParentNull(nodeToReplace)
      && (nodeToReplace.parent.right.key.compareTo(nodeToReplace) == 0) ) {
        nodeToReplace.parent.right = null;
      }
      System.out.println("Deleted nodeToReplace w/ value: " + nodeToReplace.value );
      return;
    }
    // case 1: if only has left child
    if( !hasRightChild(nodeToReplace) && hasLeftChild(nodeToReplace) ) {
      BinaryTreeNode<K,V> predecessor = predecessor(nodeToReplace);
      copyContents(nodeToReplace, predecessor);
      delete(predecessor);
    }
    // case 2: if only had right child
    else if( hasRightChild(nodeToReplace) && !hasLeftChild(nodeToReplace) ) {
      BinaryTreeNode<K,V> successor = successor(nodeToReplace);
      copyContents(nodeToReplace, successor);
      delete(successor);
    }
    // case 3: if has both left and right child
    if( hasRightChild(nodeToReplace) && hasLeftChild(nodeToReplace) ) {
      BinaryTreeNode<K,V> successor = successor(nodeToReplace);
      // copy contents over from succesor
      copyContents(nodeToReplace, successor);
      delete(successor);
    }
  }

  public void copyContents(BinaryTreeNode<K,V> toReplace, BinaryTreeNode<K,V> replacement) {
    K tempKey = toReplace.key;
    V tempVal = toReplace.value;

    toReplace.key = replacement.key;
    toReplace.value = replacement.value;

    replacement.key = tempKey;
    replacement.value = tempVal;
  }

  // public void transport(BinaryTreeNode<K,V> transportTo,
  //  BinaryTreeNode<K,V> transportFrom) {
  //
  //    if( transportTo.parent == null ) {
  //      root = transportFrom;
  //    } else if( transportTo.parent.left.key.compareTo() ){
  //
  //    } else if() {
  //
  //    }
  // }

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
