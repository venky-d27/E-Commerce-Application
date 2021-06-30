import java.sql.Timestamp;
import java.util.*;

public class Order 
{
    private String orderID;
    private Timestamp orderTimestamp;
    private Customer customer;
    private HashMap<Product,Integer> orderedProducts= new HashMap<Product,Integer>();
    private OrderStatus orderStatus;
    private double totalCost;

    public Timestamp getOrderTimestamp() 
    {
        return orderTimestamp;
    }
    public String getOrderID()
    {
        return orderID;
    }
    public OrderStatus getOrderStatus() 
    {
        return orderStatus;
    }
    public HashMap<Product, Integer> getOrderedProducts() 
    {
        return orderedProducts;
    }
    public double getTotalCost() 
    {
        return totalCost;
    }
    public void setTotalCost(double totalCost) 
    {
        this.totalCost = totalCost;
    }
    public void setOrderedProducts(HashMap<Product, Integer> orderedProducts) 
    {
        this.orderedProducts = orderedProducts;
    }
    public void setOrderStatus(OrderStatus orderStatus) 
    {
        this.orderStatus = orderStatus;
    }
    public void setOrderID(String orderID) 
    {
        this.orderID = orderID;
    }
    public void setOrderTimestamp() 
    {
        Date date=new Date();
        long time=date.getTime();
        Timestamp ts= new Timestamp(time);
        this.orderTimestamp = ts;
    }
    public String generateOrderID()
    {
        UUID uuid=UUID.randomUUID(); 
        return uuid.toString();
    }
}
