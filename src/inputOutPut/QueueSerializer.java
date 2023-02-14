package inputOutPut;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueSerializer<E> {
    private String link;
    public QueueSerializer(String link){
        this.link=link;
    }
    public Queue<E> readObjects() {
        Queue<E> result;
        //Catch emptyErr
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(link));
        } catch (IOException e) {
            return (Queue<E>) (result=new LinkedList<>());
        }
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(link))) {
            result= (Queue<E>) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ;
        return result;
    }

    public void writeObjects(Queue<E> list) {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(link))){
            objectOutputStream.writeObject(list);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
