package request;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author: guangxush
 * @create: 2019/08/20
 */
public class SortRpcRequest implements Serializable {
    private static final long serialVersionUID = 7503710091945320739L;
    private String method;
    private int[] array;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "SortRpcRequest{" +
                "method='" + method + '\'' +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}
