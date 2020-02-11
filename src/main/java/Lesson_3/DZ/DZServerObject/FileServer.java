package Lesson_3.DZ.DZServerObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer extends Thread {
    private ServerSocket ss;

    public FileServer(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                Socket clientSock = ss.accept(); //ждем подключения нашего клиента
                try {
                    saveFile(clientSock); //после того как клиент подключился вызываем метод сейффайл
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Socket clientSock) throws IOException, ClassNotFoundException {
        DataInputStream dis = new DataInputStream(clientSock.getInputStream()); // читает данные из входящего потока
        FileOutputStream fos = new FileOutputStream("stud2.ser");
        byte[] buffer = new byte[4096]; //определяем кусок по которому будем читать
        int filesize = 15123;
        int read = 0;
        int totalRead = 0;
        int remaining = filesize;
        while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) { //будем читать данные пока они не будут меньше 0
            totalRead += read;
            remaining -= read;
            System.out.println("read" + totalRead + " bytes.");
            fos.write(buffer,0,read); //прочитываем данные
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stud2.ser")); //процесс десирилизации объекта
        Studens s2 = (Studens)ois.readObject();
        ois.close();
        s2.info();
        fos.close();
        dis.close();
    }

    public static void main(String[] args) {
        FileServer fs = new FileServer(1988); //это новый поток
        fs.start();
    }
}
