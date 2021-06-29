import java.util.HashMap;

public class Customer extends User 
{
    ShoppingCart shoppingcart=new ShoppingCart();
    Category category;
    Product product;
    public void createCustomer(String userName,String userPassword)
    {
        setUserID();
        setUserName(userName);
        setUserPassword(userPassword);
    }
    public void addProductToCart(String categoryID,String productID,int productQuantity)
    {
        category=getCategoryByID(categoryID);
        product=getProductByID(category,productID);
        shoppingcart.cartProducts.put(product,productQuantity);
    } 
}