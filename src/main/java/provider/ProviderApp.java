package provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import provider.service.HeapSortImpl;
import provider.service.QuickSortImpl;
import provider.service.Sort;
import request.SortRpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: guangxush
 * @create: 2019/08/20
 */
public class ProviderApp {
    private static Logger log = LoggerFactory.getLogger(ProviderApp.class);

    private Sort quickSort = new QuickSortImpl();

    private Sort heapSort = new HeapSortImpl();

    public static void main(String[] args) throws IOException {
        new ProviderApp().run();
    }

    private void run() throws IOException {
        ServerSocket listener = new ServerSocket(8081);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    // 将请求反序列化
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Object object = objectInputStream.readObject();

                    log.info("request is {}", object);

                    // 调用服务
                    int[] result = {};
                    if (object instanceof SortRpcRequest) {
                        SortRpcRequest sortRpcRequest = (SortRpcRequest) object;
                        if ("quickSort".equals(sortRpcRequest.getMethod())) {
                            result = quickSort.sort(sortRpcRequest.getArray());
                        } else if("heapSort".equals(sortRpcRequest.getMethod())){
                            result = heapSort.sort(sortRpcRequest.getArray());
                        }
                        else{
                            throw new UnsupportedOperationException();
                        }
                    }

                    // 返回结果
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(result);
                } catch (Exception e) {
                    log.error("fail", e);
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }
}
