import java.util.HashMap;

public class Customer extends User 
{
    String orderID;
    double discountAmount;
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
    public Order placeOrder(ShoppingCart shoppingcart,Customer customer)
    {
        if(shoppingcart.cartProducts.size()>0)
        {
            order=new Order();
            String orderID=order.generateOrderID();
            order.setOrderID(orderID);
            order.setOrderTimestamp();
            order.setOrderStatus(OrderStatus.ORDERED);
            order.setCustomer(customer);
            for(Product cartProduct: shoppingcart.cartProducts.keySet())
            {
                order.getOrderedProducts().put(cartProduct,shoppingcart.cartProducts.get(cartProduct));
            }
            order.setTotalCost(calculateTotalAmount(shoppingcart));
            clearShoppingCart();
            return order;
        }
        System.out.println("No Product in Shopping Cart!!!");
        return null;
    }
    public void clearShoppingCart()
    {
        shoppingcart.cartProducts.clear();
    }
    public double verifyCoupon(String couponID)
    {
        for(String i: Admin.couponList.keySet())
        {
            if(i.equals(couponID))
            {
                return Admin.couponList.get(couponID).discountPercent;
            }
            
        }
        System.out.println("Wrong Coupon ID");
        return 0.0;
    }
    public void applyCoupon(String couponID,Order order)
    {
        double discountPercent=verifyCoupon(couponID);
        double totalCost=order.getTotalCost();
        totalCost-=totalCost*discountPercent/100;
        order.setTotalCost(totalCost);
    }
}