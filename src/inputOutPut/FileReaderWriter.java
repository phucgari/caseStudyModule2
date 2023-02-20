package inputOutPut;

import java.io.*;

public class FileReaderWriter {
    String link;
    private String newLine = System.getProperty("line.separator");
    public FileReaderWriter(String link){
        this.link=link;
    }
    public String read() {
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(link))) {
            String line;
            String result="";
            while((line=bufferedReader.readLine())!=null){
                result+=line+newLine;
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void write(String str){
        try(FileWriter fileWriter=new FileWriter(link,true)) {
            fileWriter.append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete() {
        try(FileWriter fileWriter=new FileWriter(link)){
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
