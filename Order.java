import java.security.Timestamp;
import java.util.HashMap;

public class Order 
{
    String orderID;
    Timestamp orderTimestamp;
    Customer customer;
    HashMap<Product,Integer> orderedProducts= new HashMap<Product,Integer>();
    OrderStatus orderStatus;
    double totalCost;
}
