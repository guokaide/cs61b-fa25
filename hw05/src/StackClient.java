public class StackClient {

    public static Stack flipped(Stack s) {
        Stack r = new Stack();
        while (s.size() > 0) {
            r.push(s.pop());
        }
        return r;
    }
}
