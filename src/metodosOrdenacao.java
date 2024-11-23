public class metodosOrdenacao {
    // Bubble Sort 
    public static void bubbleSort(int[] array) {
        long startTime = System.currentTimeMillis();
        
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        long endTime = System.currentTimeMillis();
        System.out.println("Bubble Sort levou " + (endTime - startTime) + " ms para " + array.length + " elementos");
    }

    // Merge Sort 
    public static void mergeSort(int[] array, int n) {
        long startTime = System.currentTimeMillis();
        
        mergeSortHelper(array, n);

        long endTime = System.currentTimeMillis();
        System.out.println("Merge Sort levou " + (endTime - startTime) + " ms para " + array.length + " elementos");
    }

    private static void mergeSortHelper(int[] array, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
    
        for (int i = 0; i < mid; i++) {
            l[i] = array[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = array[i];
        }
        mergeSortHelper(l, mid);
        mergeSortHelper(r, n - mid);
        merge(array, l, r, mid, n - mid);
    }

    private static void merge(int[] array, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                array[k++] = l[i++];
            } else {
                array[k++] = r[j++];
            }
        }
        while (i < left) {
            array[k++] = l[i++];
        }
        while (j < right) {
            array[k++] = r[j++];
        }
    }

    // Quick Sort
    public static void quickSort(int[] array, int low, int high) {
        long startTime = System.currentTimeMillis();
        
        quickSortHelper(array, low, high);

        long endTime = System.currentTimeMillis();
        System.out.println("Quick Sort levou " +(endTime - startTime) + " ms para " + array.length + " elementos");
    }

    private static void quickSortHelper(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSortHelper(array, low, pi - 1);
            quickSortHelper(array, pi + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    // Shell Sort
    public static void shellSort(int[] array) {
        long startTime = System.currentTimeMillis();
        
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Shell Sort levou " + (endTime - startTime) + " ms para " + array.length + " elementos\n\n");
    }

    
}
