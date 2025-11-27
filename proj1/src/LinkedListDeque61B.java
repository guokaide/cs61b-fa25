import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    // static inner class: less memory (no reference of this) and avoid outer use
    private static class Node<T> {
        private final T item;
        private Node<T> prev;
        private Node<T> next;

        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    // sentinel is the sentinel of first and last node
    // sentinel.next points to first node
    // sentinel.prev points to last node
    private final Node<T> sentinel;
    private int size;

    // sentinel <=> sentinel
    public LinkedListDeque61B() {
        this.sentinel = new Node<>(null, null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }
    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        // sentinel <=> x <=> 1 <=> sentinel (note: the order of step 2 and 3 can't change)
        // 1. set the prev and next of x node
        Node<T> node = new Node<>(x, this.sentinel, this.sentinel.next);
        // 2. set the prev of sentinel.next
        this.sentinel.next.prev = node;
        // 3. set the next of sentinel
        this.sentinel.next = node;
        this.size++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        // sentinel <=> 1 <=> x <=> sentinel
        Node<T> node = new Node<>(x, this.sentinel.prev, this.sentinel);
        this.sentinel.prev.next = node;
        this.sentinel.prev = node;
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
        // iterate at first
        Node<T> p = this.sentinel.next;
        while (p != this.sentinel) {
            list.add(p.item);
            p = p.next;
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
        Node<T> first = this.sentinel.next;
        this.sentinel.next = first.next;
        first.next.prev = first;
        this.size--;
        return first.item;
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
        Node<T> last = this.sentinel.prev;
        this.sentinel.prev = last.prev;
        last.prev.next = last.next;
        this.size--;
        return last.item;
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
        if (index < 0 || index >= this.size) {
            return null;
        }
        Node<T> p = this.sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
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
        if (index < 0 || index >= this.size) {
            return null;
        }
        return getRecursive(this.sentinel.next, index);
    }

    private static <T> T getRecursive(Node<T> node, int index) {
        if (index < 0 || node == null) {
            return null;
        }
        if (index == 0) {
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }
}
