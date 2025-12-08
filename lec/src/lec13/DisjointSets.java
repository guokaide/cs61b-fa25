package lec13;

/**
 *  A Disjoint-Sets (or Union-Find) data structure keeps track of a fixed number of elements
 *  partitioned into a number of disjoint sets.
 */
public interface DisjointSets {
    /**
     * connects two items P and Q
     */
    void connect(int p, int q);

    /**
     * checks to see if two items are connected
     */
    boolean isConnected(int p, int q);
}
