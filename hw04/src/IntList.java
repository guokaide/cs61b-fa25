/**
 * a naked linked list: each node is the list itself.
 */
public class IntList {
    int first;
    IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. Must use recursion.
     *
     * This method is non-destructive, i.e. it must not modify the original list.
     */
    public static IntList incrRecursiveNondestructive(IntList L, int x) {
        // L: 5 - 7 - 9 - null
        // M: 7 - 9 - 11 - null
        // sub problem: IntList M.rest = incrRecursiveNondestructive(IntList L.rest, int x)
        if (L == null) {
            return null;
        }
        return new IntList(L.first + x, incrRecursiveNondestructive(L.rest, x));
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Modifies the original list.
     * You are not allowed to use "new" in this method.
     */
    public static IntList incrRecursiveDestructive(IntList L, int x) {
        if (L == null) {
            return null;
        }
        L.first = L.first + x;
        incrRecursiveDestructive(L.rest, x);
        return L;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed
     * to use recursion. May not modify the original list.
     */
    public static IntList incrIterativeNondestructive(IntList L, int x) {
        if (L == null) {
            return null;
        }
        IntList M = new IntList(L.first + x, null);
        IntList p = L.rest, q = M;
        while (p != null) {
            q.rest = new IntList(p.first + x, null);
            p = p.rest;
            q = q.rest;
        }
        return M;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed
     * to use recursion. Modifies the original list.
     * You are not allowed to use "new" in this method.
     */
    public static IntList incrIterativeDestructive(IntList L, int x) {
        if (L == null) {
            return null;
        }
        IntList p = L;
        while (p != null) {
            p.first = p.first + x;
            p = p.rest;
        }
        return L;
    }

    /**
     * Returns an IntList consisting of the elements of L1 followed by the
     * elements of L2.
     */
    public static IntList concatenate(IntList L1, IntList L2) {
        if (L1 == null) {
            return L2;
        }
        if (L2 == null) {
            return L1;
        }
        IntList p = L1;
        while (p.rest != null) {
            p = p.rest;
        }
        p.rest = L2;
        return L1;
    }

    /*
     * =================================================================
     * OPTIONAL METHODS
     * =================================================================
     */

    /**
     * Returns the sum of all elements in the IntList.
     */
    public int sum() {
        if (rest == null) {
            return first;
        }
        return first + rest.sum();
    }

    /**
     * Destructively adds x to the end of the list.
     */
    public void addLast(int x) {
        IntList p = this;
        while (p.rest != null) {
            p = p.rest;
        }
        p.rest = new IntList(x, null);
    }

    /**
     * Destructively adds x to the front of this IntList.
     * This is a bit tricky to implement. The standard way to do this would be
     * to return a new IntList, but for practice, this implementation should
     * be destructive.
     */
    public void addFirst(int x) {
        rest = new IntList(first, rest);
        first = x;
    }
}
