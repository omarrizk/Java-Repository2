import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {

   public void insert(E data){
      Node<E> temp = new Node<E>(data);
      root = insert(root, temp);
   }
   
   private Node<E> insert(Node<E> curr, Node<E> node){
      if(curr == null){
         return node;
      }
      else if(node.data.compareTo(curr.data) <= 0){
        curr.right = insert(curr.right, node);
      }
      else if(curr.left == null){
         curr.left = insert(curr.left, node);
      }
      return curr;
   }
  private Node<E> findIOP(Node<E> curr) {
        for (curr = curr.left; curr.right != null; curr = curr.right);
        return curr;
    }
     
  public void remove(E data){
      root = remove(root, data);
   }
 
  private Node<E> remove(Node<E> c, E data){
    Node<E> curr;
    if (c != null){
        if (data.compareTo(c.data) == 0) {
            if (c.left == null || c.right == null) {
                c = c.left != null ? c.left : c.right;
                return c;
            } else {
                Node<E> iop = findIOP(c);
                E temp = c.data;
                c.data = iop.data;
                iop.data = temp;
                if (c.left == iop) {
                    c.left = c.left.left;
                    return c;
                } else {
                    curr = c.left;
                    if(curr.right != iop) {
                        curr = curr.right;
                        remove(curr, data);
                    }
                    curr.right = curr.right.left;
                    return c;
                }
            }
        } else {
            if(data.compareTo(c.data) < 0) {
                c.left = remove(c.left, data);
                if(c.left != null){
                    return c.left;
                }
            } else {
                c.right = remove(c.right, data);
                if(c.right != null){
                    return c.right;
                }
            }
            return c;
        }
    }
    return c;
  }

   public boolean search(E data){
      Node<E> curr = root;
      if(search(data, curr))
         return true;
      else
         return false;
   }
   
   private boolean search(E data, Node<E> curr) {
      if(curr != null){
         if(data.compareTo(curr.data) == 0){
            return true;
         }
         else if(data.compareTo(curr.data) < 0){
            curr = curr.left;
            search(data, curr);
         }
         else {
            curr = curr.right;
            search(data, curr);
         }
         }
      return false;
   } 
   public Iterator<E> iterator() {
      list = new ArrayList<E>();
      traverse(root);
      return list.iterator();
    }
   private void traverse(Node<E> curr){
      if(curr != null){
         traverse(curr.left);
         list.add(curr.data);
         traverse(curr.right);
      }
   } 
   private ArrayList<E> list;  
}

