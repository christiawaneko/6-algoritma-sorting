// swap method untuk mempermudah
private  void swap(String[ ] data, int index1, int index2){
        if (index1==index2) return;
        String temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

// 1. SELECTION SORT
public  void selectionSort(String[ ] data){
        // for each position, from 0 up, find the next smallest item
        // and swap it into place
        assert data!=null : "Argument should not be null" ;
        int back = data.length-1;
        for (int front = 0; front < back; front++){
            int minIndex = front;
            int maxIndex = back;
            // check the lowest value then swap it with the front
            for (int sweep = front+1; sweep <= back; sweep++){
                if (data[sweep].compareTo(data[minIndex]) < 0) minIndex=sweep;
            }
            swap(data, front, minIndex);
            // check the highest value then swap it with the back
            for (int sweep = back-1; sweep > front; sweep--){
                if (data[sweep].compareTo(data[maxIndex]) > 0) maxIndex=sweep;
            }
            swap(data, back, maxIndex);
            back--;
        }
    }

// 2. BUBBLE SORT
public  void bubbleSort(String[] data){
        // Repeatedly scan array, swapping adjacent items if out of order
        // Builds a sorted region from the end
        int front = 0;
        int back = data.length-1;
        while (front < back){
            // compare the highest value then swap it with the back
            for (int sweep = front; sweep < back; sweep++){
                if (data[sweep].compareTo(data[sweep+1]) > 0) swap(data, sweep, sweep+1);
            }
            back--;
            // compare the lowest value then swap it with the front
            for (int sweep = back; sweep > front; sweep--){
                if (data[sweep].compareTo(data[sweep-1]) < 0) swap(data, sweep, sweep-1);
            }
            front++;
        }
    }

// 3. INSERTION SORT
// DITINGKATKAN DENGAN METODE BINARY SEARCH
public  void insertionSort(String[] data){
        // for each item, from 0, insert into place in the sorted region (0..i-1)
        for (int i=1; i<data.length; i++){
            String item = data[i];
            int place = findIndex(data, item, 0, i-1);
            for (int j = i; j > place ; j--){
                data[j] = data[j-1];
            }
            data[place]= item;
        }
    }

    private int findIndex(String[] data, String s, int min, int max){
        // YOUR CODE HERE
        while (true){
            int mid = (min+max)/2;
            int compValue = s.compareTo(data[mid]);
            if (min == max){
                if (compValue > 0) return mid+1;
                return mid;
            }
            if (compValue == 0){
                return mid;
            }
            if (compValue < 0){
                max = mid;
            }
            else if (compValue > 0){
                min = mid+1;
            }
        }
    }

// 4. MERGE SORT
public void mergeSort(String[] data, String[] temp, int low, int high) {
        if(low < high-1) {
            int mid = ( low + high ) / 2;
            mergeSort(temp, data, low, mid);
            mergeSort(temp, data, mid, high);
            merge(temp, data, low, mid, high);
        }
    }

    /** Iterative Merge Sort
     * Modified Merge Sort using for loop
     * Splitting elements into 2^n subrange and merge them
     * The remainder elements will be merged with previous merged elements
     */
    public void mergeSort(String[] data) {
        for (int range = 2; range < data.length; range = range*2){
            int startPos = 0;
            int endPos = 0;
            for (int start = 0; start < data.length; start+=range){
                String[] other = new String[data.length];
                for (int i = 0; i < data.length; i++) other[i] = data[i];
                if (data.length - start >= range){
                    int low = start;
                    int high = start+range;
                    int mid = (low+high)/2;
                    merge(other, data, low, mid, high);
                    startPos = low;
                    endPos = high;
                }
                else {
                    merge(other, data, startPos, endPos, data.length);
                }
            }
        }
    }

    /** Merge method
     *  Merge from[low..mid-1] with from[mid..high-1] into to[low..high-1]
     *  Print data array after merge using printData
     */
    public void merge(String[] from, String[] to, int low, int mid, int high) {
        int index = low;      //where we will put the item into "to"
        int indxLeft = low;   //index into the lower half of the "from" range
        int indxRight = mid; // index into the upper half of the "from" range
        while (indxLeft<mid && indxRight < high) {
            if ( from[indxLeft].compareTo(from[indxRight]) <=0 )
                to[index++] = from[indxLeft++];
            else
                to[index++] = from[indxRight++];
        }
        // copy over the remainder. Note only one loop will do anything.
        while (indxLeft<mid)
            to[index++] = from[indxLeft++];
        while (indxRight<high)
            to[index++] = from[indxRight++];
    }

// 5. MERGE SORT
public  void quickSort(String[ ] data) {
        quickSort(data, 0, data.length);
    }

    public  void quickSort(String[ ] data, int low, int high) {
        if (high-low < 2)      // only one item to sort.
            return;
        else {     // split into two parts, mid = index of boundary
            int mid = partition(data, low, high);
            // insertionSort
            if(high-low < 6){
                insertionSort(data, low, high);
                return;
            }
            quickSort(data, low, mid);
            quickSort(data, mid, high);
        }
    }

// 6. TIMSORT (KOMBINASI)
public  void insertionSort(String[] data, int low, int high){
        // for each item, from 0, insert into place in the sorted region (0..i-1)
        for (int i=low+1; i<high; i++){
            String item = data[i];
            int place = i;
            while (place > 0  &&  item.compareTo(data[place-1]) < 0){
                data[place] = data[place-1];       // move up
                place--;
            }
            data[place]= item;
        }
    }

    /** Partition into small items (low..mid-1) and large items (mid..high-1)
     *  Print data array after partition
     */
    private int partition(String[] data, int low, int high) {
        String pivot = data[(low+high)/2];
        int left = low-1;
        int right = high;
        while( left < right ) {
            do {
                left++;       // just skip over items on the left < pivot
            }
            while (left<high && data[left].compareTo(pivot) < 0);

            do {
                right--;     // just skip over items on the right > pivot
            }
            while (right>=low &&data[right].compareTo(pivot) > 0);

            if (left < right)
                swap(data, left, right);
        }
        return left;
    }