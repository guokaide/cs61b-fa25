public class SLList {

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

    public SLList() {
        this.sentinel = new IntNode(0, null);
        this.size = 0;
        this.sum = 0;
    }

    public SLList(int x) {
        this.sentinel = new IntNode(0, null);
        this.sentinel.next = new IntNode(x, this.sentinel);
        this.size = 1;
        this.sum = x;
    }

    public void addFirst(int x) {
        this.sentinel.next = new IntNode(x, this.sentinel.next);
        this.size++;
        this.sum += x;
    }

    public int removeFirst() {
        IntNode first = this.sentinel.next;
        this.sentinel.next = first.next;
        this.size--;
        this.sum -= first.item;
        return first.item;
    }

    public int size() {
        return this.size;
    }

    public int sum() {
        return this.sum;
    }
}
