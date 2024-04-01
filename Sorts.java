import java.util.Arrays;

public class Sorts {

    public static <T extends Comparable<? super T>> void heapSort(T[] array) {
      int length = array.length;
      heapSort(array, length);
      T temp;
      for(int i = length -1; i >= 0; i--){
         temp = array[0];
         array[0] = array[i];
         array[i] = temp;
         heapSort(array, i, 0);
      }
    }
    private static <T extends Comparable<? super T>> void heapSort(T[] array, int length) {
      for(int i = (length/2-1); i >= 0; i--){
         heapSort(array, length, i);
      }
    }
    private static <T extends Comparable<? super T>> void heapSort(T[] array, int length, int i) {
     // parent is (array.length-1)/2;
     int biggest = i;
     int leftChild = 2 * biggest + 1;
     int rightChild = 2 * biggest + 2;
     T temp;
     // check both children left and right and update biggest if needed
     if(leftChild < length && array[leftChild].compareTo(array[biggest]) > 0){
      biggest = leftChild;
     }
     if(rightChild < length && array[rightChild].compareTo(array[biggest]) > 0){
      biggest = rightChild;
     }
     // switch positions if biggest is not in correct place
     if(biggest != i){
      temp = array[i]; 
      array[i] = array[biggest];
      array[biggest] = temp;
      heapSort(array, length, biggest);
     }
   }

}