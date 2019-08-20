package provider.service;

/**
 * @author: guangxush
 * @create: 2019/08/20
 */
public class HeapSortImpl implements Sort {
    public int[] sort(int[] array) {
        heapSort(array);
        return array;
    }

    private void heapSort(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            adjustHeap(array, 0, i);
        }
    }

    private void adjustHeap(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < length) {
            if (childIndex + 1 < length && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            if (temp > array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }
        array[parentIndex] = temp;
    }
}
