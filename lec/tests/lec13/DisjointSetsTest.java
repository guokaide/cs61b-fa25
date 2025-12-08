package lec13;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class DisjointSetsTest {

    @Test
    public void quickFindDSTest() {
        DisjointSets ds = new QuickFindDS(7);

        assertThat(ds.isConnected(1, 2)).isFalse();
        ds.connect(1, 2);
        assertThat(ds.isConnected(1, 2)).isTrue();

        assertThat(ds.isConnected(1, 3)).isFalse();
        ds.connect(1, 3);
        assertThat(ds.isConnected(1, 3)).isTrue();
    }

    @Test
    public void quickUnionDSTest() {
        DisjointSets ds = new QuickUnionDS(7);

        assertThat(ds.isConnected(1, 2)).isFalse();
        ds.connect(1, 2);
        assertThat(ds.isConnected(1, 2)).isTrue();

        assertThat(ds.isConnected(1, 3)).isFalse();
        ds.connect(1, 3);
        assertThat(ds.isConnected(1, 3)).isTrue();
    }

    @Test
    public void weightedQuickUnionDSTest() {
        DisjointSets ds = new WeightedQuickUnionDS(7);

        assertThat(ds.isConnected(1, 2)).isFalse();
        ds.connect(1, 2);
        assertThat(ds.isConnected(1, 2)).isTrue();

        assertThat(ds.isConnected(1, 3)).isFalse();
        ds.connect(1, 3);
        assertThat(ds.isConnected(1, 3)).isTrue();
    }

    @Test
    public void weightedQuickUnionWithPathCompressionDSTest() {
        DisjointSets ds = new WeightedQuickUnionWithPathCompressionDS(7);

        assertThat(ds.isConnected(1, 2)).isFalse();
        ds.connect(1, 2);
        assertThat(ds.isConnected(1, 2)).isTrue();

        assertThat(ds.isConnected(1, 3)).isFalse();
        ds.connect(1, 3);
        assertThat(ds.isConnected(1, 3)).isTrue();
    }
}