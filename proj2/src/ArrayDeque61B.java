import java.util.*;

/**
 * Array deque: circular backing array.
 * backing array: T[] items, n=items.length;
 * start: size=8, nextFirst=n-1, nextLast=0 (0 -> ... <- n-1, easy to deal with)
 * addFirst: items[nextFirst]=x, nextFirst=(nextFirst-1+n)%n
 * addLast: items[nextLast]=x, nextLast=(nextLast+1)%n
 * get: deque[i] = (nextFirst+1+i)%n
 * toList: deque[0, size-1]=[get[0], get[size-1]]
 * resize:
 * - copy items: [... nextFirst, deque[0], ..., deque[n-1], nextLast] to
 *   tmp: [deque[0], ..., deque[n-1], nextLast, ..., nextFirst]
 * - nextFirst=n-1, nextLast=size
 * resize up: cap = n*2;
 * resize down: if n >= 16 && size < n/4 => cap = n/4
 * @param <T>
 */
public class ArrayDeque61B<T> implements Deque61B<T> {

    private static final int RESIZE_DOWN_THRESHOLD = 16;

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque61B() {
        this.items = (T[]) new Object[8];
        this.nextFirst = this.items.length - 1;
        this.nextLast = 0;
        this.size = 0;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if (this.isFull()) {
            resize(this.items.length * 2);
        }
        this.items[this.nextFirst] = x;
        this.nextFirst = (this.nextFirst - 1 + this.items.length) % this.items.length;
        this.size++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        if (this.isFull()) {
            resize(this.items.length * 2);
        }
        this.items[this.nextLast] = x;
        this.nextLast = (this.nextLast + 1) % this.items.length;
        this.size++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            list.add(this.get(i));
        }
        return list;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.items.length >= RESIZE_DOWN_THRESHOLD && this.size < this.items.length / 4) {
            resize(this.items.length / 4);
        }
        T first = this.get(0);
        this.nextFirst = (this.nextFirst + 1) % this.items.length;
        this.items[this.nextFirst] = null;
        this.size--;
        return first;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.items.length >= RESIZE_DOWN_THRESHOLD && this.size < this.items.length / 4) {
            resize(this.items.length / 4);
        }
        T last = this.get(this.size - 1);
        this.nextLast = (this.nextLast - 1 + this.items.length) % this.items.length;
        this.items[this.nextLast] = null;
        this.size--;
        return last;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size() || this.isEmpty()) {
            return null;
        }
        return this.items[(this.nextFirst + 1 + index) % this.items.length];
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque61B<?> other) {
            if (this.size != other.size) {
                return false;
            }
            for (int i = 0; i < this.size; i++) {
                if (this.get(i) != other.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            list.add(this.get(i).toString());
        }
        return String.format("[%s]", String.join(", ", list));
    }

    private class ArrayDeque61BIterator implements Iterator<T> {
        private int index;

        public ArrayDeque61BIterator() {
            this.index = 0;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #index} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return this.index < ArrayDeque61B.this.size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            return ArrayDeque61B.this.get(index++);
        }
    }

    private boolean isFull() {
        return this.size == this.items.length;
    }

    // items: [... nextFirst, deque[0], ..., deque[n-1], nextLast]
    // tmp: [deque[0], ..., deque[n-1], nextLast, ..., nextFirst]
    private void resize(int cap) {
        T[] tmp = (T[]) new Object[cap];
        for (int i = 0; i < this.size; i++) {
            tmp[i] = this.get(i);
        }
        this.items = tmp;
        this.nextFirst = this.items.length - 1;
        this.nextLast = size;
    }

}
