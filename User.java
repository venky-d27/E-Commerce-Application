import java.util.*;
public class User 
{ 
    private String userID;
    private String userName;
    private String userPassword;
    private long userMobileNumber;

    public String getUserID() 
    {
        return userID;
    }
    public String getUserName() 
    {
        return userName;
    }
    public String getUserPassword() 
    {
        return userPassword;
    }
    public long getUserMobileNumber() 
    {
        return userMobileNumber;
    }
    public void setUserID()
    {
        UUID uuid=UUID.randomUUID(); 
        this.userID = uuid.toString();
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }
    public void setUserPassword(String userPassword) 
    {
        this.userPassword = userPassword;
    }
    public void setUserMobileNumber(long userMobileNumber) 
    {
        this.userMobileNumber = userMobileNumber;
    }

    public HashMap<String,Category> getAllCategory()
    {
        return ECommerceSystem.shop.categoryList;
    }

    public HashMap<String, Product> getAllProduct(String categoryID)
    {
        return ECommerceSystem.shop.categoryList.get(categoryID).productList;
    }

    public Product getProductByID(Category category, String productID)
    {
        return category.productList.get(productID);
    }

    public Category getCategoryByID(String categoryID)
    {
        // for(String i: categoryList.keySet())
        // {
        //     if(categoryList.get(i).categoryID.equals(categoryID))
        //     {
        //         System.out.println(categoryList.get(i).toString());
        //         return categoryList.get(i);
        //     }
        // }
        // return null;
        return ECommerceSystem.shop.categoryList.get(categoryID);
    }

    public HashMap<String,Category> getCategories()
    {
        return ECommerceSystem.shop.categoryList;
    }

    
}
