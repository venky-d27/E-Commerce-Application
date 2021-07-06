public class Coupon 
{
    private String couponCode;
    private double discountPercent;  

    public String getCouponCode() 
    {
        return couponCode;
    }

    public double getDiscountPercent() 
    {
        return discountPercent;
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
