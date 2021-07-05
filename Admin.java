import java.util.HashMap;
import java.util.UUID;

public class Admin extends User
{
    HashMap<String,Customer> customerList=new HashMap<String,Customer>();
    static HashMap<String,Coupon> couponList=new HashMap<String,Coupon>();
    HashMap<String,Order> customerOrders=new HashMap<String,Order>();
    Coupon coupon;
    Category category;
    Product product;

    public void generateCoupon(double discountPercent)
    {
        coupon=new Coupon();
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
        category = new Category();
        category.setCategoryID(Integer.toString(ECommerceSystem.shop.categoryList.size()+1));
        category.setCategoryName(categoryName);
        ECommerceSystem.shop.categoryList.put(category.categoryID,category);
        System.out.println("Category Successfully Addded!!!");
        // System.out.println(shop.categoryList.toString());
    }
    public HashMap<String, Order> getCustomerOrders() 
    {
        return customerOrders;
    }
    public boolean checkCategoryID(String categoryID)
    {
        for(String i: ECommerceSystem.shop.categoryList.keySet())
        {
            if(i.equals(categoryID))
            {
                return true;
            }
        }
        System.out.println("Wrong Category ID");
        return false;
    }

    public boolean checkProductID(String categoryID,String productID)
    {
        if(ECommerceSystem.shop.categoryList.get(categoryID).getProducts().get(productID)!=null)
        {
            return true;
        }
        System.out.println("Wrong Product ID");
        return false;
    }

    public boolean checkOrderID(String orderID)
    {
        if(customerOrders.get(orderID)!=null)
        {
            return true;
        }
        System.out.println("Wrong Order ID");
        return false;
    }

    public void addProduct(String categoryID,String productName,double productPrice,int productAvailability,String productDescription)
    {
        
        // System.out.println(shop.categoryList.toString());
        if(checkCategoryID(categoryID))
        {
            product=new Product();
            product.setProductName(productName);
            product.setProductDescription(productDescription);
            product.setProductAvailability(productAvailability);
            product.setProductPrice(productPrice);
            product.setProductID(Integer.toString(ECommerceSystem.shop.categoryList.get(categoryID).productList.size()+1));
            ECommerceSystem.shop.categoryList.get(categoryID).productList.put(product.getProductID(),product);
            System.out.println("Product Successfully Addded!!!");
            // System.out.println(shop.categoryList.toString());
        }
        
    }
    
    public void modifyProduct(String categoryID,String productID,String productName,double productPrice,int productAvailability,String productDescription)
    {
        category=getCategoryByID(categoryID);
        product=getProductByID(category, productID);
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

    public boolean customerSignIn(String customerID, String customerPassword)
    {
        for(String i: customerList.keySet())
        {
            if(i.equals(customerID))
            {
                if(customerList.get(i).getUserPassword().equals(customerPassword))
                {
                    System.out.println("SignIn Successfull!!");
                    return true;
                }
                System.out.println("Wrong Password!!!");
                return false;
            }
        }
        System.out.println("Account doesn't Exist!!! Check customer ID");
        return false;
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

    

    public boolean checkAvailability(String categoryID,String productID,int quantity)
    {
        product=ECommerceSystem.shop.categoryList.get(categoryID).productList.get(productID);
        if(product.getProductAvailability()>=quantity)
        {
            product.setProductAvailability(product.getProductAvailability()-quantity);
            return true;
        }
        System.out.println("Only "+product.getProductAvailability()+"products are available!!!");
        return false;
    }

}
