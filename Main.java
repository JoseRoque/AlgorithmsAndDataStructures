import java.util.*;
import java.lang.*;

public class Main {

  public static void main( String[] args ){

  }

  // CSLR 2.3-4
  public static void insertionSortRecursive( int[] arr, int itemIndex ) {
    // base case
    if(itemIndex < 1){
      return;
    }

    insertionSortRecursive( arr, itemIndex-1);

    // check to see if wher
    int currentItem = arr[itemIndex];
    int i = itemIndex;     // assume item in correct position

    for( i= itemIndex; i > 0 && currentItem < arr[i-1]; i--) {
      arr[i] = arr[i-1];
    }

    arr[i] = currentItem;
  }

  // for each card i
  //  check to see if cards up to and including i-1 before card i are less than
  //    move card j up one spot
  public static void insertionSort(int[] arr) {
      int j=0;

      for(int i=1; i < arr.length; i++ ){
        int currentItem = arr[i];
        for(j=i; j>0 && currentItem < arr[j-1]; j--) {
          arr[j] = arr[j-1];
        }
        arr[j] = currentItem;
      }
  }

  // split the data in halve
  // further split both those halves into more halves
  // compare values in both halves and add to auxilary array sorted
  public static void mergeSort(int[] arr, int left, int right) {
    if( left >= right ) {
      return;
    }

    int middle = left + ((right- left)/2);
    // System.out.printf("Left= %d, Middle=%d, Right =%d\n", left, middle, right);

    mergeSort(arr, left, middle);
    mergeSort(arr, middle+1, right);
    merge( arr, left, middle, right );
  }

  private static void merge( int[] arr, int left, int middle, int right ){
    int leftSize = middle - left + 1;
    int rightSize = right - middle;
    System.out.printf("leftSize: %d, rightSize: %d\n", leftSize, rightSize);

    int[] leftArr = new int[leftSize + 1];
    int[] rightArr = new int[rightSize + 1];
    leftArr[leftSize] = Integer.MAX_VALUE;
    rightArr[rightSize] = Integer.MAX_VALUE;

    for(int i = 0; i < leftSize ; i++ ){
      leftArr[i] = arr[left+i];
    }
    System.out.printf("Left Arr: " + toString(leftArr));
    for(int j = 0; j < rightSize; j++){
      rightArr[j] = arr[middle+ j + 1]; // due to 0 based indices
    }

    System.out.println("Right Arr: " + toString(rightArr));
    int leftIndx =0, rightIndx = 0;
    int index = left;
    while( index <= right ){
      if ( leftArr[leftIndx] <= rightArr[rightIndx]) {
        arr[index] = leftArr[leftIndx];
        leftIndx++;
      }else {
        arr[index] = rightArr[rightIndx];
        rightIndx++;
      }
      index++;
    }
  }

  // choose pivot (could be random or just last end)
  // put items less than pivot on left, and those larger than the pivot to the right
  // swap choosen pivot where divison of numbers less than are to left, and those greater to the right (its in correct seat)
  // repeat above steps on subarray split about the pivot element (neither including it)
  public static void quickSort(int[] arr, int leftIndex, int rightIndex) {
    // base case 1 card in subset, by defaul sorted
    if( leftIndex >= rightIndex ) {
      return;
    }

    System.out.println("LeftINdex "+ leftIndex + " RightIndex: " + rightIndex);
    int pivot = arr[rightIndex];
    int i, j;
    for( i=leftIndex-1, j= leftIndex; j < rightIndex; j++) {
      if(arr[j] < pivot) {
        i++;
        swap(arr, i, j);
      }
    }
    swap(arr,i+1,rightIndex);

    quickSort(arr, leftIndex, i-1);
    quickSort(arr, i+1, rightIndex);
  }

  public static void quickSortRandomized( int[] arr, int leftIndex, int rightIndex ){
    if( leftIndex >= rightIndex ){
      return;
    }

    int lessGreaterThanDivision = leftIndex-1;
    int currentIndex;
    Random rand = new Random();
    int randomIndex = rand.nextInt((rightIndex - leftIndex) + 1 ) + leftIndex;

    System.out.println("random pivot");
    swap(arr, randomIndex, rightIndex);
    int pivot = arr[rightIndex];

    for (currentIndex = leftIndex; currentIndex <= rightIndex -1; currentIndex++ ) {
      if( arr[currentIndex] <= pivot ) {
        lessGreaterThanDivision++;
        System.out.println("swap");
        swap(arr, currentIndex, lessGreaterThanDivision);
      }
    }

    System.out.println("correct placement");
    swap(arr, lessGreaterThanDivision+1, rightIndex );

    quickSortRandomized(arr, leftIndex, lessGreaterThanDivision );
    quickSortRandomized(arr, lessGreaterThanDivision+2, rightIndex );
  }

  private static void swap( int[] arr, int index1, int  index2) {
      int temp = arr[index1];
      System.out.println(String.format("Index1: %d -> Index2: %d", index1, index2));
      arr[index1] = arr[index2];
      arr[index2] = temp;
  }

  public static void printArr(int[] arr) {
    for( int i : arr ){
      System.out.printf("%d ", i);
    }
  }

  public static String toString(int [] arr) {
    StringBuilder sB = new StringBuilder();
    if( arr.length == 0) {
      return sB.append("EMPTY ARRAY\n").toString();
    }

    for( int i : arr ){
      sB.append(i).append(" ");
    }
    return sB.append("\n").toString();
  }

  public static <T extends Collection> void print( T list ){
    list.forEach(System.out::println);
  }

  // for every parent node apply maxHeapify to restore or maintain heap characteristic
  public static void createHeap(int[] arr){
    for( int index= arr.length /2; index >= 0 ; index--) {
      maxHeapify(index, arr);
    }
  }

  // NOTE: there is heap capacity but heap may not be full, so there is also used capacity (heap size)

  public static void maxHeapify( int parentIndex, int[] arr) {
    int largestIndex = 0;
    // compare parent to left child and get largest
    if( isValidIndex( arr, getLeftChildIndex(parentIndex) ) &&
      arr[getLeftChildIndex(parentIndex)] >= arr[parentIndex] ) {
       largestIndex = getLeftChildIndex(parentIndex);
    } else {
       largestIndex = parentIndex;
    }
    // compare largest to right child
    if( isValidIndex( arr, getRightChildIndex(parentIndex) ) &&
      arr[getRightChildIndex(parentIndex)] >= arr[largestIndex] ) {
      largestIndex = getRightChildIndex(parentIndex);
    }

    // new parent if largest value found from left, right. and initial parent
    if( parentIndex != largestIndex ) {
      swap(arr, parentIndex, largestIndex);
      maxHeapify(largestIndex, arr);
    }
  }

  public static int heapMax(int[] arr) {
    // validate not null or empty
    return arr[0];
  }

  // replace max w/ last value in heap
  // maxHeapify at the root
  // maxHeapify if childBinaryTreeNode or root was switched with root
  public static int heapExtractMax(int[] arr) {
    int temp = arr[0];
    arr[0] = arr[arr.length-1];
    arr[arr.length-1] = -1;
    maxHeapify(0, arr);
    return temp;
  }

  // check if heap is full
  // if so add extra space
  // traverse upward comparing newly inserted value to its parent
  // to determine its new positioning
  public static void heapMaxInsert(int[] arr, int valueToInsert) {
    int currentIndex = arr.length -1;
    arr[arr.length-1] = valueToInsert;

    // not at the root
    while ( currentIndex > 0 && valueToInsert >= arr[getParentIndex(currentIndex)]) {
      swap(arr, currentIndex, getParentIndex(currentIndex) );
      currentIndex = getParentIndex(currentIndex);
    }
    arr[currentIndex] = valueToInsert;
  }

  public static boolean isValidIndex( int[] arr, int index) {
    return index <= arr.length -1 ? true : false;
  }

  public static int getParentIndex(int childIndex) {
    return childIndex / 2;
  }

  public static int getLeftChildIndex( int parentIndex ) {
    return (parentIndex) * 2 + 1;
  }

  public static int getRightChildIndex( int parentIndex ) {
    return (parentIndex) * 2 + 2 ;
  }


  // find max
  // for LSB to MSB of MAX_DIGIT.len
  //   create MAX_DIGIT.len buckets
  //   // go through all values and add them to bucket
  //   // recombine all buckets
  public static void radixSort(int[] arr) {
    int max = max(arr);
    int place = 1;

    while(place < max) {
      // place = place*10;
      List<Integer>[] buckets = new List[10];
      for (int i = 0; i < 10; i++) {
          buckets[i] = new ArrayList<>();
      }

      for( int val: arr ){
        int bucketNum=0;
        if( val / place == 0) {
            if(val>=0 && val <=9 && place == 1){
              bucketNum = val;
            } else {
              bucketNum = 0;
            }
        } else {
           bucketNum = (val / place) % 10;
        }

        buckets[bucketNum].add(val);
      }

      int index = 0;
      for( List<Integer> bucket: buckets) {
        for( Integer v : bucket) {
          arr[index++] = v;
        }
      }
      place = place*10;
    }
  }

  public static int max(int arr[]) {
    int max = arr[0];
    for(int i: arr){
      if( i > max) {
        max = i;
      }
    }
    return max;
  }

  // select ith smallest value in array
  // Steps:
  // 1. split about some random index
  // 2. recurse on left or recurse on right to find value
  //
  public static int selection(int[] arr, int leftIndex, int rightIndex, int i ) {
    // base case
    if( leftIndex >= rightIndex ){
      return arr[leftIndex];
    }

    int pivot = randomizedPartition(leftIndex, rightIndex);
    int leftSize = pivot - leftIndex + 1;

    if(i == pivot) {
      return arr[pivot];
    }else if( i < pivot ) {
      return selection(arr, leftIndex, pivot -1, i);
    } else {
      return selection(arr, pivot+1, rightIndex, i - leftSize);
    }
  }

  public static int randomizedPartition(int leftIndex, int rightIndex) {
    return new Random().nextInt(rightIndex - leftIndex + 1) + leftIndex;
  }
}
