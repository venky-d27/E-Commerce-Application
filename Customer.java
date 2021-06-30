import java.util.HashMap;

public class Customer extends User 
{
    String orderID;
    
    ShoppingCart shoppingcart=new ShoppingCart();
    Category category;
    Product product;
    HashMap<String,Order> ordersHistory=new HashMap<String,Order>();
    Order order;
    public HashMap<String, Order> getOrdersHistory() 
    {
        return ordersHistory;
    }
    public void createCustomer(String userName,String userPassword,long userMobileNumber)
    {
        setUserID();
        setUserName(userName);
        setUserPassword(userPassword);
        setUserMobileNumber(userMobileNumber); 
    }
    public void addProductToCart(Product product,int productQuantity)
    {
        
        shoppingcart.cartProducts.put(product,productQuantity);
    } 

    public void removeProductFromCart(Product product)
    {
        shoppingcart.cartProducts.remove(product);
    }

    public double calculateTotalAmount(ShoppingCart shoppingcart)
    {
        double totalCost=0.0;
        for(Product cartProduct: shoppingcart.cartProducts.keySet())
        {
            totalCost+= cartProduct.getProductPrice()*shoppingcart.cartProducts.get(cartProduct);
        }
        return totalCost;
    }
    public Order placeOrder(ShoppingCart shoppingcart)
    {
        order=new Order();
        String orderID=order.generateOrderID();
        order.setOrderID(orderID);
        order.setOrderTimestamp();
        order.setOrderStatus(OrderStatus.ORDERED);
        for(Product cartProduct: shoppingcart.cartProducts.keySet())
        {
            order.getOrderedProducts().put(cartProduct,shoppingcart.cartProducts.get(cartProduct));
        }
        order.setTotalCost(calculateTotalAmount(shoppingcart));
        clearShoppingCart();
        return order;
    }
    public void clearShoppingCart()
    {
        shoppingcart.cartProducts.clear();
    }
}