import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class stringOrdenacao {
    //Bubble Sort para Strings
    public static void bubbleSort(String[] array, int cenario, int id_hardware) {
        long startTime = System.currentTimeMillis();

        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    String temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        String logEntry = String.format(
           "INSERT INTO public.resultados(id_hardware, id_algoritmo, cenario, tamanho_amostra, tempo_execucao_microsegundos) " +
           "VALUES (%d, %d, %d, %d, %d);",
           id_hardware, 1, cenario, array.length, time
       );

        writeToFile("resultados.txt", logEntry);
        System.out.println("Bubble Sort levou " + (endTime - startTime) + " ms para " + array.length + " elementos");
    }

     private static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);
            writer.newLine(); 
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    
    // Merge Sort String
    public static void mergeSort(String[] array, int n,  int cenario, int id_hardware) {
        long startTime = System.currentTimeMillis();

        mergeSortHelper(array, n);

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        String logEntry = String.format(
           "INSERT INTO public.resultados(id_hardware, id_algoritmo, cenario, tamanho_amostra, tempo_execucao_microsegundos) " +
           "VALUES (%d, %d, %d, %d, %d);",
           id_hardware, 2, cenario, array.length, time
       );

       writeToFile("resultados.txt", logEntry);
        System.out.println("Merge Sort levou " + (endTime - startTime) + " ms para " + array.length + " elementos");
    }

    private static void mergeSortHelper(String[] array, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        String[] left = new String[mid];
        String[] right = new String[n - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = array[i];
        }

        mergeSortHelper(left, mid);       
        mergeSortHelper(right, n - mid); 

        merge(array, left, right, mid, n - mid);
    }

    private static void merge(String[] array, String[] left, String[] right, int leftSize, int rightSize) {
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (left[i].compareTo(right[j]) <= 0) { 
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < leftSize) {
            array[k++] = left[i++];
        }
        while (j < rightSize) {
            array[k++] = right[j++];
        }
    }

    // Quick Sort para Strings
    public static void quickSort(String[] array, int low, int high, int cenario, int id_hardware) {
        long startTime = System.currentTimeMillis();

        quickSortHelper(array, low, high);

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        String logEntry = String.format(
           "INSERT INTO public.resultados(id_hardware, id_algoritmo, cenario, tamanho_amostra, tempo_execucao_microsegundos) " +
           "VALUES (%d, %d, %d, %d, %d);",
           id_hardware, 3, cenario, array.length, time
       );

       writeToFile("resultados.txt", logEntry);
        System.out.println("Quick Sort levou " + (endTime - startTime) + " ms para " + array.length + " elementos");
    }

    private static void quickSortHelper(String[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSortHelper(array, low, pi - 1);
            quickSortHelper(array, pi + 1, high);
        }
    }

    private static int partition(String[] array, int low, int high) {
        String pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                String temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        String temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

     // Shell Sort para Strings
     public static void shellSort(String[] array, int cenario, int id_hardware) {
        long startTime = System.currentTimeMillis();

        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                String temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap].compareTo(temp) > 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        String logEntry = String.format(
           "INSERT INTO public.resultados(id_hardware, id_algoritmo, cenario, tamanho_amostra, tempo_execucao_microsegundos) " +
           "VALUES (%d, %d, %d, %d, %d);",
           id_hardware, 4, cenario, array.length, time
       );

       writeToFile("resultados.txt", logEntry);
        System.out.println("Shell Sort levou " + (endTime - startTime) + " ms para " + array.length + " elementos\n\n");
    }
}
