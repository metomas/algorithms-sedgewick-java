package org.home.algorithms.array;

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
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j >= 1; j -= 1) {
                if (less(a[j], a[j - 1])) swap(a, j, j - 1);
                else break;
            }
        }
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
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(a[j], a[j - h])) swap(a, j, j - h);
                    else break;
                }
            }
            h /= 3;
        }
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
