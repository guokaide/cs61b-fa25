package lec13;

public class WeightedQuickUnionWithPathCompressionDS implements DisjointSets {

    private final int[] parent;

    // time: time: Θ(N)
    public WeightedQuickUnionWithPathCompressionDS(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = -1;
        }
    }

    /**
     * connects two items P and Q, time: O(α(N)) very fast
     */
    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j = find(q);
        int size = this.parent[i] + this.parent[j];
        if (Math.abs(i) >= Math.abs(j)) {
            this.parent[i] = size;
            this.parent[j] = i;
        } else {
            this.parent[j] = size;
            this.parent[i] = j;
        }
    }

    /**
     * checks to see if two items are connected, time: O(α(N)) very fast
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // find the root, then the size of the set which belongs to the root is parent[find[p]]
    // use size to lower the height of tree
    // use path compression to lower the height of tree
    private int find(int p) {
        int root = p;
        while (this.parent[root] >= 0) {
            root = this.parent[root];
        }
        // path halving：每次把当前节点的父指针跳两层
        while (p != root) {
            int newp = this.parent[p];
            this.parent[p] = root;
            p = newp;
        }
        return root;
    }
}
