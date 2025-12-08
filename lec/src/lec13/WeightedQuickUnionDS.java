package lec13;

/**
 * 1. 加权合并规定：合并时把较小规模的集合的根指向较大规模集合的根。这样能避免长链，控制树高增长。
 * 2. 每次把两个同规模的“大树”合到一起，高度只增加 1；而规模翻倍。
 * 因此当规模 N 以 2 的倍数增长时，高度按加 1 增长，对应高度约为 log₂ N。
 * - 最好高度：Θ(1)。当每次合并都是把单节点（规模 1）接到大树根上，树高几乎不增长，保持常数级。
 * - 最坏高度：Θ(log N)。因为每当规模翻倍（同规模合并），高度只增加 1，最终高度与 log₂ N 同阶。
 */
public class WeightedQuickUnionDS implements DisjointSets {

    private final int[] parent;

    // time: time: Θ(N)
    public WeightedQuickUnionDS(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = -1;
        }
    }

    /**
     * connects two items P and Q, time: O(logN)
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
     * checks to see if two items are connected, time: O(logN)
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // find the root, then the size of the set which belongs to the root is parent[find[p]]
    // use size to lower the height of tree
    private int find(int p) {
        while (this.parent[p] >= 0) {
            p = this.parent[p];
        }
        return p;
    }
}
