import java.util.Iterator;
import java.util.Random;
public class SortedList<E extends Comparable<? super E>> extends List<E>{
    public boolean search(E key){
      if(search(head, key))
         return true;
      else
         return false;
    }
   private boolean search(Node<E> head, E key){
      if(head == null)
         return false;
      if(head.data == key)
         return true;
      return search(head.next,key);
     }

	public E retrieve(int index){
     Node<E> curr = head;
     int myIndex = 0;
     return(retrieve(index, myIndex, curr));
     }
     
    public E retrieve(int index, int myIndex, Node<E> curr){
       if(myIndex == index)
         return curr.data;
       if(curr.next != null){
         myIndex+=1;
         return retrieve(index, myIndex, curr.next);   
        }
        return null;
    }

   /*
   private void insert(E data, Node<E> curr, Node<E> prev){
      Node<E> temp = new Node<E>(data);
      if(curr == null || curr.data.compareTo(data) < 0){
          if(prev==null){
            temp.next = head;
            head = temp;
            }
     }
     else if(curr.data.compareTo(data) < 0 && curr.next.data.compareTo(data) > 0)){
            temp.next = prev.next;
            prev.next = temp;
           //might need to check if curr is null
      }
      //check for duplicate values then do recursive run 
      else{
         insert(data,curr.next,temp);
    }
   } 
   */
   public void insert(E value){
      Node<E> temp = new Node<E>(value);
      insert(null, head, temp);
   } 
   private void insert(Node<E> prev, Node<E> curr,Node<E> temp){
      if(curr == null ||temp.data.compareTo(curr.data) < 0){
         if(prev == null){
            temp.next = head;
            head = temp;
            }
            else{
               temp.next = prev.next;
               prev.next = temp;
               }
         }
       else
         insert(curr, curr.next, temp);
   } 
   
	public void remove(E data){
		Node<E> curr = head;
      remove(data, curr, null);
	}
   private void remove(E key, Node<E> curr, Node<E> prev){
      if(curr == null){
         return;
      }
      if(key.compareTo(curr.data) == 0){
         if(prev == null){
            head = curr.next;
         }
         else
            prev.next=curr.next;
      }
      else if(curr.data.compareTo(key)>0){
         return;
      }
      else
         remove(key, curr.next,curr);
   }
   
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            public boolean hasNext() {
                return curr != null;
            }
            public E next() {
                E temp = curr.data;
                curr = curr.next;
                return temp;
            }
            private Node<E> curr = head;
        };
    }
}

