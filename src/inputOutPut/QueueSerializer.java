package inputOutPut;

import java.io.*;
import java.util.PriorityQueue;


public class QueueSerializer<E> {
    private String link;
    public QueueSerializer(String link){
        this.link=link;
    }
    public PriorityQueue<E> readObjects() {
        PriorityQueue<E> result;
        //Catch emptyErr
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(link));
        } catch (IOException e) {
            return result=new PriorityQueue<>();
        }
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(link))) {
            result= (PriorityQueue<E>) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ;
        return result;
    }

    public void writeObjects(PriorityQueue<E> list) {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(link))){
            objectOutputStream.writeObject(list);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
