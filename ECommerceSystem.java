import java.util.*;

public class ECommerceSystem
{
    public static Shop shop=new Shop();
    public static void main(String[] args) 
    { 

        Admin admin=new Admin();
        Customer customer=new Customer(); 

        Scanner sc=new Scanner(System.in);
        int userType;
        do
        {
            System.out.println("Enter type of User:\n1.Admin\n2.Customer\n3.Exit");
            userType=sc.nextInt();
            int userChoice,userStatus;
            if(userType==1)
            {
                do
                {
                    System.out.println("Enter the action to be performed:\n1.Add Category\n2.Add Product\n3.View Category\n4.View Product\n5.Generate Coupon\n6.View generated Coupons\n7.Modify Product\n8.Modify Category\n9.View Customers Orders\n10.Change Order Status\n11.Exit");
                    userChoice=sc.nextInt();
                    switch(userChoice)
                    {
                        case 1: 
                                System.out.println("Enter the Category Name: ");
                                String categoryName=sc.next();
                                admin.addCategory(categoryName);
                                System.out.println("Category Successfully Addded!!!");
                                break;
                        case 2:
                            try 
                            {    
                                System.out.println("Enter the Category ID: ");
                                String categoryID=sc.next();
                                if(admin.checkCategoryID(categoryID))
                                {
                                    System.out.println("Enter the Product Name: ");
                                    String productName=sc.next();
                                    System.out.println("Enter the Product Price: ");
                                    double productPrice=sc.nextDouble();
                                    ECommerceSystem.validateProductPrice(productPrice);
                                    System.out.println("Enter the Product Availability: ");
                                    int productAvailability=sc.nextInt();
                                    ECommerceSystem.validateProductAvailability(productAvailability);
                                    System.out.println("Enter the Product Description: ");
                                    String productDescription=sc.next();
                                    admin.addProduct(categoryID, productName, productPrice, productAvailability, productDescription);
                                    System.out.println("Product Successfully Added!!!");
                                }
                                else
                                {
                                    System.out.println("Wrong Category ID");
                                }
                            }
                            catch(ProductError error)
                            {
                                System.out.println(error.getMessage());
                            }
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
                                String categoryID=sc.next();
                                if(admin.checkCategoryID(categoryID))
                                {
                                    HashMap<String, Product> productList= admin.getAllProduct(categoryID);
                                    for(String i: productList.keySet())
                                    {
                                        System.out.println(i+" "+productList.get(i).getProductName());
                                    }
                                }
                                else
                                {
                                    System.out.println("Wrong Category ID");
                                }
                                break;
                        case 5:
                                System.out.println("Enter discount Percent: ");
                                double discountPercent=sc.nextDouble();
                                admin.generateCoupon(discountPercent);
                                break;
                        case 6: 
                                HashMap<String,Coupon> couponList=admin.getCouponList();
                                for(String i: couponList.keySet())
                                {
                                    System.out.println("Coupon Code: "+i+"\nDiscount Percent: "+couponList.get(i).getDiscountPercent());
                                }
                                break;
                        case 7:
                            try
                            {
                                System.out.println("Enter the Category ID: ");
                                categoryID=sc.next();
                                if(admin.checkCategoryID(categoryID))
                                {
                                    System.out.println("Enter the Product ID: ");
                                    String productID=sc.next();
                                    if(admin.checkProductID(categoryID, productID))
                                    {
                                        System.out.println("Enter the Product Name: ");
                                        String productName=sc.next();
                                        System.out.println("Enter the Product Price: ");
                                        double productPrice=sc.nextDouble();
                                        ECommerceSystem.validateProductPrice(productPrice);
                                        System.out.println("Enter the Product Availability: ");
                                        int productAvailability=sc.nextInt();
                                        ECommerceSystem.validateProductAvailability(productAvailability);
                                        System.out.println("Enter the Product Description: ");
                                        String productDescription=sc.next();
                                        admin.modifyProduct(categoryID, productID, productName, productPrice, productAvailability, productDescription);
                                        System.out.println("Modified Product Successfully!!!");
                                    }
                                    else
                                    {
                                        System.out.print("Wrong Product ID");
                                    }
                                }
                                else
                                {
                                    System.out.println("Wrong Category ID");
                                }
                            }
                            catch(ProductError error)
                            {
                                System.out.println(error.getMessage());
                            }
                                break;
                        case 8:
                                System.out.println("Enter the Category ID: ");
                                categoryID=sc.next();
                                if(admin.checkCategoryID(categoryID))
                                {
                                    System.out.println("Enter the Category Name: ");
                                    categoryName=sc.next();
                                    admin.modifyCategory(categoryID, categoryName);
                                    System.out.println("Modified Category Successfully!!!");
                                }
                                else
                                {
                                    System.out.println("Wrong Category ID");
                                }
                                break;
                        case 9:
                                HashMap<String,Order> customerOrders=admin.getCustomerOrders();
                                for(String orderID: customerOrders.keySet())
                                {
                                    Order order=customerOrders.get(orderID);
                                    System.out.println("Order ID: "+order.getOrderID()+"\nCustomer ID:"+order.getCustomer().getUserID()+"\nTotal Cost: "+order.getTotalCost());
                                    for(Product orderedproduct: order.getOrderedProducts().keySet())
                                    {
                                        System.out.println("Product ID: "+orderedproduct.getProductID()+"\nProduct Name: "+orderedproduct.getProductName()+"\nProduct Price: "+orderedproduct.getProductPrice()+"\nProduct Description: "+orderedproduct.getProductDescription()+"\nProduct Quantity: "+order.getOrderedProducts().get(orderedproduct));
                                    }
                                }
                                break;
                        case 10:
                                System.out.println("Enter the Order ID: ");
                                String orderId=sc.next();
                                if(admin.checkOrderID(orderId))
                                {
                                    System.out.println("Enter the Order Status:\n1.Ordered\n2.Shipped\n3.Delivered");
                                    int orderStatus=sc.nextInt();
                                    switch(orderStatus)
                                    {
                                        case 1: admin.changeOrderStatus(orderId,OrderStatus.ORDERED);
                                                break;

                                        case 2: admin.changeOrderStatus(orderId,OrderStatus.SHIPPED);
                                                break;
                                        
                                        case 3: admin.changeOrderStatus(orderId,OrderStatus.DELIVERED);
                                                break;
                                        default:System.out.print("Wrong Order Status");        
                                    }
                                }
                                else
                                {
                                    System.out.println("Wrong Order ID");
                                }
                                break;
                        case 11: break;          

                        default: System.out.println("Wrong Choice!!!");
                    }
                }while(userChoice!=11);
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
                            try
                            {
                     
                                System.out.println("Enter Username: ");
                                String customerName=sc.next();
                                System.out.println("Enter User Password: ");
                                String customerPassword=sc.next();
                                ECommerceSystem.validatePassword(customerPassword);
                                System.out.println("Enter User Mobile Number: ");
                                long customerMobileNumber=sc.nextLong();
                                ECommerceSystem.validateUserMobileNumber(customerMobileNumber);
                                customer=new Customer();
                                customer.createCustomer(customerName, customerPassword,customerMobileNumber);
                                admin.customerList.put(customer.getUserID(),customer);
                                System.out.println(customer.getUserID());
                            }
                            catch(CustomerError error)
                            {
                                System.out.println(error.getMessage());
                            }
                                break;

                        case 2:
                                System.out.println("Enter Your ID:  ");
                                String customerID=sc.next();
                                System.out.println("Enter Your Password: ");
                                String customerPassword=sc.next();
                                try
                                {
                                if(admin.customerSignIn(customerID,customerPassword))
                                {
                                    System.out.println("SignIn Successfull!!");
                                    do
                                    {
                                        System.out.println("Enter the Choice:\n1.Add Product to Cart\n2.View Category\n3.View Product\n4.View Product Details\n5.Remove Product from Cart\n6.Place Order\n7.View Previous Orders\n8.View Cart Products\n9.Modify Quantity in Cart\n10.Exit");
                                        userChoice=sc.nextInt();
                                        switch(userChoice)
                                        {
                                            case 1: 
                                                try{
                                                    System.out.println("Enter the Category ID: ");
                                                    String categoryID=sc.next();
                                                    if(admin.checkCategoryID(categoryID))
                                                    {
                                                        System.out.println("Enter the Product ID: ");
                                                        String productID=sc.next();
                                                        if(admin.checkProductID(categoryID, productID))
                                                        {
                                                            System.out.println("Enter the Product Quantity: ");
                                                            int productQuantity=sc.nextInt();
                                                            ECommerceSystem.validateProductAvailability(productQuantity);
                
                                                            if(admin.checkAvailability(categoryID, productID, productQuantity))
                                                            {
                                                                Category category=customer.getCategoryByID(categoryID);
                                                                Product product=customer.getProductByID(category,productID);
                                                                customer.addProductToCart(product, productQuantity);
                                                            }
                                                        }
                                                        else
                                                        {
                                                            System.out.print("Wrong Product ID");
                                                        }
                                                    }
                                                    else
                                                    {
                                                        System.out.println("Wrong Category ID");
                                                    }
                                                }
                                                catch(ProductError error)
                                                {
                                                    System.out.println(error.getMessage());
                                                }
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
                                                    String categoryID=sc.next();
                                                    if(admin.checkCategoryID(categoryID))
                                                    {
                                                        HashMap<String, Product> productList= customer.getAllProduct(categoryID);
                                                        for(String i: productList.keySet())
                                                        {
                                                            System.out.println(i+" "+productList.get(i).getProductName());
                                                        }
                                                    }
                                                    else
                                                    {
                                                        System.out.println("Wrong Category ID");
                                                    }
                                                    break;
                                            case 4:
                                                    System.out.println("Enter the Category ID: ");
                                                    categoryID=sc.next();
                                                    if(admin.checkCategoryID(categoryID))
                                                    {
                                                        System.out.println("Enter the Product ID: ");
                                                        String productID=sc.next();
                                                        Category category=customer.getCategoryByID(categoryID);
                                                        if(admin.checkProductID(categoryID, productID))
                                                        {
                                                            Product product=customer.getProductByID(category, productID);
                                                            System.out.println("Product Details:\nProduct ID: "+product.getProductID()+"\nProduct Name: "+product.getProductName()+"\nProduct Price: "+product.getProductPrice()+"\nProduct Description: "+product.getProductDescription());
                                                        }
                                                        else
                                                        {
                                                            System.out.print("Wrong Product ID");
                                                        }
                                                    }
                                                    else
                                                    {
                                                        System.out.println("Wrong Category ID");
                                                    }
                                                    break;
                                            case 5:
                                                    System.out.println("Enter the Category ID: ");
                                                    categoryID=sc.next();
                                                    if(admin.checkCategoryID(categoryID))
                                                    {
                                                        System.out.println("Enter the Product ID: ");
                                                        String productID=sc.next();
                                                        if(admin.checkProductID(categoryID, productID))
                                                        {
                                                            Category category=customer.getCategoryByID(categoryID);
                                                            Product product=customer.getProductByID(category, productID);
                                                            customer.removeProductFromCart(product);
                                                            System.out.println("Product Successfully Removed from Cart!!!");
                                                        }
                                                    }
                                            case 6:
                                                    try
                                                    {
                                                        Order order=customer.placeOrder(customer.shoppingcart,customer);
                                                        System.out.println("Wanna apply Coupon?\n1.Yes\n2.No");
                                                        int couponChoice=sc.nextInt();
                                                        if(couponChoice==1)
                                                        {
                                                            System.out.println("Enter the Coupon ID: ");
                                                            String couponID=sc.next();
                                                            customer.applyCoupon(couponID,order);
                                                        }
                                                        customer.ordersHistory.put(order.getOrderID(), order);
                                                        admin.customerOrders.put(order.getOrderID(),order);
                                                        System.out.println(order.getTotalCost());
                                                    }
                                                    catch(CartError error)
                                                    {
                                                        System.out.println(error.getMessage());
                                                    }
                                                    catch(CouponError error)
                                                    {
                                                        System.out.println(error.getMessage());
                                                    }
                                                    break;
                                            case 7:
                                                    HashMap<String,Order> ordersHistory=customer.getOrdersHistory();
                                                    for(String orderID: ordersHistory.keySet())
                                                    {
                                                        Order order=ordersHistory.get(orderID);
                                                        System.out.println("Order ID: "+orderID+"\nTotal Amount: "+order.getTotalCost()+"\nOrder Status: "+order.getOrderStatus()+"\nTimestamp: "+order.getOrderTimestamp());
                                                        HashMap<Product,Integer> orderedProducts= ordersHistory.get(orderID).getOrderedProducts();
                                                        for(Product orderedproduct: orderedProducts.keySet())
                                                        {
                                                            System.out.println("Product ID: "+orderedproduct.getProductID()+"\nProduct Name: "+orderedproduct.getProductName()+"\nProduct Price: "+orderedproduct.getProductPrice()+"\nProduct Description: "+orderedproduct.getProductDescription()+"\nProduct Quantity: "+orderedProducts.get(orderedproduct));
                                                        }
                                                    }
                                                    break;
                                            case 8:
                                                    HashMap<Product,Integer> cartProducts=customer.shoppingcart.getCartProducts();
                                                    if(cartProducts.size()>0)
                                                    {
                                                        for(Product i: cartProducts.keySet())
                                                        {
                                                            System.out.println("Product ID: "+i.getProductID()+"\nProduct Name: "+i.getProductName()+"\nProduct Price: "+i.getProductPrice()+"\nProduct Description: "+i.getProductDescription()+"\nProduct Quantity: "+cartProducts.get(i));
                                                        }
                                                    }
                                                    else
                                                    {
                                                       System.out.println("Your Cart is Empty!!!"); 
                                                    }
                                                    break;
                                            case 9: 
                                                System.out.println("Enter the Category ID: ");
                                                categoryID=sc.next();
                                                if(admin.checkCategoryID(categoryID))
                                                {
                                                    System.out.println("Enter the Product ID: ");
                                                    String productID=sc.next();
                                                    if(admin.checkProductID(categoryID, productID))
                                                    {
                                                        Category category=customer.getCategoryByID(categoryID);
                                                        Product product=customer.getProductByID(category, productID);
                                                        System.out.println("Enter new quantity: ");
                                                        int quantity=sc.nextInt();
                                                        customer.modifyProductQuantity(product, quantity);
                                                        System.out.println("Product Quantity Successfully Modified!!!");
                                                    }
                                                    else
                                                    {
                                                        System.out.print("Wrong Product ID");
                                                    }
                                                }
                                                else
                                                {
                                                    System.out.print("Wrong PCategory ID");
                                                }
                                                break;
                                            case 10:
                                                break;
                                            default: System.out.println("Wrong Choice!!!");
                                        }
                                    }while(userChoice!=10);
                                }
                                }
                                catch(CustomerError error)
                                {
                                    System.out.println(error.getMessage());
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

    public static void validateUserMobileNumber(long customerMobileNumber) throws CustomerError
    {
        if(customerMobileNumber<0)
        {
            throw new CustomerError("Mobile Number Cannot be negative");
        }
        if(Long.toString(customerMobileNumber).length()!=10)
        {
            throw new CustomerError("Length of the number must be 10");
        }
    }

    public static void validatePassword(String customerPassword) throws CustomerError
    {
        if(customerPassword==null)
        {
            throw new CustomerError("Password cannot be empty!!!");
        }
        
        if(customerPassword.length()<6)
        {
            throw new CustomerError("Password must be of length 6 or above!!!");
        }
    }

    public static void validateProductAvailability(int productAvailability) throws ProductError
    {
        if(productAvailability<0)
        {
            throw new ProductError("Value cannot be negative!!!");
        }
    }
    public static void validateProductPrice(double productPrice) throws ProductError
    {
        if(productPrice<0.0)
        {
            throw new ProductError("Price cannot be negative!!!");
        }
    }
}