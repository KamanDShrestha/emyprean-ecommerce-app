
package resources;

import java.io.File;

public class myConstants {
    public static final String Driver_Name = "com.mysql.jdbc.Driver";
    public static final String Db_Url = "jdbc:mysql://localhost:3306/empyrean";
    public static final String Db_User = "root";
    public static final String Db_Pass = "";
    public static final String Check_Email = "Select user_email from users Where user_email = ?";
    public static final String Get_Password_By_Email = "Select user_password from users Where user_email = ?";
    public static final String Get_User_ID_By_Email = "Select * from users WHERE user_email = ?";
    public static final String Get_All_Products_By_ID = "Select * from products WHERE product_id = ?";
    public static final String Get_All_Cart_Products_By_Cart_And_Product_ID = "Select * from carts_products WHERE cart_id = ? AND product_id = ?";
    public static final String Get_Cart_ID_By_User_ID = "Select cart_id FROM carts WHERE user_id = ?";
    public static final String Get_User_Role_By_Email = "Select user_role from users WHERE user_email = ?";

    public static final String Get_All_Cart_By_User_ID = "Select * from carts WHERE user_id = ?";
    public static final String Insert_Into_Cart = "INSERT INTO carts(user_id, cart_creationDate) VALUES(?,?)";
    public static final String Insert_Into_Cart_Products = "INSERT INTO carts_products(cart_id, product_id) VALUES(?,?)";

    public static final String Inset_Into_User = "INSERT INTO users(user_FName,user_LName, user_email, user_password, user_contact, user_address, cc_number, user_birthDate,image_link) VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String Insert_Into_Products ="Insert INTO products(product_id,product_name,product_brand,product_category,product_price,image_link, product_quantity) VALUES(?,?,?,?,?,?,?)";
    public static final String Delete_From_Products ="Delete FROM products WHERE product_id = ?";
    public static final String Delete_From_Cart_Products = "Delete FROM carts_products WHERE cart_id = ?";
    public static final String Delete_From_Cart ="Delete FROM carts WHERE user_id = ?";
    
    public static final String Delete_From_Cart_Product_By_Product_ID ="Delete FROM carts_products WHERE cart_id = ? AND product_id=?";
    public static final String Insert_Into_Queries = "INSERT INTO queries(user_id, query_desc) VALUES(?,?)";
    public static final String Insert_Into_Orders = "INSERT INTO orders(user_id, order_date, total_cost) VALUES(?,?,?)";

    
    public static final String Update_From_Products ="Update Products SET product_name = ?, product_brand = ?, product_category = ?, product_price = ?, image_link = ?, product_quantity = ? WHERE product_id = ?";
    public static final String Update_From_Products_No_Url ="Update Products SET product_name = ?, product_brand = ?, product_category = ?, product_price = ?, product_quantity = ? WHERE product_id = ?";

    public static final String IMAGE_DIR = "xampp\\tomcat\\webapps\\images\\";
    public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR;
    public static final String PRODUCT_IMAGE_DIR = "xampp\\tomcat\\webapps\\images\\products\\";
    public static final String PRODUCTS_IMAGE_DIR_SAVE_PATH = "C:" + File.separator + PRODUCT_IMAGE_DIR;
    
    public static final String Get_All_Products = "Select * from products";
    public static final String Insert_Into_Customer = "INSERT INTO customers(customerFname,customerLname, customer_email, customer_contact, delivery_address, cc_number, customer_birthDate,image_link) VALUES(?,?,?,?,?,?,?,? )";
    
    public static final String UPDATE_CUSTOMER_DETAILS = "UPDATE users SET user_FName = ?, user_LName = ?, user_address = ?, user_email = ?, user_contact = ?, user_birthDate = ?, user_password = ? WHERE user_email = ?";
    public static final String UPDATE_CUSTOMER_DETAILS_URL = "UPDATE users SET user_FName = ?, user_LName = ?, user_address = ?, user_email = ?, user_contact = ?, user_birthDate = ?, user_password = ?, image_link = ?	 WHERE user_email = ?";

    
    public static final String SEARCH_PRODUCT = "SELECT * FROM products WHERE product_name LIKE ?";
    
    public static final String FILTER_BY_CATEGORY = "SELECT * FROM products WHERE product_category = ?";
    public static final String FILTER_BY_CATEGORY_PRICE = "SELECT * FROM products WHERE product_category = ? AND product_price >= ?";
    public static final String FILTER_BY_CATEGORY_BRAND = "SELECT * FROM products WHERE product_category = ? AND product_brand = ?";
    public static final String FILTER_BY_BRAND = "SELECT * FROM  products WHERE product_brand = ?";
    public static final String FILTER_BY_PRICE = "SELECT * FROM products WHERE product_price >= ?";
    public static final String FILTER_BY_PRICE_BRAND = "SELECT * FROM products WHERE product_price >= ? AND product_brand = ?";

    public static final String FILTER_BY_CATEGORY_PRICE_BRAND = "SELECT * FROM products WHERE product_category = ? AND product_price >= ? AND product_brand = ?";
   
    
    
    
    
    public static final String SORT_BY_PRICE_ASC = "SELECT * FROM products ORDER BY product_price";
    public static final String SORT_BY_PRICE_DESC = "SELECT * FROM products ORDER BY product_price DESC";
    
    public static final String SORT_BY_RATINGS_ASC = "SELECT * FROM products ORDER BY product_ratings";
    public static final String SORT_BY_RATINGS_DESC = "SELECT * FROM products ORDER BY product_ratings DESC";
    

    
}
