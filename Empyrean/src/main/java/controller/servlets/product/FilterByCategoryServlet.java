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
 * Servlet implementation class FilterByCategoryServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/filterByCategoryServlet" })
public class FilterByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
       
    
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		context = getServletContext();
		ArrayList<Product> products = new ArrayList<Product>();
		DBConnection conn = new DBConnection();
		
		String category = request.getParameter("category");
		String brand = request.getParameter("brands");
		int price = Integer.parseInt(request.getParameter("priceSlider"));
		
		
		
		this.context.log(brand);
		this.context.log(category);
		this.context.log(String.valueOf(price));
		
		ResultSet filteredSet;
		if(brand == null && price == 0) {
			if(category.equals("all")) {
				filteredSet = conn.groupByCategory(myConstants.Get_All_Products);
			}else {
				filteredSet = conn.filterByCategory(myConstants.FILTER_BY_CATEGORY, category);
			}
			
			try {
				while(filteredSet.next()) {
					Product product = new Product();
					product.setProductID(filteredSet.getString("product_id"));
					product.setProductName(filteredSet.getString("product_name"));
					product.setProductQuantity(filteredSet.getString("product_quantity"));
					product.setProductBrand(filteredSet.getString("product_brand"));
					product.setProductPrice(filteredSet.getString("product_price"));
					product.setProductQuantity(filteredSet.getString("product_category"));
					product.setImageLink((filteredSet.getString("image_link")));
					
					products.add(product);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(price == 0 && brand != null) {
			if(category.equals("all")) {
				filteredSet = conn.filterByBrand(myConstants.FILTER_BY_BRAND, brand);
			}else {
				filteredSet = conn.filterByCategoryBrand(myConstants.FILTER_BY_CATEGORY_BRAND, category, brand);
			}
			
			try {
				while(filteredSet.next()) {
					Product product = new Product();
					product.setProductID(filteredSet.getString("product_id"));
					product.setProductName(filteredSet.getString("product_name"));
					product.setProductQuantity(filteredSet.getString("product_quantity"));
					product.setProductBrand(filteredSet.getString("product_brand"));
					product.setProductPrice(filteredSet.getString("product_price"));
					product.setProductQuantity(filteredSet.getString("product_category"));
					product.setImageLink((filteredSet.getString("image_link")));

					products.add(product);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}		
		}
		
		if(price != 0 && brand == null) {
			if(category.equals("all")) {
				filteredSet = conn.filterByPrice(myConstants.FILTER_BY_PRICE, price);
			}else {
				filteredSet = conn.filterByCategoryPrice(myConstants.FILTER_BY_CATEGORY_PRICE, category, price);
			}
			
			try {
				while(filteredSet.next()) {
					Product product = new Product();
					product.setProductID(filteredSet.getString("product_id"));
					product.setProductName(filteredSet.getString("product_name"));
					product.setProductQuantity(filteredSet.getString("product_quantity"));
					product.setProductBrand(filteredSet.getString("product_brand"));
					product.setProductPrice(filteredSet.getString("product_price"));
					product.setProductQuantity(filteredSet.getString("product_category"));
					product.setImageLink((filteredSet.getString("image_link")));

					products.add(product);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}		
		}
		
		if(price != 0 && brand != null) {
			if(category.equals("all")) {
				filteredSet = conn.filterByPriceBrand(myConstants.FILTER_BY_PRICE_BRAND, price, brand);
			}else {
				filteredSet = conn.filterByCategoryPriceBrand(myConstants.FILTER_BY_CATEGORY_PRICE_BRAND, category, brand, price);
			}
			
			try {
				while(filteredSet.next()){
					Product product = new Product();
					product.setProductID(filteredSet.getString("product_id"));
					product.setProductName(filteredSet.getString("product_name"));
					product.setProductQuantity(filteredSet.getString("product_quantity"));
					product.setProductBrand(filteredSet.getString("product_brand"));
					product.setProductPrice(filteredSet.getString("product_price"));
					product.setProductQuantity(filteredSet.getString("product_category"));
					product.setImageLink((filteredSet.getString("image_link")));

					products.add(product);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}		
		}
		
		request.setAttribute("category", category);
		request.setAttribute("brand", brand);
		request.setAttribute("price", price);
		this.context.log(String.valueOf(products));
		request.setAttribute("filteredProducts", products);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/filteredResults.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
