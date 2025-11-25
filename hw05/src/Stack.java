public class Stack {

    private final SLList list;

    public Stack() {
        this.list = new SLList();
    }

    /** Puts x on top of the stack. */
    public void push(int x) {
        list.addFirst(x);
    }

    /** Removes and returns the top item on the stack. */
    public int pop() {
        return list.removeFirst();
    }

    /** Returns the number of items on the stack. */
    public int size() {
        return list.size();
    }

    /** Computes the sum of the numbers on the stack. */
    public int sum() {
        return list.sum();
    }

}
