package org.home.algorithms.array;

import java.util.Random;

public final class Arrays {

    /**
     * Selection sort uses ~N^2/2 compares and N swaps.
     * <p>
     * Properties
     * <ul>
     *     <li>in-place</li>
     *     <li>not stable</li>
     * </ul>
     * <p>
     * Time complexity
     * <ul>
     *     <li>Best case: ~N^2/2</li>
     *     <li>Average case: N^2/2</li>
     *     <li>Worst case: ~N^2/2</li>
     * </ul>
     * <p>
     * Notes:
     * <ul>
     *     <li>Running time insensitive to input. Time complexity is same for sorted and unsorted input.</li>
     *     <li>Data movement is minimal. Linear number of exchanges.</li>
     * </ul>
     */
    public static <T extends Comparable<T>> void selectionSort(T[] a) {
        for (int i = 0, n = a.length; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    /**
     * Insertion sort uses ~(N^2)/4 compares and ~(N^2)/4 swaps on average to sort a randomly-ordered
     * array with distinct keys.
     * <p>
     * Properties
     * <ul>
     *     <li>in-place</li>
     *     <li>stable</li>
     * </ul>
     * <p>
     * Time complexity
     * <ul>
     *     <li>Best case: ~N - if the array is in ascending order, insertion sort makes N-1 compares and 0 swaps</li>
     *     <li>Average case: ~(N^2)/4</li>
     *     <li>Worst case: ~(N^2)/2 - if the array is in descending order (and no duplicates), insertion sort
     *     makes ~(N^2)/2 compares and the same number of swaps</li>
     * </ul>
     * <p>
     * For partially-sorted arrays, insertion sort runs in linear time. Number of swaps equals the number of inversions
     * (pairs of keys that are out of order). Number of compares equals the number of swaps plus (N - 1).
     * <p>
     * Use cases:
     * <ul>
     *     <li>small N</li>
     *     <li>partially ordered arrays</li>
     * </ul>
     */
    public static <T extends Comparable<T>> void insertionSort(T[] a) {
        hsort(a, 1);
    }

    /**
     * <p>Shellsort with the 3x + 1 increments uses O(N^(3/2)) compares in the worst-case.</p>
     * <p>The number of compares used by shellsort with the 3x + 1 increments is bounded by a small
     * multiple of n times the number of increments used.</p>
     * <p> A g-sorted array remains g-sorted after h-sorting it. So if you sort an array using 7-sort and then 3-sort
     * the array is still 7-sorted.</p>
     * <p>
     * Properties
     * <ul>
     *     <li>in-place</li>
     *     <li>not stable</li>
     * </ul>
     * <p>
     * Time complexity
     * <ul>
     *     <li>Best case: ~N</li>
     *     <li>Average case: ? - accurate model has not yet been discovered</li>
     *     <li>Worst case: ? - accurate model has not yet been discovered</li>
     * </ul>
     * <p>
     * Applications
     * <ul>
     *     <li>fast unless array size is huge (used for small subarrays)</li>
     *     <li>tiny, fixed footprint for code (used in some embedded systems)</li>
     *     <li>hardware sort prototype</li>
     * </ul>
     * <p>
     * Which increment sequence to use?
     * <ol>
     *     <li>3x + 1</li>
     *     <li>Sedgewick: 1, 5, 19, 41, 109, 209, etc.</li>
     * </ol>
     */
    public static <T extends Comparable<T>> void shellSort(T[] a) {
        int h = 1;
        while (h < a.length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            hsort(a, h);
            h /= 3;
        }
    }

    /**
     * <p>Mergesort uses at most NlgN compares and 6NlgN array accesses to sort any array of size N.</p>
     * <p>Mergesort uses extra space proportional to N - the array aux needs to be of size N for the last merge.</p>
     * <p>
     * Properties
     * <ul>
     *     <li>not in-place - sorting algorithm is in-place if it uses <=clogN extra memory</li>
     *     <li>stable - as long as a duplicate item is always selected from the same sub-array</li>
     *     <li>duplicate keys - always between NlgN/2 and NlgN compares</li>
     * </ul>
     * </p>
     * <p>
     * Time complexity
     * <ul>
     *     <li>Best case: ~NlgN</li>
     *     <li>Average case: ~NlgN</li>
     *     <li>Worst case: ~NlgN</li>
     * </ul>
     * </p>
     * <p>
     * Java uses tuned mergesort for objects - assumption is that if the client uses objects then it means
     * that space is not critically important.
     * </p>
     */
    public static <T extends Comparable<T>> void mergeSort(T[] a) {
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) java.util.Arrays.copyOf(new Object[0], a.length, a.getClass());

        mergeSort(a, aux, 0, a.length - 1);
    }

    /**
     * Bottom-up merge sort is about 10% slower than recursive, top-down mergesort.
     */
    public static <T extends Comparable<T>> void bottomUpMergeSort(T[] a) {
        int n = a.length;
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) java.util.Arrays.copyOf(new Object[0], n, a.getClass());

        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    /**
     * <p>Quick sort uses on average ~2nlgN compares and ~(NlgN)/3 swaps to sort an array of N distinct keys.</p>
     * <p>
     * Properties
     * <ul>
     *     <li>in-place - no extra space required to swap items</li>
     *     <li>not stable - partitioning does long distance exchanges; In order to make quicksort stable
     *     and partitioning easier use an extra array (not worth the cost)</li>
     *     <li>duplicate keys - goes quadratic unless partitioning stops on items equal to the partitioning item</li>
     * </ul>
     * </p>
     * <p>
     * Time complexity
     * <ul>
     *     <li>Best case: ~NlgN</li>
     *     <li>Average case: ~1.39NlnN - 39% more compares than mergesort, but faster than mergesort in practice
     *     because of less data movement</li>
     *     <li>Worst case: ~(N^2)/2 - when input is sorted, but shuffling makes it unlikely to happen</li>
     * </ul>
     * </p>
     * <p>Java uses tuned quicksort for primitive types.</p>
     */
    public static <T extends Comparable<T>> void quickSort(T[] a) {
        Arrays.knuthShuffle(a); // needed for performance guarantee
        quickSort(a, 0, a.length - 1);
    }

    /**
     * <p>The goal of 3-way partitioning is to speed up quicksort in the presence of duplicate keys.</p>
     * <p>3-way partitioning puts all items equal to the partitioning item in place.</p>
     * <p>Randomized quicksort with 3-way partitioning reduces running time from linearithmic to linear
     * in broad class of applications.</p>
     * <p>
     * Properties
     * <ul>
     *     <li>in-place</li>
     *     <li>not stable</li>
     * </ul>
     * </p>
     * <p>
     * Time complexity
     * <ul>
     *     <li>Best case: ~N - a constant number of distinct keys</li>
     *     <li>Average case: ~2NlnN - when all distinct keys</li>
     *     <li>Worst case: ~(N^2)/2 - input is sorted, but shuffling makes it unlikely to happen</li>
     * </ul>
     * </p>
     */
    public static <T extends Comparable<T>> void threeWayQuickSort(T[] a) {
        Arrays.knuthShuffle(a); // needed for performance guarantee
        threeWayQuickSort(a, 0, a.length - 1);
    }

    /**
     * <p>Heap construction uses ≤2N compares and swaps.</p>
     * <p>Heapsort uses ≤2NlgN compares and swaps.</p>
     * <p>
     * Properties:
     * <ul>
     *     <li>in-place</li>
     *     <li>not stable</li>
     * </ul>
     * </p>
     * <p>
     * Time complexity:
     * <ul>
     *     <li>Best case: ~NlgN</li>
     *     <li>Average case: ~2NlgN</li>
     *     <li>Worst case: ~2NlgN</li>
     * </ul>
     * </p>
     * <p>Heapsort is optimal for both time and space, but:
     * <ul>
     *     <li>inner loop longer than quicksort's</li>
     *     <li>makes poor use of cache memory</li>
     * </ul>
     * </p>
     */
    public static <T extends Comparable<T>> void heapSort(T[] a) {
        int n = a.length;
        for (int k = n / 2; k > 0; k--) sink(a, k, n);

        while (n > 1) {
            swap(a, 0, n - 1);
            sink(a, 1, --n);
        }
    }

    private static <T extends Comparable<T>> void sink(T[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a[j - 1], a[j])) j++;
            if (!less(a[k - 1], a[j - 1])) break;
            swap(a, k - 1, j - 1);
            k = j;
        }
    }

    /**
     * Partition array into 3 parts so that:
     * <ul>
     *     <li>entries between lt and gt equal to partition item v</li>
     *     <li>no larger entries to left of lt</li>
     *     <li>no smaller entries to right of gt</li>
     *     <li>entries between lt and i known to be equal to partition item v</li>
     * </ul>
     */
    private static <T extends Comparable<T>> void threeWayQuickSort(T[] a, int lo, int hi) {
        if (lo >= hi) return;

        T v = a[lo];
        int i = lo, lt = lo, gt = hi;

        while (i <= gt) {
            if (less(a[i], v)) swap(a, lt++, i++);
            else if (less(v, a[i])) swap(a, gt--, i);
            else i++;
        }

        threeWayQuickSort(a, lo, lt - 1);
        threeWayQuickSort(a, gt + 1, hi);
    }

    private static <T extends Comparable<T>> void quickSort(T[] a, int lo, int hi) {
        /*
        Quicksort has too much overhead for tiny sub-arrays.
        One of possible improvements is to introduce a cutoff parameter (10-20) defining when to use a different
        sorting algorithm. This may improve performance by 20%.

        lo + CUTOFF - 1 >= hi Arrays.insertionSort(a, lo, hi); return;
        */
        if (lo >= hi) return;

        /*
        Another practical improvement is to estimate median by taking median of sample
        for a pivot item (median of three random items).
        This change makes quicksort to perform slightly fewer compares (~12/7 nlnn) and slightly more swaps (~12/35 nlnn).

        int m = medianOfThree(a, lo, lo + (hi - lo) / 2, hi);
        swap(a, lo, m);
        */

        int j = partition(a, lo, hi);
        quickSort(a, lo, j - 1);
        quickSort(a, j + 1, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            // when duplicates are present, it is counter-intuitively better to stop on
            // keys equal to the partitioning item's key
            while (less(a[++i], a[lo])) if (i == hi) break;
            // in fact, the if statement is redundant because
            // when j is equal to lo, a[lo] == a[j] so the while condition isn't met
            while (less(a[lo], a[--j])) if (j == lo) break;
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;  // index of item now known to be in place
    }

    private static <T extends Comparable<T>> void mergeSort(T[] a, T[] aux, int lo, int hi) {
        // Mergesort has too much overhead for tiny sub-arrays.
        // One of possible improvements is to introduce a cutoff parameter (7) defining when to use a different sorting
        // lo + CUTOFF - 1 >= hi Arrays.insertionSort(a, lo, hi); return;
        if (lo >= hi) return;

        int mid = lo + ((hi - lo) / 2);
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        if (less(a[mid], a[mid + 1])) return; // helps for partially-ordered arrays
        merge(a, aux, lo, mid, hi);
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    /**
     * <p>Knuth shuffling algorithm produces a uniformly random permutation of
     * the input array in linear time, assuming integers uniformly at random.</p>
     * <p>Time complexity: O(n)</p>
     * <p>Common bug: pick integer r between 0 and N-1 instead of i and N-1.</p>
     * <p>
     * Incorrect implementation of shuffling a deck of cards
     * <pre>
     * for (int i = 1; i < 52; i++) {
     *   int r = random(51) + 1;
     *   swap(a, i, r);
     * }
     * </pre>
     * </p>
     * Bugs:
     * <ol>
     *    <li>Random number r never 52 - 52nd card can't end up in 52nd place</li>
     *    <li>Shuffle not uniform (should be between 1 and i)</li>
     *    <li>#random uses 32-bit seed - 2^32 possible shuffles - not enough possible shuffles, number of possible
     *    shuffles is 52 factorial</li>
     *    <li>Seed = milliseconds since midnight = 86.4 million shuffles</li>
     * </ol>
     * Exploit: After seeing 5 cards and synchronizing with server clock, can determine all future cards in real time.
     */
    public static <T extends Comparable<T>> void knuthShuffle(T[] a) {
        final Random random = new Random();
        for (int i = 0, n = a.length; i < n; i++) {
            swap(a, i, random.nextInt(i + 1)); // [0-i]
        }
    }

    private static <T extends Comparable<T>> void hsort(T[] a, int h) {
        for (int i = h; i < a.length; i++) {
            for (int j = i; j >= h; j -= h) {
                if (less(a[j], a[j - h])) swap(a, j, j - h);
                else break;
            }
        }
    }

    /**
     * <p>Quick-select selects kth SMALLEST item (min: k=0; max: k=N-1; median: k=N/2).</p>
     * <p>Quick-select uses ~(N^2)/2 compares in the worst case, but (as with quicksort) the random shuffle
     * provides a probabilistic guarantee.</p>
     * <p>Quick-select takes linear time on average - each partitioning step splits array
     * approximately in half (~2N compares).</p>
     */
    public static <T extends Comparable<T>> T quickSelect(T[] a, int k) {
        Arrays.knuthShuffle(a);
        int lo = 0, hi = a.length - 1;

        while (lo < hi) {
            int j = partition(a, lo, hi);
            if (k < j) hi = j - 1;
            else if (k > j) lo = j + 1;
            else return a[k];
        }

        return a[k]; // lo == hi == k
    }

    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }

    // Suppresses default constructor, ensuring non-instantiability.
    private Arrays() {
        throw new AssertionError();
    }
}
