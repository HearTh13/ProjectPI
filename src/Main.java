//public class Main {
//    public static void main(String[] args) {
//        InvertedIndexWithStemming idx = new InvertedIndexWithStemming();
//
//        idx.indexFolder("koleksi"); // <-- pastikan folder ini ada dan berisi .txt
//
//        idx.printIndex();
//
//        // Coba pencarian
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.print("\nMasukkan kata kunci pencarian (atau 'exit'): ");
//            String keyword = scanner.nextLine();
//            if (keyword.equalsIgnoreCase("exit")) break;
//            idx.search(keyword);
//        }
//
//        scanner.close();
//    }
//}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InvertedIndexWithStemming idx = new InvertedIndexWithStemming();

        idx.indexFolder("Koleksi"); // Pastikan folder ini ada dan berisi file .txt

        idx.printIndex();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nMasukkan kata kunci pencarian (atau 'exit'): ");
            String keyword = scanner.nextLine();
            if (keyword.equalsIgnoreCase("exit")) break;
            idx.search(keyword);
        }

        scanner.close();
    }
}