package lec04;

/** An SLList (single linked list) is a list of integers, which hides the terrible truth
 * of the nakedness within.
 *
 * Naked Linked Lists (lec04.IntList) vs. SLLists
 * lec04.SLList class acts as a middle man between user and the naked recursive data structure.
 * Allows us to store meta information about entire list, e.g. size.
 */
public class SLList {

    // static: 不依赖外部类实例，节省内存
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            this.item = i;
            this.next = n;
        }
    }

    /** Private Recursive Helper Method: Returns the size of the list that starts at IntNode p. */
    private static int size(IntNode p) {
        if (p.next == null) {
            return 1;
        }
        return 1 + size(p.next);
    }

    private IntNode sentinel;
    private int size;

    /** Creates an empty lec04.SLList. */
    public SLList() {
        this.sentinel = new IntNode(0, null);
        this.size = 0;
    }

    public SLList(int x) {
        this.sentinel = new IntNode(0, null);
        this.sentinel.next = new IntNode(x, null);
        this.size = 1;
    }

    /** Adds x to the front of the list. */
    public void addFirst(int x) {
        this.sentinel.next = new IntNode(x, this.sentinel.next);
        this.size++;
    }

    /** Returns the first item in the list. */
    public int getFirst() {
        return this.sentinel.next.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addLast(5);
        L.addFirst(4);
        L.addFirst(3);
        L.addLast(6);
        System.out.println(L.getFirst()); // 3
        System.out.println(L.size()); // 4
    }
}
