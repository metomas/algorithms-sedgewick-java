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
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) swap(a, j, j - 1);
                else break;
            }
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
