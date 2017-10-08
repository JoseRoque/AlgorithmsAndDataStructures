public class RedBlackTree<K extends Comparable<K>,V> {
  RedBlackTreeNode<K,V> root;
  public RedBlackTree() {
    this.root = null;
  }

  public void add( K key, V value ) {
    // if root = null
  }

  static class RedBlackTreeNode<K,V> {
    K key;
    V value;
    RedBlackTreeNode left;
    RedBlackTreeNode right;
    RedBlackTreeNode parent;
    Color color;

    public RedBlackTreeNode( K key, V value, Color color ) {
      this.key = key;
      this.value = value;
      this.parent = null;
      this.left = null;
      this.right = null;
    }
    enum Color{
      RED, BLACK;
    }
  }
}
