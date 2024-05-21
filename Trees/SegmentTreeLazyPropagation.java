package Trees;

public class SegmentTreeLazyPropagation {
    private int[] tree, lazy;
    private int n;

    public SegmentTreeLazyPropagation(int[] arr) {
        this.n = arr.length;
        tree = new int[4 * n];
        lazy = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public void updateRange(int l, int r, int val) {
        updateRange(0, 0, n - 1, l, r, val);
    }

    private void updateRange(int node, int start, int end, int l, int r, int val) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (start > end || start > r || end < l) {
            return;
        }

        if (start >= l && end <= r) {
            tree[node] += (end - start + 1) * val;
            if (start != end) {
                lazy[2 * node + 1] += val;
                lazy[2 * node + 2] += val;
            }
            return;
        }

        int mid = (start + end) / 2;
        updateRange(2 * node + 1, start, mid, l, r, val);
        updateRange(2 * node + 2, mid + 1, end, l, r, val);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    public int queryRange(int l, int r) {
        return queryRange(0, 0, n - 1, l, r);
    }

    private int queryRange(int node, int start, int end, int l, int r) {
        if (start > end || start > r || end < l) {
            return 0;
        }

        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (start >= l && end <= r) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int left = queryRange(2 * node + 1, start, mid, l, r);
        int right = queryRange(2 * node + 2, mid + 1, end, l, r);
        return left + right;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11 };
        SegmentTreeLazyPropagation st = new SegmentTreeLazyPropagation(arr);

        // Query the sum of range(1, 3)
        System.out.println(st.queryRange(1, 3)); 

        // Update the range(1, 5) with +3
        st.updateRange(1, 5, 3);

        // Query again the sum of range(1, 3)
        System.out.println(st.queryRange(1, 3)); 
    }
}
