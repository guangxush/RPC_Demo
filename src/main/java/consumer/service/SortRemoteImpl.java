package consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import provider.service.Sort;
import request.SortRpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: guangxush
 * @create: 2019/08/20
 */
public class SortRemoteImpl implements Sort{
    public static final int PORT = 8081;
    private static Logger log = LoggerFactory.getLogger(SortRemoteImpl.class);

    public int[] sort(int[] array) {
        List<String> addressList = lookupProviders("Sort.quickSort");
        String address = chooseTarget(addressList);
        try {
            Socket socket = new Socket(address, PORT);

            // 将请求序列化
            SortRpcRequest sortRpcRequest = generateRequest(array);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // 将请求发给服务提供方
            objectOutputStream.writeObject(sortRpcRequest);

            // 将响应体反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object response = objectInputStream.readObject();

            log.info("response is {}", response);

            if (response instanceof int[]) {
                return (int[]) response;
            } else {
                throw new InternalError();
            }

        } catch (Exception e) {
            log.error("fail", e);
            throw new InternalError();
        }
    }


    private SortRpcRequest generateRequest(int[] array) {
        SortRpcRequest calculateRpcRequest = new SortRpcRequest();
        calculateRpcRequest.setArray(array);
        calculateRpcRequest.setMethod("quickSort");
        return calculateRpcRequest;
    }

    /**
     * 负载均衡
     * @param providers
     * @return
     */
    private String chooseTarget(List<String> providers) {
        if (null == providers || providers.size() == 0) {
            throw new IllegalArgumentException();
        }
        return providers.get(0);
    }

    /**
     * 服务发现
     * @param name
     * @return
     */
    public static List<String> lookupProviders(String name) {
        List<String> strings = new ArrayList();
        strings.add("127.0.0.1");
        return strings;
    }
}
