package model;

import java.io.File;

import javax.servlet.http.Part;

import resources.myConstants;

public class Product {
	String productID;
	String productName;
	String productBrand;
	String productCategory;
	String productPrice;
    String productImageURL;
	String productQuantity;
	String image_link;
	float productRatings;
	
	public float getProductRatings() {
		return productRatings;
	}

	public void setProductRatings(float productRatings) {
		this.productRatings = productRatings;
	}

	public Product() {}
    
	public Product(String productID, String productName, String productBrand, String productCategory,String productPrice, Part part, String productQuantity) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productImageURL = this.getImageUrl(part);
		this.productQuantity = productQuantity;
	}

	private String getImageUrl(Part part) {
		// TODO Auto-generated method stub
		String[] items;
        String savePath = myConstants.PRODUCTS_IMAGE_DIR_SAVE_PATH;
        File fileSaveDir = new File(savePath);
        String imageUrlFromPart = null;
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String contentDisp = part.getHeader("content-disposition");
        String[] arrstring = items = contentDisp.split(";");
        int n = items.length;
        for (int i = 0; i < n; ++i) {
            String s = arrstring[i];
            if (!s.trim().startsWith("filename")) continue;
            imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
        }
        if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
            imageUrlFromPart = "img.jpg";
        }
        return imageUrlFromPart;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return this.productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductPrice() {
		return this.productPrice;
	}
	
	
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getImage_link() {
		return image_link;
	}
	
	public void setImageLink(String image_link) {
		this.image_link = image_link;
	
	}
	


	public String getProductImageURL() {
		return this.productImageURL;
	}

	public void setProductImageURL(Part part) {
        this.productImageURL = this.getImageUrl(part);
	}

	public String getProductQuantity() {
		return this.productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}
	
}
