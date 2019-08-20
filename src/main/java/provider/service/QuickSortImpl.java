package provider.service;

/**
 * @author: guangxush
 * @create: 2019/08/20
 */
public class QuickSortImpl implements Sort {
    public int[] sort(int[] array) {
        quickSort(array, 0, array.length-1);
        return array;
    }

    private void quickSort(int[] a, int start, int end) {
        if (start < end) {
            int i = start;
            int j = end;
            int pivot = a[i];
            while (i < j) {
                while (i < j && pivot < a[j]) {
                    j--;
                }
                if (i < j) {
                    a[i] = a[j];
                    a[j] = pivot;
                    i++;
                }
                while (i < j && a[i] < pivot) {
                    i++;
                }
                if (i < j) {
                    a[j] = a[i];
                    a[i] = pivot;
                    j--;
                }
            }
            quickSort(a, start, i - 1);
            quickSort(a, i + 1, end);
        }
    }
}
