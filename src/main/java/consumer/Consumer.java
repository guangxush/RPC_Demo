package consumer;

import consumer.service.SortRemoteImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import provider.service.Sort;

import java.util.Arrays;

/**
 * @author: guangxush
 * @create: 2019/08/20
 */
public class Consumer {
    private static Logger log = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) {
        Sort sort = new SortRemoteImpl();
        int[] array = new int[]{1, 10, 3, 2, 5, 8, 6, 4, 9, 7};
        int[] result = sort.sort(array);
        log.info("result is {}", Arrays.toString(result));
    }
}
