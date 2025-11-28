import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordComparators {

    /** Returns a comparator that orders strings by the number of lowercase 'x' characters (ascending). */
    public static Comparator<String> getXComparator() {
        return new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return count(s1, Set.of('x')) - count(s2, Set.of('x'));
            }
        };
    }

    /** Returns a comparator that orders strings by the count of the given character (ascending). */
    public static Comparator<String> getCharComparator(char c) {
        return (s1, s2) -> count(s1, Set.of(c)) - count(s2, Set.of(c));
    }

    /** Returns a comparator that orders strings by the total count of the given characters (ascending). */
    public static Comparator<String> getCharListComparator(List<Character> chars) {
        return Comparator.comparingInt(s -> count(s, new HashSet<>(chars)));
    }

    private static int count(String s, Set<Character> chars) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (chars.contains(s.charAt(i))) {
                count++;
            }
        }
        return count;
    }

}
