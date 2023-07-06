package controller.servlets.product;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class SortByServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/sortByServlet" })
public class SortByServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		context = getServletContext();
		ArrayList<Product> products = new ArrayList<Product>();
		
		
		String descPriceClicked = request.getParameter("DescPrice");
		String  ascPriceClicked = request.getParameter("AscPrice");
		String ratingsClicked = request.getParameter("Ratings");
		
		if(ratingsClicked != null) {
			this.context.log("ratingsClicked");
			this.context.log("DescPriceClicked");
			DBConnection conn = new DBConnection();
			ResultSet set = conn.selectAllQuery(myConstants.SORT_BY_RATINGS_ASC);
			try {
				while(set.next()) {
					Product product = new Product();
					product.setProductID(set.getString("product_id"));
					product.setProductName(set.getString("product_name"));
					product.setProductQuantity(set.getString("product_quantity"));
					product.setProductBrand(set.getString("product_brand"));
					product.setProductPrice(set.getString("product_price"));
					product.setProductRatings(set.getFloat("product_ratings"));
					product.setProductCategory(set.getString("product_category"));
					product.setImageLink(set.getString("image_link"));
					products.add(product);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("sortBy", "Ascending Ratings");
			request.setAttribute("sortedProducts", products);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/sortedResults.jsp");
			dispatcher.forward(request, response);
		}else if(descPriceClicked != null) {
			this.context.log("DescPriceClicked");
			DBConnection conn = new DBConnection();
			ResultSet set = conn.selectAllQuery(myConstants.SORT_BY_PRICE_DESC);
			try {
				while(set.next()) {
					Product product = new Product();
					product.setProductID(set.getString("product_id"));
					product.setProductName(set.getString("product_name"));
					product.setProductQuantity(set.getString("product_quantity"));
					product.setProductBrand(set.getString("product_brand"));
					product.setProductPrice(set.getString("product_price"));
					product.setProductRatings(set.getFloat("product_ratings"));
					product.setProductCategory(set.getString("product_category"));
					product.setImageLink(set.getString("image_link"));
					products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("sortBy", "Descending Price");
			request.setAttribute("sortedProducts", products);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/sortedResults.jsp");
			dispatcher.forward(request, response);
			
		} else if(ascPriceClicked != null) {
			this.context.log("AscPriceClicked");
			DBConnection conn = new DBConnection();
			ResultSet set = conn.selectAllQuery(myConstants.SORT_BY_PRICE_ASC);
			try {
				while(set.next()) {
					Product product = new Product();
					product.setProductID(set.getString("product_id"));
					product.setProductName(set.getString("product_name"));
					product.setProductQuantity(set.getString("product_quantity"));
					product.setProductBrand(set.getString("product_brand"));
					product.setProductPrice(set.getString("product_price"));
					product.setProductRatings(set.getFloat("product_ratings"));
					product.setProductCategory(set.getString("product_category"));
					product.setImageLink(set.getString("image_link"));
					products.add(product);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				this.context.log(String.valueOf(set.getFloat("product_ratings")));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.context.log(String.valueOf(products));
			request.setAttribute("sortBy", "Ascending Price");
			request.setAttribute("sortedProducts", products);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/sortedResults.jsp");
			dispatcher.forward(request, response);
		} else {
			this.context.log("Other clicked");
		}
		
	}

}
