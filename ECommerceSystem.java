import java.util.*;
public class ECommerceSystem
{
    public static Shop shop=new Shop();
    public static void main(String[] args) 
    { 
        String customerName;
        String customerID;
        String customerPassword;
        long customerMobileNumber;
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
        Order order;

        Scanner sc=new Scanner(System.in);
        do
        {
            System.out.println("Enter type of User:\n1.Admin\n2.Customer");
            userType=sc.nextInt();
            if(userType==1)
            {
                do
                {
                    System.out.println("Enter the action to be performed:\n1.Add Category\n2.Add Product\n3.View Category\n4.View Product\n5.Generate Coupon\n6.View generated Coupons\n7.Modify Product\n8.Modify Category\n9.View Customers Orders\n10.Exit");
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
                                    System.out.println(i+" "+productList.get(i).getProductName());
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
                                System.out.println("Enter the Category ID: ");
                                categoryID=sc.next();
                                System.out.println("Enter the Product ID: ");
                                productID=sc.next();
                                System.out.println("Enter the Product Name: ");
                                productName=sc.next();
                                System.out.println("Enter the Product Price: ");
                                productPrice=sc.nextDouble();
                                System.out.println("Enter the Product Availability: ");
                                productAvailability=sc.nextInt();
                                System.out.println("Enter the Product Description: ");
                                productDescription=sc.next();
                                admin.modifyProduct(categoryID, productID, productName, productPrice, productAvailability, productDescription);
                                System.out.println("Modified Product Successfully!!!");
                                break;
                        case 8:
                                System.out.println("Enter the Category ID: ");
                                categoryID=sc.next();
                                System.out.println("Enter the Category Name: ");
                                categoryName=sc.next();
                                admin.modifyCategory(categoryID, categoryName);
                                System.out.println("Modified Category Successfully!!!");
                                break;
                        case 9:
                                HashMap<String,Order> customerOrders=admin.getCustomerOrders();
                                for(String orderID: customerOrders.keySet())
                                {
                                    order=customerOrders.get(orderID);
                                    System.out.println("Order ID: "+order.getOrderID()+"\nCustomer ID:"+order.getCustomer().getUserID());
                                    for(Product orderedproduct: order.getOrderedProducts().keySet())
                                    {
                                        System.out.println("Product ID: "+orderedproduct.getProductID()+"\nProduct Name: "+orderedproduct.getProductName()+"\nProduct Price: "+orderedproduct.getProductPrice()+"\nProduct Description: "+orderedproduct.getProductDescription()+"\nProduct Quantity: "+order.getOrderedProducts().get(orderedproduct));
                                    }
                                }
                                break;
                                

                        default: System.out.println("Wrong Choice!!!");
                    }
                }while(userChoice!=10);
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
                                customerName=sc.next();
                                System.out.println("Enter User Password: ");
                                customerPassword=sc.next();
                                System.out.println("Enter User Mobile Number: ");
                                customerMobileNumber=sc.nextLong();
                                customer=new Customer();
                                customer.createCustomer(customerName, customerPassword,customerMobileNumber);
                                admin.customerList.put(customer.getUserID(),customer);
                                System.out.println(customer.getUserID());
                                break;

                        case 2:
                                System.out.println("Enter Your ID:  ");
                                customerID=sc.next();
                                System.out.println("Enter Your Password: ");
                                customerPassword=sc.next();
                                if(admin.customerSignIn(customerID,customerPassword))
                                {
                                    do
                                    {
                                        System.out.println("Enter the Choice:\n1.Add Product to Cart\n2.View Category\n3.View Product\n4.View Product Details\n5.Remove Product from Cart\n6.Place Order\n7.View Previous Orders\n8.View Cart Products\n9.Exit");
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
                                                    category=customer.getCategoryByID(categoryID);
                                                    product=customer.getProductByID(category,productID);
                                                    customer.addProductToCart(product, productQuantity);
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
                                                        System.out.println(i+" "+productList.get(i).getProductName());
                                                    }
                                                    break;
                                            case 4:
                                                    System.out.println("Enter the Category ID: ");
                                                    categoryID=sc.next();
                                                    System.out.println("Enter the Product ID: ");
                                                    productID=sc.next();
                                                    category=customer.getCategoryByID(categoryID);
                                                    product=customer.getProductByID(category, productID);
                                                    System.out.println("Product Details:\nProduct ID: "+product.getProductID()+"\nProduct Name: "+product.getProductName()+"\nProduct Price: "+product.getProductPrice()+"\nProduct Description: "+product.getProductDescription());
                                                    break;
                                            case 5:
                                                    System.out.println("Enter the Category ID: ");
                                                    categoryID=sc.next();
                                                    System.out.println("Enter the Product ID: ");
                                                    productID=sc.next();
                                                    category=customer.getCategoryByID(categoryID);
                                                    product=customer.getProductByID(category, productID);
                                                    customer.removeProductFromCart(product);
                                                    System.out.println("Product Successfully Removed from Cart!!!");
                                            case 6:
                                                    order=customer.placeOrder(customer.shoppingcart,customer);
                                                    customer.ordersHistory.put(order.getOrderID(), order);
                                                    admin.customerOrders.put(order.getOrderID(),order);
                                                    System.out.println(order.getTotalCost());
                                                    break;
                                            case 7:
                                                    HashMap<String,Order> ordersHistory=customer.getOrdersHistory();
                                                    for(String orderID: ordersHistory.keySet())
                                                    {
                                                        HashMap<Product,Integer> orderedProducts= ordersHistory.get(orderID).getOrderedProducts();
                                                        for(Product orderedproduct: orderedProducts.keySet())
                                                        {
                                                            System.out.println("Product ID: "+orderedproduct.getProductID()+"\nProduct Name: "+orderedproduct.getProductName()+"\nProduct Price: "+orderedproduct.getProductPrice()+"\nProduct Description: "+orderedproduct.getProductDescription()+"\nProduct Quantity: "+orderedProducts.get(orderedproduct));
                                                        }
                                                    }
                                                    break;
                                            case 8:
                                                    HashMap<Product,Integer> cartProducts=customer.shoppingcart.getCartProducts();
                                                    for(Product i: cartProducts.keySet())
                                                    {
                                                        System.out.println("Product ID: "+i.getProductID()+"\nProduct Name: "+i.getProductName()+"\nProduct Price: "+i.getProductPrice()+"\nProduct Description: "+i.getProductDescription()+"\nProduct Quantity: "+cartProducts.get(i));
                                                    }
                                                    break;
                                        }
                                    }while(userChoice!=9);
                                }
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