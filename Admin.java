import java.util.HashMap;
import java.util.UUID;

public class Admin extends User
{
    HashMap<String,Customer> customerList=new HashMap<String,Customer>();
    HashMap<String,Coupon> couponList=new HashMap<String,Coupon>();

    Coupon coupon;
    
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

}
