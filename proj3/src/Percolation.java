import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private final int N;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF percolationUf;
    private final WeightedQuickUnionUF fullnessUf;
    private final int virtualTop;
    private final int virtualBottom;
    private int openSits;

    // time: Θ(N^2)
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        this.N = N;
        this.grid = new boolean[N][N];
        this.percolationUf = new WeightedQuickUnionUF(N * N + 2);
        this.fullnessUf = new WeightedQuickUnionUF(N * N + 1);
        this.virtualTop = N * N;
        this.virtualBottom = N * N + 1;
        this.openSits = 0;
    }

    // time: Θ(1)
    public void open(int row, int col) {
        this.grid[row][col] = true;
        this.openSits++;
        for (int i = 0; i < 4; i++) {
            int x = row + DX[i];
            int y = col + DY[i];
            if (validate(x, y) && isOpen(x, y)) {
                this.percolationUf.union(xyTo1D(x, y), xyTo1D(row, col));
                this.fullnessUf.union(xyTo1D(x, y), xyTo1D(row, col));
                for (int j = 0; j < 4; j++) {
                    int p = x + DX[j];
                    int q = y + DY[j];
                    if (validate(p, q) && isOpen(p, q)) {
                        this.percolationUf.union(xyTo1D(x, y), xyTo1D(p, q));
                        this.fullnessUf.union(xyTo1D(x, y), xyTo1D(p, q));
                    }
                }
            }
        }
        if (row == 0) {
            this.percolationUf.union(this.virtualTop, xyTo1D(row, col));
            this.fullnessUf.union(this.virtualTop, xyTo1D(row, col));
        }
        if (row == N - 1) {
            this.percolationUf.union(this.virtualBottom, xyTo1D(row, col));
        }
    }

    // time: Θ(1)
    public boolean isOpen(int row, int col) {
        return this.grid[row][col];
    }

    // time: Θ(1)
    public boolean isFull(int row, int col) {
        return this.fullnessUf.find(xyTo1D(row, col)) == this.fullnessUf.find(virtualTop);
    }

    // time: Θ(1)
    public int numberOfOpenSites() {
        return this.openSits;
    }

    // time: Θ(1)
    public boolean percolates() {
        return this.percolationUf.find(virtualTop) == this.percolationUf.find(virtualBottom);
    }

    // index = x * N + y
    private int xyTo1D(int x, int y) {
        return x * N + y;
    }

    private boolean validate(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
