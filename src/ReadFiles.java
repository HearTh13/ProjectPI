import java.io.File;

public class ReadFiles {

    public void read(){
        String path = "Koleksi";
        File name = new File(path);
        if (name.exists()) {
            if (name.isDirectory()) {
                String directory[] = name.list();
                System.out.println("\n\nDirectory Contents\n");
                for (String string : directory) {
                    System.out.println(string);
                }
            }
        }
    }

}
