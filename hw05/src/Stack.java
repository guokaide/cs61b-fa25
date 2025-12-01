public class Stack {

    private static class IntNode {
        // 在 Java 中，外部类与其嵌套类互相可以访问对方的私有成员；编译器会生成必要的合成访问器。
        private int item;
        private IntNode next;

        public IntNode(int i, IntNode n) {
            this.item = i;
            this.next = n;
        }
    }

    private final IntNode sentinel;
    private int size;
    private int sum;

    public Stack() {
        this.sentinel = new IntNode(0, null);
        this.size = 0;
        this.sum = 0;
    }

    /** Puts x on top of the stack. */
    public void push(int x) {
        this.sentinel.next = new IntNode(x, this.sentinel.next);
        this.size++;
        this.sum += x;
    }

    /** Removes and returns the top item on the stack. */
    public int pop() {
        IntNode top = this.sentinel.next;
        this.sentinel.next = top.next;
        this.size--;
        this.sum -= top.item;
        return top.item;
    }

    /** Returns the number of items on the stack. */
    public int size() {
        return this.size;
    }

    /** Computes the sum of the numbers on the stack. */
    public int sum() {
        return this.sum;
    }

}
