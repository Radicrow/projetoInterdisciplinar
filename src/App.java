import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {

        int cenario;


        for(int i = 0; i<31;i++){
         //Primeiro caso
         cenario = 1;
         System.out.println('\n' + "Primeiro caso: Vetor de inteiros ordenados de forma crescente." + '\n');
         int[] primeiroCaso1 = lerArquivo("amostra_crescente_10000.txt");
         ordenarNumeros(primeiroCaso1, cenario);
         int[] primeiroCaso2 = lerArquivo("amostra_crescente_100000.txt");
         ordenarNumeros(primeiroCaso2, cenario);
         int[] primeiroCaso3 = lerArquivo("amostra_crescente_500000.txt");
         ordenarNumeros(primeiroCaso3, cenario);
         
         System.out.println('\n' + "Segundo caso: Vetor de inteiros ordenados de forma decrescente." + '\n');
         cenario = 2;
         int[] segundoCaso1 = lerArquivo("amostra_decrescente_10000.txt");
         ordenarNumeros(segundoCaso1, cenario);
         int[] segundoCaso2 = lerArquivo("amostra_decrescente_100000.txt");
         ordenarNumeros(segundoCaso2,cenario);
         int[] segundoCaso3 = lerArquivo("amostra_decrescente_500000.txt");
         ordenarNumeros(segundoCaso3, cenario);
         
         //Terceiro caso
         cenario = 3;
         System.out.println('\n' + "Terceiro caso: Vetor de inteiros aleatórios." + '\n');
         int[] terceiroCaso1 = lerArquivo("amostra_aleatoria_10000.txt");
         ordenarNumeros(terceiroCaso1, cenario);
         int[] terceiroCaso2 = lerArquivo("amostra_aleatoria_100000.txt");
         ordenarNumeros(terceiroCaso2, cenario);
         int[] terceiroCaso3 = lerArquivo("amostra_aleatoria_500000.txt");
         ordenarNumeros(terceiroCaso3, cenario);

         //Quarto caso
         cenario = 4;
         System.out.println('\n' + "Quarto caso: Vetor de strings aleatórias." + '\n');
         String[] quartoCaso1 = lerArquivoString("strings_aleatorias_10000.txt");
         ordenarStrings(quartoCaso1, cenario);
         String[] quartoCaso2 = lerArquivoString("strings_aleatorias_100000.txt");
         ordenarStrings(quartoCaso2, cenario);
         String[] quartoCaso3 = lerArquivoString("strings_aleatorias_500000.txt");
         ordenarStrings(quartoCaso3, cenario);
        }

    }

    public static void ordenarNumeros(int[] array, int cenario){
        if (array == null) {
            System.err.println("Erro ao carregar o arquivo.");
            return;
        }
        int idHardware = 4;

        metodosOrdenacao.mergeSort(array.clone(), array.clone().length, cenario, idHardware);
        metodosOrdenacao.quickSort(array.clone(), 0, array.clone().length-1, cenario, idHardware);
        metodosOrdenacao.shellSort(array.clone(), cenario, idHardware);
    }

    public static void ordenarStrings(String[] array, int cenario){
        if (array == null) {
            System.err.println("Erro ao carregar o arquivo.");
            return;
        }
        int idHardware = 4;

        stringOrdenacao.mergeSort(array.clone(), array.clone().length, cenario, idHardware);
        stringOrdenacao.quickSort(array.clone(), 0, array.clone().length-1, cenario, idHardware);
        stringOrdenacao.shellSort(array.clone(), cenario, idHardware);
    }
    
    public static int[] lerArquivo(String fileName) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + fileName);
            return null;
        }
        return numbers.stream().mapToInt(i -> i).toArray();
    }

    public static String[] lerArquivoString(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line); 
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + fileName);
            return null;
        }
        return lines.toArray(new String[0]); 
    }

    //Métodos usados para gerar os números
    //Cenário 1: Vetor de inteiros ordenados de forma crescente.
    public static void gerarOrdenadosCrescente() {
        int[] sizes = {10000, 100000, 500000};

        for (int size : sizes) {
            try {
                FileWriter writer = new FileWriter("amostra_crescente_" + size + ".txt");
                for (int i = 0; i < size; i++) {
                    writer.write(i + "\n"); 
                }

                writer.close();
                System.out.println("Arquivo crescente gerado com sucesso");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo crescente");
            }
        }
    }

    //Cenário 2: Vetor de inteiros ordenados de forma decrescente.
    public static void gerarOrdenadosDecrescente() {
        int[] sizes = {10000, 100000, 500000};

        for (int size : sizes) {
            try {
                FileWriter writer = new FileWriter("amostra_decrescente_" + size + ".txt");
                for (int i = size; i > 0; i--) {
                    writer.write(i + "\n"); // Os números serão size, size-1, ..., 1
                }

                writer.close();
                System.out.println("Arquivo decrescente gerado com sucesso");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo decrescente");
            }
        }
    }


    // Cenário 3: Vetor de inteiros aleatórios.
    public static void gerarAleatorios(){
        int[] sizes = {10000, 100000, 500000};
        Random random = new Random();

        for (int size : sizes) {
            try {
                FileWriter writer = new FileWriter("amostra_aleatoria_" + size + ".txt");
                for (int i = 0; i < size; i++) {
                    int number = random.nextInt(100000);
                    writer.write(number + "\n");
                }

                writer.close();
                System.out.println("Arquivo aleatórios gerado com sucesso");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo de números aleatórios");
            }
        }
    }


    // Cenário 4: Vetor de strings (palavras) aleatórias.
    public static void gerarStringsAleatorias() {
        int[] sizes = {10000, 100000, 500000}; // Tamanhos das amostras
        int stringLength = 10; // Tamanho de cada string
        Random random = new Random();

        for (int size : sizes) {
            try {
                FileWriter writer = new FileWriter("strings_aleatorias_" + size + ".txt");
                for (int i = 0; i < size; i++) {
                    String randomString = gerarStringAleatoria(random.nextInt(5) +4, random);
                    writer.write(randomString + "\n");
                }
                writer.close();
                System.out.println("Arquivo strings_aleatorias_" + size + ".txt gerado com sucesso");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo de strings aleatórias");
            }
        }
    }

    private static String gerarStringAleatoria(int length, Random random) {
        String caracteres = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }
}

