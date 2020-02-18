package Lesson_6;

public class Box {
    private String name;

    public Box(String name) {
        this.name = name;
    }
}


// ящик для вещей
class StorageBox extends Box {
    // вместимость
    private int capacity;


    public StorageBox(String name, int capacity) {
        super(name);
        this.capacity = capacity;
    }
}

// ящик для вещей
class EStorageBox extends StorageBox {
    // очитка вещей
    private boolean clean;

    public EStorageBox(String name, int capacity, boolean clean) {
        super(name, capacity);
        this.clean = clean;
    }
}

