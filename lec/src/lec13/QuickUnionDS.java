package lec13;

// problem: connect and isConnected is linear to n
public class QuickUnionDS implements DisjointSets {

    // index: store elements in ds
    // value: store the parent of the element, value = -1 if there is no parent.
    private final int[] parent;

    // time: Θ(N)
    public QuickUnionDS(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = -1;
        }
    }

    /**
     * connects two items P and Q, time: O(n)
     */
    @Override
    public void connect(int p, int q) {
        this.parent[find(p)] = find(q);
    }

    /**
     * checks to see if two items are connected, time: O(n)
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        while (this.parent[p] >= 0) {
            p = this.parent[p];
        }
        return p;
    }
}
