package Lesson_3.DZ.DZServerObject;

import java.io.*;
import java.net.Socket;

public class FileClient {
    private Socket s;

    public FileClient(String host, int port, String file) {
        try {
            s = new Socket(host, port); //создание сокета
            sendFile(file); //отпрвка файла
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String file) throws IOException {
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); //ОУТпутстрим куда мы данные будем отправлять
        FileInputStream fis = new FileInputStream(file); // название пути к файлу
        byte[] buffer = new byte[4096];
        while (fis.read(buffer) > 0) { //читаем данные из файла
            dos.write(buffer); //передаем данные в исходящий поток отправляем сокету s
        }
        fis.close();
        dos.close();
    }

    public static void main(String[] args) throws IOException {
        Studens s = new Studens(1, "Bob"); //создали объект для передачи
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stud.ser")); // создали обжект Оутпутстрим в файл stud.ser
        oos.writeObject(s); //записали в файл созданный объъект (ОбжектОУТСТрим(ФилеОУтСтрим)
        //это все была серелизация обекта
        oos.close();
        String filePath = "stud.ser"; //сериализовали в файл и передали  клиенту
        new FileClient("localhost", 1988, filePath);
    }
}
