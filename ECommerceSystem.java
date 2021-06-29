import java.util.*;
public class ECommerceSystem
{
    public static Shop shop=new Shop();
    public static void main(String[] args) 
    { 
        String userName;
        String userPassword;
        int userChoice,userStatus,userType;
        String categoryID;
        String categoryName;
        String productID;
        String productName;
        int productQuantity;
        double productPrice;
        int productAvailability;
        String productDescription;
        double discountPercent;

        Admin admin=new Admin();
        Customer customer=new Customer(); 
        Product product;
        Category category;

        Scanner sc=new Scanner(System.in);
        do
        {
            System.out.println("Enter type of User:\n1.Admin\n2.Customer");
            userType=sc.nextInt();
            if(userType==1)
            {
                do
                {
                    System.out.println("Enter the action to be performed:\n1.Add Category\n2.Add Product\n3.View Category\n4.View Product\n5.Generate Coupon\n6.View generated Coupons\n7.Exit");
                    userChoice=sc.nextInt();
                    switch(userChoice)
                    {
                        case 1: 
                                System.out.println("Enter the Category Name: ");
                                categoryName=sc.next();
                                admin.addCategory(categoryName);
                                break;
                        case 2:
                                System.out.println("Enter the Category ID: ");
                                categoryID=sc.next();
                                System.out.println("Enter the Product Name: ");
                                productName=sc.next();
                                System.out.println("Enter the Product Price: ");
                                productPrice=sc.nextDouble();
                                System.out.println("Enter the Product Availability: ");
                                productAvailability=sc.nextInt();
                                System.out.println("Enter the Product Description: ");
                                productDescription=sc.next();
                                admin.addProduct(categoryID, productName, productPrice, productAvailability, productDescription);
                                break;
                        case 3:
                                HashMap<String, Category> categoryList= admin.getAllCategory();
                                for(String i: categoryList.keySet())
                                {
                                    System.out.println(i+" "+categoryList.get(i).categoryName);
                                }
                                break;
                        case 4:
                                System.out.println("Enter Catgeory ID: ");
                                categoryID=sc.next();
                                HashMap<String, Product> productList= admin.getAllProduct(categoryID);
                                for(String i: productList.keySet())
                                {
                                    System.out.println(i+" "+productList.get(i).productName);
                                }
                                break;
                        case 5:
                                System.out.println("Enter discount Percent: ");
                                discountPercent=sc.nextDouble();
                                admin.generateCoupon(discountPercent);
                                break;
                        case 6: 
                                HashMap<String,Coupon> couponList=admin.getCouponList();
                                for(String i: couponList.keySet())
                                {
                                    System.out.println("Coupon Code: "+i+"\nDiscount Percent: "+couponList.get(i).discountPercent);
                                }
                                break;
                        case 7:
                                break;

                        default: System.out.println("Wrong Choice!!!");
                    }
                }while(userChoice!=7);
            }
            else if(userType==2)
            {
                do
                {
                    System.out.println("Choose one of the below Operation:\n1.Create Account\n2.Sign in\n3.Exit");
                    userStatus=sc.nextInt();
                    switch(userStatus)
                    {
                        case 1:
                                System.out.println("Enter Username: ");
                                userName=sc.next();
                                System.out.println("Enter User Password: ");
                                userPassword=sc.next();
                                customer=new Customer();
                                customer.createCustomer(userName, userPassword);
                                admin.customerList.put(customer.getUserID(),customer);
                                System.out.println(admin.customerList);
                                break;

                        case 2:
                                do
                                {
                                    System.out.println("Enter the Choice:\n1.Add Product to Cart\n2.View Category\n3.View Product\n4.View Product Details\n5.Exit");
                                    userChoice=sc.nextInt();
                                    switch(userChoice)
                                    {
                                        case 1: 
                                                System.out.println("Enter the Category ID: ");
                                                categoryID=sc.next();
                                                System.out.println("Enter the Product ID: ");
                                                productID=sc.next();
                                                System.out.println("Enter the Product Quantity: ");
                                                productQuantity=sc.nextInt();
                                                customer.addProductToCart(categoryID, productID, productQuantity);
                                                break;

                                        case 2:
                                            HashMap<String, Category> categoryList= customer.getAllCategory();
                                            for(String i: categoryList.keySet())
                                            {
                                                System.out.println(i+" "+categoryList.get(i).categoryName);
                                            }
                                            break;

                                        case 3:
                                                System.out.println("Enter Catgeory ID: ");
                                                categoryID=sc.next();
                                                HashMap<String, Product> productList= customer.getAllProduct(categoryID);
                                                for(String i: productList.keySet())
                                                {
                                                    System.out.println(i+" "+productList.get(i).productName);
                                                }
                                                break;
                                        case 4:
                                                System.out.println("Enter the Category ID: ");
                                                categoryID=sc.next();
                                                System.out.println("Enter the Product ID: ");
                                                productID=sc.next();
                                                category=customer.getCategoryByID(categoryID);
                                                product=customer.getProductByID(category, productID);
                                                System.out.println("Product Details:\nProduct ID: "+product.productID+"\nProduct Name: "+product.productName+"\nProduct Price: "+product.productPrice+"\nProduct Description: "+product.productDescription);
                                                break;
                                    }

                                }while(userChoice!=5);
                                break;
                        case 3:
                                break;
                        default: System.out.print("Wrong Choice!!!");
                    }
                }while(userStatus!=3);
            }
        }while(userType!=3);
    }
}