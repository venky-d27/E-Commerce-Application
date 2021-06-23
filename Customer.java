import java.util.HashMap;

public class Customer extends User 
{
    void createCustomer(String userName,String userPassword)
    {
        setUserID();
        setUserName(userName);
        setUserPassword(userPassword);
    }
}