package controller.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PasswordEncryption;
import model.Product;
import model.User;
import resources.myConstants;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/empyrean";
            String user = "root";
            String pass = "";
            Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    
    public String selectAllQueryByID(String query,String ID) {
        Connection dbConnection = DBConnection.getConnection();
        if (dbConnection != null) {
            try {
                PreparedStatement statement = dbConnection.prepareStatement(query);
                statement.setString(1, ID);
                ResultSet result = statement.executeQuery();
                if(result.next()) {
                    return result.getString(1);
                }
                else {
                	return null;
                }
            }
            catch (SQLException e) {
                return String.valueOf(e);
            }
        }
        return null;
    }
    
    public Boolean getUserRole(String Email) {
    	 Connection dbConnection = DBConnection.getConnection();
         if (dbConnection != null) {
             try {
                 PreparedStatement statement = dbConnection.prepareStatement(myConstants.Get_User_Role_By_Email);
                 statement.setString(1, Email);
                 ResultSet result = statement.executeQuery();
                 if(result.next()) {
                     if(result.getInt(1)==0) {
                    	 return true;
                     }
                 }
                 else {
                 	return false;
                 }
             }
             catch (SQLException e) {
                 return false;
             }
         }
         return false;
    }
    
    public ResultSet selectAllQuery(String query) {
        Connection dbConnection = DBConnection.getConnection();
        if (dbConnection != null) {
            try {
                PreparedStatement statement = dbConnection.prepareStatement(query);
                ResultSet result = statement.executeQuery();
                return result;
            }
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    
    public String getCartIDFromUser(String user_ID) {
        Connection dbConnection = DBConnection.getConnection();
        if (dbConnection != null) {
            try {
                PreparedStatement statement = dbConnection.prepareStatement(myConstants.Get_Cart_ID_By_User_ID);
                statement.setString(1, user_ID);
                ResultSet result = statement.executeQuery();
                if(result.next()) {
                	return result.getString(1);
                }
                else {
                	return null;
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    
    }
    
    public Boolean enterIntoQueries(String user_id, String query_desc) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement statement1 = con.prepareStatement(myConstants.Insert_Into_Queries);
            statement1.setString(1, user_id);
            statement1.setString(2, query_desc);
            int result = statement1.executeUpdate();
            if (result == 0) {
                return false;
            }
            if (result == 1) {
                return true;
            }
            return false;
        }
        catch (SQLException ex) {
            return null;
        }
    }
    
    public Boolean enterIntoOrders(String user_id, String total_cost) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement statement1 = con.prepareStatement(myConstants.Insert_Into_Orders);
            statement1.setString(1, user_id);
            statement1.setString(2, String.valueOf(java.time.LocalDate.now()));
            statement1.setString(3, total_cost);

            int result = statement1.executeUpdate();
            if (result == 0) {
                return false;
            }
            if (result == 1) {
                return true;
            }
            return false;
        }
        catch (SQLException ex) {
            return false;
        }
    }
    
    public Boolean enterIntoCart(String user_id) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement statement1 = con.prepareStatement(myConstants.Insert_Into_Cart);
            statement1.setString(1, user_id);
            statement1.setString(2, String.valueOf(java.time.LocalDate.now()));
            int result = statement1.executeUpdate();
            if (result == 0) {
                return false;
            }
            if (result == 1) {
                return true;
            }
            return false;
        }
        catch (SQLException ex) {
            return null;
        }
    }
    
    public Boolean isDuplicateCartProduct(String cart_id,String product_id) {
    	Connection con = DBConnection.getConnection();
    	try {PreparedStatement statement1 = con.prepareStatement(myConstants.Get_All_Cart_Products_By_Cart_And_Product_ID);
        statement1.setString(1, cart_id);
        statement1.setString(2, product_id);
        ResultSet result = statement1.executeQuery();
        if(result.next()) {
            return true; 	 	
        }
        else {
        	return false;
        }
    }
    catch (SQLException ex) {
        return null;
    }
    }

    
    public Boolean enterIntoCartProducts(String cart_id,String product_id) {
    	
    	Connection con = DBConnection.getConnection();
    	try {PreparedStatement statement = con.prepareStatement(myConstants.Insert_Into_Cart_Products);
        statement.setString(1, cart_id);
        statement.setString(2, product_id);
        int result = statement.executeUpdate();
        if (result == 0) {
            return false;
        }
        if (result == 1) {
            return true;
        }
        return false; 	 	
    }
    catch (SQLException ex) {
        return false;
    }
}
    
    public Boolean removeCart(String userId) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(myConstants.Delete_From_Cart);
    	        statement.setString(1, userId);

    			int result = statement.executeUpdate();
                if (result==0) {
                    return false;
                }
                else if(result ==1) {
                    return true;
                }
    		}
            
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    }
    
    public Boolean removeCartProducts(String cartID) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(myConstants.Delete_From_Cart_Products);
    	        statement.setString(1, cartID);

    			int result = statement.executeUpdate();
                if (result==0) {
                    return false;
                }
                else if(result ==1) {
                    return true;
                }
    		}
            
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    }
    
    public Boolean removeProductFromCart(String productID, String cartID) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(myConstants.Delete_From_Cart_Product_By_Product_ID);
    	        statement.setString(1, cartID);
    			statement.setString(2, productID);

    			int result = statement.executeUpdate();
                if (result==0) {
                    return false;
                }
                else if(result ==1) {
                    return true;
                }
    		}
            
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    }
    
    public ResultSet searchProduct(String query, String searchText) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setString(1, "%" +  searchText + "%");
    			ResultSet set = statement.executeQuery();
    			return set;
    		}catch (SQLException e) {
                return null;
            }
    	}
		return null;
    }
    
    
    
    public ResultSet groupByCategory(String query) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			ResultSet set = statement.executeQuery();
    			return set;
    		}catch (SQLException e) {
                return null;
            }
    	}
		return null;
    }
    
    public ResultSet filterByCategory(String query, String category) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setString(1, category);
    			ResultSet set = statement.executeQuery();
    			return set;
    		}catch (SQLException e) {
                return null;
            }
    	}
		return null;
    }
    
    public ResultSet filterByCategoryPrice(String query, String category, int price) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setString(1, category);
    			statement.setInt(2, price);
    			ResultSet set = statement.executeQuery();
    			return set;
    		}catch (SQLException e) {
                return null;
            }
    	}
		return null;
    }
    
    public ResultSet filterByCategoryBrand(String query, String category, String brand) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setString(1, category);
    			statement.setString(2, brand);
    			ResultSet set = statement.executeQuery();
    			return set;
    		}catch (SQLException e) {
                return null;
            }
    	}
		return null;
    }
    
    public ResultSet filterByCategoryPriceBrand(String query, String category, String brand, int price) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setString(1, category);
    			statement.setInt(2, price);
    			statement.setString(3, brand);
    			ResultSet set = statement.executeQuery();
    			return set;
    		}catch (SQLException e) {
                return null;
            }
    	}
		return null;
    }
    
    public ResultSet filterByBrand(String query, String brand) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setString(1, brand);
    			ResultSet set = statement.executeQuery();
    			return set;
    		}catch (SQLException e) {
                return null;
            }
    	}
		return null;
    }
    
    public ResultSet filterByPrice(String query, int price) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setInt(1, price);
    			ResultSet set = statement.executeQuery();
    			return set;
    		}catch (SQLException e) {
                return null;
            }
    	}
		return null;
    }
    
    public ResultSet filterByPriceBrand(String query, int price, String brand) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setInt(1, price);
    			statement.setString(2, brand);
    			ResultSet set = statement.executeQuery();
    			return set;
    		}catch (SQLException e) {
                return null;
            }
    	}
		return null;
    }
    
    
    public int updateCustomerDetailsNoUrl(String query, String fname, String lname, String address, String email, String contact, String date, String previousEmail,String password) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setString(1, fname);
    			statement.setString(2, lname);
    			statement.setString(3, address);
    			statement.setString(4, email);
    			statement.setString(5, contact);
    			statement.setString(6, date);
      			statement.setString(7, PasswordEncryption.encrypt(email, password));
    			statement.setString(8, previousEmail);

  
    			int set = statement.executeUpdate();
    			return set;
    		}catch (SQLException e) {
                return 0;
            }
    	}
		return 0;
    }
    
    public int updateCustomerDetails(String query, String fname, String lname, String address, String email, String contact, String date, String previousEmail,String password, String URL) {
    	Connection dbConnection = DBConnection.getConnection();
    	if(dbConnection != null) {
    		try {
    			PreparedStatement statement = dbConnection.prepareStatement(query);
    			statement.setString(1, fname);
    			statement.setString(2, lname);
    			statement.setString(3, address);
    			statement.setString(4, email);
    			statement.setString(5, contact);
    			statement.setString(6, date);
    			statement.setString(7, PasswordEncryption.encrypt(email, password));
    			statement.setString(8, URL);
    			statement.setString(9, previousEmail);


  
    			int set = statement.executeUpdate();
    			return set;
    		}catch (SQLException e) {
                return 0;
            }
    	}
		return 0;
    }
    
    public String updateProduct(Product product) {
    	Connection con = DBConnection.getConnection();
    	if(con!=null) {
    		try {
    			PreparedStatement statement = con.prepareStatement(myConstants.Update_From_Products);
    			statement.setString(1, product.getProductName());
    			statement.setString(2, product.getProductBrand());
    			statement.setString(3, product.getProductCategory());
    			statement.setString(4, product.getProductPrice());
    			statement.setString(5, product.getProductImageURL());
    			statement.setString(6, product.getProductQuantity());
    			statement.setString(7, product.getProductID());

    			int result = statement.executeUpdate();
                if (result==0) {
                    return "false";
                }
                else if(result ==1) {
                    return "true";
                }
    		}
            
            catch (SQLException e) {
                return String.valueOf(e);
            }
        }
        return "null";
    }
    
    public String updateProductNoUrl(Product product) {
    	Connection con = DBConnection.getConnection();
    	if(con!=null) {
    		try {
    			PreparedStatement statement = con.prepareStatement(myConstants.Update_From_Products_No_Url);
    			statement.setString(1, product.getProductName());
    			statement.setString(2, product.getProductBrand());
    			statement.setString(3, product.getProductCategory());
    			statement.setString(4, product.getProductPrice());
    			statement.setString(5, product.getProductQuantity());
    			statement.setString(6, product.getProductID());

    			int result = statement.executeUpdate();
                if (result==0) {
                    return "false";
                }
                else if(result ==1) {
                    return "true";
                }
    		}
            
            catch (SQLException e) {
                return String.valueOf(e);
            }
        }
        return "null";
    }
    
    
    public String deleteProduct(String productID) {
    	Connection con = DBConnection.getConnection();
    	if(con!=null) {
    		try {
    			PreparedStatement statement = con.prepareStatement(myConstants.Delete_From_Products);
    			statement.setString(1, productID);
    			int result = statement.executeUpdate();
                if (result==0) {
                    return "false";
                }
                else if(result ==1) {
                    return "true";
                }
    		}
            
            catch (SQLException e) {
                return String.valueOf(e);
            }
        }
        return null;
    }
    
    public String addProduct(Product product) {
    	Connection con = DBConnection.getConnection();
    	if(con!= null) {
    		try {
    			PreparedStatement statement = con.prepareStatement(myConstants.Insert_Into_Products);
    			statement.setString(1, product.getProductID());
    			statement.setString(2, product.getProductName());
    			statement.setString(3, product.getProductBrand());
    			statement.setString(4, product.getProductCategory());
    			statement.setString(5, product.getProductPrice());
    			statement.setString(6, product.getProductImageURL());
    			statement.setString(7, product.getProductQuantity());
    			 int result = statement.executeUpdate();
                 if (result == 0) {
                     return "false";
                 }
                 else if (result == 1) {
                     return "true";
                 }
             }
             catch (SQLException e) {
                 return String.valueOf(e);
             }
         }
         return "null";
    }
    
    

    public Boolean registerNewUser(User user) {
        Connection con = DBConnection.getConnection();
        if (con != null) {
            try {
                PreparedStatement statement = con.prepareStatement(myConstants.Inset_Into_User);
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getEmail());
                statement.setString(4, PasswordEncryption.encrypt(user.getEmail(), user.getPassword()));
                statement.setString(5, user.getUserContact());
                statement.setString(6, user.getUserAdd());
                statement.setString(7, user.getUserCCN());
                statement.setString(8, user.getUserBirth());
                statement.setString(9, user.getImageUrlFromPart());
                int result = statement.executeUpdate();
                if (result == 0) {
                    return false;
                }
                if (result == 1) {
                    return true;
                }
                return false;
            }
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    public Boolean isUserRegistered(String email) {
        Connection dbConnection = DBConnection.getConnection();
        if (dbConnection != null) {
            try {
                PreparedStatement statement = dbConnection.prepareStatement(myConstants.Check_Email);
                statement.setString(1, email);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    return false;
                }
                return true;
            }
            catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public Boolean checkPassword(String query, String username, String password) {
        Connection dbConnection = DBConnection.getConnection();
        if (dbConnection != null) {
            try {
                PreparedStatement statement = dbConnection.prepareStatement(query);
                statement.setString(1, username);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    String encryPass = result.getString("user_password");
                    String decryPass = PasswordEncryption.decrypt((String)encryPass, (String)username);
                    try {
                        if (decryPass.equals(password)) {
                            return true;
                        }
                        return false;
                    }
                    catch (NullPointerException ex) {
                        return null;
                    }
                }
                return null;
            }
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    }
}