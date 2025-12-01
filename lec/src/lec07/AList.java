package lec07;

public class AList<T> {
    private T[] items;
    private int size;

    public AList() {
        this.items = (T[]) new Object[8];
        this.size = 0;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        this.items[size] = item;
        this.size++;
    }

    public T getLast() {
        return this.items[size - 1];
    }

    public T removeLast() {
        T item = this.items[this.size - 1];
        this.items[this.size - 1] = null;
        this.size--;
        return item;
    }

    public T get(int i) {
        return this.items[i];
    }

    public int size() {
        return this.size;
    }

    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        System.arraycopy(this.items, 0, tmp, 0, this.size);
        this.items = tmp;
    }

}
