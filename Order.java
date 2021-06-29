import java.security.Timestamp;
import java.util.HashMap;

public class Order 
{
    private String orderID;
    private Timestamp orderTimestamp;
    private Customer customer;
    private HashMap<Product,Integer> orderedProducts= new HashMap<Product,Integer>();
    private OrderStatus orderStatus;
    private double totalCost;
}
