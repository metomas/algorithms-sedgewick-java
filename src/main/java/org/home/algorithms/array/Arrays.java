package org.home.algorithms.array;

public final class Arrays {

    /**
     * Selection sort uses ~N^2/2 compares and N exchanges.
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
