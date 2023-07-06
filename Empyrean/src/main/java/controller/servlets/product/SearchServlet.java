package controller.servlets.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dbconnection.DBConnection;
import model.Product;
import resources.myConstants;

/**
 * Servlet implementation class Product
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/searchServlet" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	
	//Product object -- by integrating Product class
	
	
	 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		context = getServletContext();
		String search = request.getParameter("searchText");		
		this.context.log(search);
		ArrayList<Product> productArray = new ArrayList<Product>();
		
		DBConnection conn = new DBConnection();
		ResultSet searchResult  = conn.searchProduct(myConstants.SEARCH_PRODUCT, search);
		if(searchResult == null) {
			this.context.log("hellos");
		}
		
		try {
			while(searchResult.next()) {
				this.context.log("This is hsere");
				Product product = new Product();
				product.setProductID(searchResult.getString("product_id"));
				product.setProductName(searchResult.getString("product_name"));
				product.setProductQuantity(searchResult.getString("product_quantity"));
				product.setProductBrand(searchResult.getString("product_brand"));
				product.setProductPrice(searchResult.getString("product_price"));
				product.setProductCategory(searchResult.getString("product_category"));
				product.setImageLink(searchResult.getString("image_link"));
				product.setProductRatings(searchResult.getFloat("product_rating"));
				productArray.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.context.log(String.valueOf(searchResult));
		request.setAttribute("searchedProducts", productArray);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/searchResults.jsp");
		dispatcher.forward(request, response);
		
	}
	


}


