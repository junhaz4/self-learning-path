import java.io.FileWriter;
import java.io.IOException;

public class io {
    public static void main(String[] args) {
        FileWriter fileWriter = null;
        String path = "src/hello.txt";
        char[] chars = {'a','b'};
        try {
            fileWriter = new FileWriter(path);
            fileWriter.write("h");
            fileWriter.write(chars);
            fileWriter.write("he;;p world");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
