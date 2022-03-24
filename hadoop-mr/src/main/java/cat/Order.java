package cat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements WritableComparable<Order> {
    private int orderId;
    private int price;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(orderId);
        out.writeInt(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        orderId = in.readInt();
        price = in.readInt();
    }

    @Override
    public int compareTo(Order bean) {
        // 先按照定id升序排序，如果相同 按照价格降序排序
        if (orderId > bean.getOrderId()) {
            return 1;
        } else if (orderId < bean.getOrderId()) {
            return -1;
        } else {
            if (price > bean.getPrice()) {
                return -1;
            } else if (price < bean.getPrice()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return orderId + "," + price;
    }
}
