public class Coupon 
{
    String couponCode;
    double discountPercent;  
    boolean couponAvailed;

    public String getCouponCode() 
    {
        return couponCode;
    }

    public double getDiscountPercent() 
    {
        return discountPercent;
    }

    public boolean getCouponAvailed()
    {
        return couponAvailed;
    }

    public void setCouponAvailed(boolean couponAvailed) 
    {
        this.couponAvailed = couponAvailed;
    }

    public void setCouponCode(String couponCode) 
    {
        this.couponCode = couponCode;
    }

    public void setDiscountPercent(double discountPercent) 
    {
        this.discountPercent = discountPercent;
    }
}
