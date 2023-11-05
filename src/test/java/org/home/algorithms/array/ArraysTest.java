package org.home.algorithms.array;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ArraysTest {

    @ParameterizedTest
    @ArgumentsSource(SortingArgumentsProvider.class)
    void selectionSort(Integer[] actual, Integer[] expected) {
        Arrays.selectionSort(actual);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(SortingArgumentsProvider.class)
    void insertionSort(Integer[] actual, Integer[] expected) {
        Arrays.insertionSort(actual);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(SortingArgumentsProvider.class)
    void shellSort(Integer[] actual, Integer[] expected) {
        Arrays.shellSort(actual);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(SortingArgumentsProvider.class)
    void mergeSort(Integer[] actual, Integer[] expected) {
        Arrays.mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(SortingArgumentsProvider.class)
    void bottomUpMergeSort(Integer[] actual, Integer[] expected) {
        Arrays.bottomUpMergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(SortingArgumentsProvider.class)
    void quickSort(Integer[] actual, Integer[] expected) {
        Arrays.quickSort(actual);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(QuickSelectArgumentsProvider.class)
    void quickSelect(Integer[] a, int k, int expected) {
        assertEquals(expected, Arrays.quickSelect(a, k));
    }

    public static class SortingArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext ignore) {
            return Stream.of(
                    arguments(empty(), empty()),
                    arguments(oneElement(), oneElement()),
                    arguments(shuffled(), sorted()),
                    arguments(sorted(), sorted()),
                    arguments(almostSorted(), sorted()),
                    arguments(reversed(), sorted())
            );
        }

        private static Integer[] empty() {
            return new Integer[0];
        }

        private static Integer[] oneElement() {
            return new Integer[]{1};
        }


        private static Integer[] shuffled() {
            return new Integer[]{5, 4, 1, 3, 2, 6};
        }

        private static Integer[] sorted() {
            return new Integer[]{1, 2, 3, 4, 5, 6};
        }

        private static Integer[] almostSorted() {
            return new Integer[]{1, 5, 2, 3, 4, 6};
        }

        private static Integer[] reversed() {
            return new Integer[]{6, 5, 4, 3, 2, 1};
        }

    }

    private static class QuickSelectArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext ignore) {
            Integer[] a = {4, 5, 6, 1, 2, 3};
            return Stream.of(
                    arguments(a, 0, 1),
                    arguments(a, a.length / 2, 4),
                    arguments(a, a.length - 1, 6)
            );
        }

    }
}