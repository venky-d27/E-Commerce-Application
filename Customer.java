import java.util.*;

public class Customer extends User 
{
    ShoppingCart shoppingcart=new ShoppingCart();
    HashMap<String,Order> ordersHistory=new HashMap<String,Order>();
    ArrayList<Coupon> couponAvailedList = new ArrayList<Coupon>();

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
    public Order placeOrder(ShoppingCart shoppingCart,Customer customer) throws CartError
    {
        if(shoppingCart.cartProducts.size()>0)
        {
            Order order=new Order();
            String orderID=order.generateOrderID();
            order.setOrderID(orderID);
            order.setOrderTimestamp();
            order.setOrderStatus(OrderStatus.ORDERED);
            order.setCustomer(customer);
            order.getOrderedProducts().putAll(new HashMap<>(shoppingCart.cartProducts));
            order.setTotalCost(calculateTotalAmount(shoppingCart));
            clearShoppingCart();
            return order;
        }
        throw new CartError("No Product in Shopping Cart!!!");
    }
    public void clearShoppingCart()
    {
        shoppingcart.cartProducts.clear();
    }
    public double verifyCoupon(String couponID) throws CouponError
    {
        for(String i: Admin.couponList.keySet())
        {
            if(i.equals(couponID))
            {
                return Admin.couponList.get(couponID).getDiscountPercent();
            }
            
        }
        throw new CouponError("Wrong Coupon ID");
       
    }
    public void applyCoupon(String couponID,Order order) throws CouponError
    {
        double discountPercent=verifyCoupon(couponID);
        double totalCost=order.getTotalCost();
        totalCost-=totalCost*discountPercent/100;
        order.setTotalCost(totalCost);

    }
    public void modifyProductQuantity(Product product,int quantity)
    {
        shoppingcart.cartProducts.put(product,quantity);
    }
}