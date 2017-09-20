public class BinaryTree<K extends Comparable, V> {
  BinaryTreeNode<K,V> root;

  public BinaryTree() {
    this.root = null;
  }

  public void addBinaryTreeNode( K key, V value ) {
    BinaryTreeNode<K,V> n = new BinaryTreeNode<K,V>(key, value);
    if( root == null ) {
      root = n;
      return;
    }
    addBinaryTreeNode( root, n );
  }

  public void addBinaryTreeNode( BinaryTreeNode<K,V> node, BinaryTreeNode<K,V> data ) {
    if(node != null && data.key.compareTo(node.key) < 0) {
      if( node.left == null ){
        node.left = data;
      } else {
        addBinaryTreeNode(node.left, data);
      }
    } else if(node != null && data.key.compareTo(node.key) > 0){
      if( node.right == null ){
        node.right = data;
      } else {
        addBinaryTreeNode(node.right, data);
      }
    }
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
}
