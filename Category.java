import java.util.HashMap;

public class Category 
{
    String categoryID;
    String categoryName;
    HashMap<String,Product> productList=new HashMap<String,Product>();

    public String getCategoryID() 
    {
        return categoryID;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }

    public void setCategoryID(String categoryID) 
    {
        this.categoryID = categoryID;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }
    HashMap<String,Product> getProducts()
    {
        return productList;
    }

    

}
