package inputOutPut;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer<E> {
    private String link;
    Serializer(String link){
        this.link=link;
    }
    public List<E> readObjects() {
        List<E> result;
        //Catch emptyErr
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(link));
        } catch (IOException e) {
            return result=new ArrayList<>();
        }
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(link))) {
            result= (List<E>) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ;
        return result;
    }

    public void writeObjects(List<E> list) {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(link))){
            objectOutputStream.writeObject(list);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
