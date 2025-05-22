import java.io.*;
import java.util.*;
public class InvertedIndexWithStemming {
    private Map<String, List<Posting>> index = new HashMap<>();
    private PorterStemmer stemmer = new PorterStemmer();
    private int docCounter = 0;
    private Map<Integer, String> docNames = new HashMap<>();

    public void addDocument(int docId, String content, String fileName) {
        String[] words = content.toLowerCase().split("\\W+");

        for (String word : words) {
            if (word.isEmpty() || isStopWord(word)) continue;
            String stemmed = stemmer.stem(word);
            addToIndex(stemmed, docId);
        }

        docNames.put(docId, fileName);
    }

    private void addToIndex(String word, int docId) {
        List<Posting> postings = index.getOrDefault(word, new ArrayList<>());
        for (Posting post : postings) {
            if (post.docId == docId) {
                post.frequency++;
                index.put(word, postings);
                return;
            }
        }
        postings.add(new Posting(docId));
        index.put(word, postings);
    }

    private boolean isStopWord(String word) {
        String[] stopWords = {"dan", "yang", "di", "ke", "dari", "ini", "itu", "pada", "dengan", "untuk"};
        return Arrays.asList(stopWords).contains(word);
    }

    public void indexFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null) {
            System.out.println("Folder tidak ditemukan atau kosong.");
            return;
        }

        Arrays.sort(files);
        for (File file : files) {
            try {
                String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                docCounter++;
                addDocument(docCounter, content, file.getName());
            } catch (IOException e) {
                System.out.println("Gagal membaca " + file.getName());
            }
        }
    }

    public void printIndex() {
        for (String term : index.keySet()) {
            System.out.print(term + " -> ");
            for (Posting p : index.get(term)) {
                System.out.print("[" + docNames.get(p.docId) + ", freq=" + p.frequency + "] ");
            }
            System.out.println();
        }
    }

    public void search(String keyword) {
        String stemmed = stemmer.stem(keyword.toLowerCase());
        List<Posting> postings = index.getOrDefault(stemmed, new ArrayList<>());
        System.out.println("Hasil pencarian untuk: " + keyword + " (stemmed: " + stemmed + ")");
        for (Posting p : postings) {
            System.out.println("- " + docNames.get(p.docId) + " (frekuensi: " + p.frequency + ")");
        }
        if (postings.isEmpty()) {
            System.out.println("Tidak ditemukan.");
        }
    }
}
