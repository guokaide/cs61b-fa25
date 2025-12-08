package lec13;

// problem: connect() is very slow
public class QuickFindDS implements DisjointSets {

    // index: store elements in ds
    // value: store set id number
    private final int[] id;

    // time: Θ(N)
    public QuickFindDS(int n) {
        this.id = new int[n];
        for (int i = 0; i < n; i++) {
            this.id[i] = i;
        }
    }

    /**
     * connects two items P and Q, time: Θ(N)
     */
    @Override
    public void connect(int p, int q) {
        int pid = this.id[p];
        int qid = this.id[q];
        for (int i = 0; i < this.id.length; i++) {
            if (this.id[i] == pid) {
                this.id[i] = qid;
            }
        }
    }

    /**
     * checks to see if two items are connected, time: Θ(1)
     */
    @Override
    public boolean isConnected(int p, int q) {
        return this.id[p] == this.id[q];
    }
}
