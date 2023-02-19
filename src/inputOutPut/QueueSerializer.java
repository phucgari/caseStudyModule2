package inputOutPut;

import model.doctor.DiagnoseDoctor;

import java.io.*;
import java.util.PriorityQueue;


public class QueueSerializer<E> {
    private String link;
    public QueueSerializer(String link){
        this.link=link;
    }
    public PriorityQueue<E> readObjects() {
        PriorityQueue<E> result=new PriorityQueue<>();
        PriorityQueue<E> temp;
        //Catch emptyErr
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(link));
        } catch (IOException e) {
            return result=new PriorityQueue<>();
        }
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(link))) {
            temp= (PriorityQueue<E>) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (!temp.isEmpty()){
            result.add(temp.poll());
        }
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
