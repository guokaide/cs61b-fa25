import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayDeque61BTest {

    @Test
    public void addFirstTestBasic() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.toList()).isEmpty();

        deque.addFirst("back");
        assertThat(deque.toList()).containsExactly("back").inOrder();

        deque.addFirst("middle");
        assertThat(deque.toList()).containsExactly("middle", "back").inOrder();

        deque.addFirst("front");
        assertThat(deque.toList()).containsExactly("front", "middle", "back").inOrder();

        // test resize up
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addFirst("5");
        deque.addFirst("6");
        deque.addFirst("7");
        deque.addFirst("8");
        assertThat(deque.toList()).
                containsExactly("8", "7", "6", "5", "4", "3", "2", "1", "front", "middle", "back").
                inOrder();
    }

    @Test
    public void addLastTestBasic() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.toList()).isEmpty();

        deque.addLast("front");
        assertThat(deque.toList()).containsExactly("front").inOrder();

        deque.addLast("middle");
        assertThat(deque.toList()).containsExactly("front", "middle").inOrder();

        deque.addLast("back");
        assertThat(deque.toList()).containsExactly("front", "middle", "back").inOrder();

        // test resize up
        deque.addLast("1");
        deque.addLast("2");
        deque.addLast("3");
        deque.addLast("4");
        deque.addLast("5");
        deque.addLast("6");
        deque.addLast("7");
        deque.addLast("8");

        assertThat(deque.toList()).
                containsExactly("front", "middle", "back", "1", "2", "3", "4", "5", "6", "7", "8").inOrder();
    }

    @Test
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        assertThat(deque.toList()).isEmpty();

        deque.addLast(0);   // [0]
        deque.addLast(1);   // [0, 1]
        deque.addFirst(-1); // [-1, 0, 1]
        deque.addLast(2);   // [-1, 0, 1, 2]
        deque.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(deque.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();

        deque.addFirst(-3);
        deque.addLast(3);
        deque.addLast(4);
        assertThat(deque.toList()).containsExactly(-3, -2, -1, 0, 1, 2, 3, 4).inOrder();

        // test resize up
        deque.addFirst(-4);
        assertThat(deque.toList()).containsExactly(-4, -3, -2, -1, 0, 1, 2, 3, 4).inOrder();
    }

    @Test
    public void getTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.get(0)).isNull();

        deque.addFirst("c");
        deque.addFirst("b");
        deque.addFirst("a");

        assertThat(deque.get(0)).isEqualTo("a");
        assertThat(deque.get(1)).isEqualTo("b");
        assertThat(deque.get(2)).isEqualTo("c");
        assertThat(deque.get(-1)).isNull();
        assertThat(deque.get(3)).isNull();

        // test resize
        deque.addLast("d");
        deque.addLast("e");
        deque.addLast("f");
        deque.addLast("g");
        deque.addLast("h");
        deque.addLast("i");
        deque.addLast("j");
        deque.addLast("k");
        assertThat(deque.get(10)).isEqualTo("k");
        assertThat(deque.get(11)).isNull();
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThrows(UnsupportedOperationException.class, () -> deque.getRecursive(0));
    }

    @Test
    public void sizeTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.size()).isEqualTo(0);

        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");

        assertThat(deque.size()).isEqualTo(3);
    }

    @Test
    public void isEmptyTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.isEmpty()).isTrue();

        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");

        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.removeFirst()).isNull();

        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");

        assertThat(deque.removeFirst()).isEqualTo("front");
        assertThat(deque.size()).isEqualTo(2);

        // test resize down
        for (int i = 0; i < 118; i++) {
            deque.addLast("" + i);
        }
        assertThat(deque.size()).isEqualTo(120);

        for (int i = 0; i < 100; i++) {
            deque.removeFirst();
        }
        assertThat(deque.size()).isEqualTo(20);
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.removeLast()).isNull();

        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");

        assertThat(deque.removeLast()).isEqualTo("back");
        assertThat(deque.size()).isEqualTo(2);

        // test resize down
        for (int i = 0; i < 118; i++) {
            deque.addLast("" + i);
        }
        assertThat(deque.size()).isEqualTo(120);

        for (int i = 0; i < 100; i++) {
            deque.removeLast();
        }
        assertThat(deque.size()).isEqualTo(20);

        for (int i = 0; i < 100; i++) {
            deque.addLast("" + i);
        }
        assertThat(deque.size()).isEqualTo(120);

        for (int i = 0; i < 100; i++) {
            deque.removeLast();
        }
        assertThat(deque.size()).isEqualTo(20);
    }

    @Test
    public void iteratorTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque).isEmpty();

        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");

        // for-each in .containsExactly
        assertThat(deque).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void equalsTest() {
        Deque61B<String> d1 = new ArrayDeque61B<>();
        Deque61B<String> d2 = new ArrayDeque61B<>();

        d1.addLast("front");
        d1.addLast("middle");
        d1.addLast("back");

        d2.addLast("front");
        d2.addLast("middle");
        d2.addLast("back");

        assertThat(d1).isEqualTo(d2);
    }

    @Test
    public void toStringTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque).isEmpty();

        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");

        assertThat(deque.toString()).isEqualTo("[front, middle, back]");
    }


}
