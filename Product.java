public class Product 
{
    String productID;
    String productName;
    double productPrice;
    int productAvailability;
    String productDescription;

    public String getProductDescription() 
    {
        return productDescription;
    }
    
    public int getProductAvailability() 
    {
        return productAvailability;
    }

    public String getProductID() 
    {
        return productID;
    }

    public String getProductName() 
    {
        return productName;
    }

    public double getProductPrice() 
    {
        return productPrice;
    }

    public void setProductDescription(String ProductDescription)
    {
        this.productDescription = ProductDescription;
    }

    public void setProductAvailability(int productAvailability) 
    {
        this.productAvailability = productAvailability;
    }
    public void setProductID(String productID) 
    {
        this.productID = productID;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }
    public void setProductPrice(double productPrice) 
    {
        this.productPrice = productPrice;
    }
    
    
}
