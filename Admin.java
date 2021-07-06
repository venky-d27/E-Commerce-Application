import java.util.HashMap;
import java.util.UUID;

public class Admin extends User
{
    HashMap<String,Customer> customerList=new HashMap<String,Customer>();
    static HashMap<String,Coupon> couponList=new HashMap<String,Coupon>();
    HashMap<String,Order> customerOrders=new HashMap<String,Order>();
    

    public void generateCoupon(double discountPercent)
    {
        Coupon coupon=new Coupon();
        UUID uuid=UUID.randomUUID();
        String couponCode= uuid.toString();
        coupon.setCouponCode(couponCode);
        coupon.setDiscountPercent(discountPercent); 
        couponList.put(couponCode,coupon);
        System.out.println(couponCode);
    }

    public HashMap<String,Coupon> getCouponList()
    {
        return couponList;
    }
    public void addCategory(String categoryName)
    {
        Category category = new Category();
        UUID uuid=UUID.randomUUID(); 
        category.setCategoryID(uuid.toString());
        category.setCategoryName(categoryName);
        ECommerceSystem.shop.categoryList.put(category.categoryID,category);
        // System.out.println(shop.categoryList.toString());
    }
    public HashMap<String, Order> getCustomerOrders() 
    {
        return customerOrders;
    }
    public boolean checkCategoryID(String categoryID)
    {
        return ECommerceSystem.shop.categoryList.keySet().contains(categoryID);
    }

    public boolean checkProductID(String categoryID,String productID)
    {
        return ECommerceSystem.shop.categoryList.get(categoryID).getProducts().keySet().contains(productID);
    }

    public boolean checkOrderID(String orderID)
    {
        if(customerOrders.get(orderID)!=null)
        {
            return true;
        }
        return false;
    }

    public void addProduct(String categoryID,String productName,double productPrice,int productAvailability,String productDescription)
    {    
        // System.out.println(shop.categoryList.toString());
            Product product=new Product();
            UUID uuid=UUID.randomUUID(); 
            product.setProductName(productName);
            product.setProductDescription(productDescription);
            product.setProductAvailability(productAvailability);
            product.setProductPrice(productPrice);
            product.setProductID(uuid.toString());
            ECommerceSystem.shop.categoryList.get(categoryID).productList.put(product.getProductID(),product);
            
            // System.out.println(shop.categoryList.toString());
    }
    
    public void modifyProduct(String categoryID,String productID,String productName,double productPrice,int productAvailability,String productDescription)
    {
        Category category=getCategoryByID(categoryID);
        Product product=getProductByID(category, productID);
        product.setProductName(productName);
        product.setProductAvailability(productAvailability);;
        product.setProductDescription(productDescription);;
        product.setProductPrice(productPrice);;
        ECommerceSystem.shop.categoryList.get(categoryID).productList.put(productID,product);
    }

    public void modifyCategory(String categoryID,String categoryName)
    {
        ECommerceSystem.shop.categoryList.get(categoryID).categoryName=categoryName;
    }

    public boolean customerSignIn(String customerID, String customerPassword) throws CustomerError
    {
        for(String i: customerList.keySet())
        {
            if(i.equals(customerID))
            {
                if(customerList.get(i).getUserPassword().equals(customerPassword))
                {
                    return true;
                }
                throw new CustomerError("Wrong Password!!!");

            }
        }
        throw new CustomerError("Account doesn't Exist!!! Check customer ID");
    }

    public void changeOrderStatus(String OrderID,OrderStatus orderStatus)
    {
        for(String i: customerOrders.keySet())
        {
            if(i.equals(OrderID))
            {
                customerOrders.get(OrderID).setOrderStatus(orderStatus);
            }
        }
    }

    

    public boolean checkAvailability(String categoryID,String productID,int quantity) throws ProductError
    {
        Product product=ECommerceSystem.shop.categoryList.get(categoryID).productList.get(productID);
        if(product.getProductAvailability()>=quantity)
        {
            product.setProductAvailability(product.getProductAvailability()-quantity);
            return true;
        }
        throw new ProductError("Only "+product.getProductAvailability()+"products are available!!!");
    }

}
