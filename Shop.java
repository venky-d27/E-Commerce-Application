import java.util.HashMap;

public class Shop 
{
    HashMap<String,Category> categoryList=new HashMap<String,Category>();

    HashMap<String,Category> getCategories()
    {
        return categoryList;
    }

    Category getProductByID(String categoryID)
    {
        for(String i: categoryList.keySet())
        {
            if(categoryList.get(i).categoryID==categoryID)
            {
                return categoryList.get(i);
            }
        }
        return null;
    }
    
}
