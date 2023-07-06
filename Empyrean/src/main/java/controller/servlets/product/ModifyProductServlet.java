package controller.servlets.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DBConnection;
import model.Product;
import resources.myConstants;

/**
 * Servlet implementation class ModifyProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ModifyProductServlet" })
@MultipartConfig(fileSizeThreshold=1024 * 1024 * 2, 
maxFileSize=1024 * 1024 * 10, 
maxRequestSize=1024 * 1024 * 10)

public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnection con = new DBConnection();
		PrintWriter out = response.getWriter();
		String productID = request.getParameter("productID");
		String productName = request.getParameter("productName");
		String brand = request.getParameter("productBrand");
		String category = request.getParameter("productCategory");
		String price = request.getParameter("productPrice");
		String quantity = request.getParameter("productQuantity");
		String buttonType = request.getParameter("submit");
		Part productImage = request.getPart("productImage");
		
		
		Product product = new Product(productID,productName,brand,category,price,productImage,quantity);
		
		if(buttonType.equals("Insert")) {
			out.println("insert");
			if(!(quantity.isEmpty()||productName.isEmpty()||brand.isEmpty()||category.isEmpty()||price.isEmpty()||price.isEmpty()||productImage.getSize()==0)) {
				String savePath = myConstants.PRODUCTS_IMAGE_DIR_SAVE_PATH;
		        String fileName = product.getProductImageURL();
		        if (!fileName.isEmpty() && fileName != null) {
		            productImage.write(savePath + fileName);
		        }
		        out.println(con.addProduct(product));
	            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/admin.jsp");				
			}
			else{
				request.setAttribute("errorMessage", "Fields cannot be empty");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin.jsp");
	            dispatcher.forward((ServletRequest)request, (ServletResponse)response);
			}
		}
		else if(buttonType.equals("Update")) {
			if(productImage.getSize()!=0) {
				if(!(quantity.isEmpty()||productName.isEmpty()||brand.isEmpty()||category.isEmpty()||price.isEmpty()||price.isEmpty())) {				
					String savePath = myConstants.PRODUCTS_IMAGE_DIR_SAVE_PATH;
			        String fileName = product.getProductImageURL();
			        if (!fileName.isEmpty() && fileName != null) {
			            productImage.write(savePath + fileName);
			        }
					out.println(con.updateProduct(product));
		            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/admin.jsp");				
					
				}
				else{
					request.setAttribute("errorMessage", "Fields cannot be empty");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin.jsp");
		            dispatcher.forward((ServletRequest)request, (ServletResponse)response);
				}
				
			}else if(productImage.getSize()==0) {
				if(!(quantity.isEmpty()||productName.isEmpty()||brand.isEmpty()||category.isEmpty()||price.isEmpty()||price.isEmpty())) {				
					
					out.println(con.updateProductNoUrl(product));
		            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/admin.jsp");				
					
				}
				else{
					request.setAttribute("errorMessage", "Fields cannot be empty");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/admin.jsp");
		            dispatcher.forward((ServletRequest)request, (ServletResponse)response);
				}
			}
			
			
		}
		else if(buttonType.equals("Delete")) {
			
			out.println("delete");
			con.deleteProduct(productID);
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/admin.jsp");				
		}
		
		
		


		
	}

}
