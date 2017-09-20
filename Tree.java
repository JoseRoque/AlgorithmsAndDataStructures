public class Tree<K extends Comparable, V> {
  Node<K,V> root;

  public Tree() {
    this.root = null;
  }

  public void addNode( K key, V value ) {
    Node<K,V> n = new Node<K,V>(key, value);
    if( root == null ) {
      root = n;
      return;
    }
    addNode( root, n );
  }

  public void addNode( Node<K,V> node,  Node<K,V> data ) {
      if(node != null && data.key.compareTo(node.key) < 0) {
        if( node.left == null ){
          node.left = data;
        } else {
          addNode(node.left, data);
        }
      } else if(node != null && data.key.compareTo(node.key) > 0){
        if( node.right == null ){
          node.right = data;
        } else {
          addNode(node.right, data);
        }
      }

  }
  public void printTree() {
    printTree(root);
  }

  private void printTree(Node<K,V> p) {
    if( p == null) {
      return;
    }

    printTree(p.left);
    System.out.println(p.value);
    printTree(p.right);
  }
} // end Tree
