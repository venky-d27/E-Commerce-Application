import java.util.HashMap;
import java.util.UUID;

public class Admin extends User
{
    HashMap<String,Customer> customerList=new HashMap<String,Customer>();
    HashMap<String,Coupon> couponList=new HashMap<String,Coupon>();

    Coupon coupon;
    Category category = new Category();
    Product product = new Product();

    public void generateCoupon(double discountPercent)
    {
        coupon=new Coupon();
        UUID uuid=UUID.randomUUID();
        String couponCode= uuid.toString();
        coupon.setCouponCode(couponCode);
        coupon.setDiscountPercent(discountPercent); 
        coupon.setCouponAvailed(false);
        couponList.put(couponCode,coupon);
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

    public boolean checkCategoryID(String categoryID)
    {
        for(String i: ECommerceSystem.shop.categoryList.keySet())
        {
            if(i.equals(categoryID))
            {
                return true;
            }
        }
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
            ECommerceSystem.shop.categoryList.get(categoryID).productList.put(product.productID,product);
            System.out.println("Product Successfully Addded!!!");
            // System.out.println(shop.categoryList.toString());
        }
        
    }
}
