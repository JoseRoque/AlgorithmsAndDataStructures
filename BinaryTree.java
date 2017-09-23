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

  // min
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

  // successor
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

  // predecessor
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

  // transplant
  // delete

  public void printBinaryTree() {
    printBinaryTree(root);
  }

  private void printBinaryTree( BinaryTreeNode<K,V> p ) {
    if( p != null) {
      printBinaryTree(p.left);
      System.out.println(p.toString());
      printBinaryTree(p.right);
    }
  }
}
