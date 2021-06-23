import java.util.HashMap;

public class ShoppingCart 
{
    HashMap<Product,Integer> cartProducts=new HashMap<Product,Integer>();

    public HashMap<Product,Integer> getCartProducts()
    {
        return cartProducts;
    }
}
