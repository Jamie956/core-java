package cat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table implements Writable {
    private String orderId;
    private String productId;
    private String productName;
    private String flag;

    /**
     * map 写出时调
     */
    @Override
    public void write(DataOutput out) throws IOException {
        // 序列化方法
        out.writeUTF(orderId);
        out.writeUTF(productId);
        out.writeUTF(productName);
        out.writeUTF(flag);
    }

    /**
     * reduce 读取时调
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        // 反序列化方法
        orderId = in.readUTF();
        productId = in.readUTF();
        productName = in.readUTF();
        flag = in.readUTF();
    }

    @Override
    public String toString() {
        return orderId + "\t" + productId + "\t" + productName;
    }
}
