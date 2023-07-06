package model;

import java.io.File;
import javax.servlet.http.Part;
import resources.myConstants;

public class User {
    String firstName;
    String lastName;
    String email;
    String password;
    String userAdd;
    String role;
    String imageUrlFromPart;
    String userCCN;
    String userBirth;
    String userContact;
    

    public String getUserAdd() {
        return this.userAdd;
    }

    public void setUserAdd(String userAdd) {
        this.userAdd = userAdd;
    }

    public User() {
    }

    public User(String firstName, String lastName, String userAdd, String email,String password, Part part, String userCCN, String userBirth, String userContact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAdd = userAdd;
        this.email = email;
        this.password = password;
        this.imageUrlFromPart = this.getImageUrl(part);
        this.userCCN = userCCN;
        this.userBirth = userBirth;
        this.userContact = userContact;
    }

    private String getImageUrl(Part part) {
        String[] items;
        String savePath = myConstants.IMAGE_DIR_SAVE_PATH;
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

    public String getUserCCN() {
        return this.userCCN;
    }

    public void setUserCCN(String userCCN) {
        this.userCCN = userCCN;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getUserBirth() {
        return this.userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserContact() {
        return this.userContact;
    }
    
    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public void setImageUrlFromPart(String imageUrlFromPart) {
        this.imageUrlFromPart = imageUrlFromPart;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
 

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
    	return this.password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageUrlFromPart() {
        return this.imageUrlFromPart;
    }

    public void setImageUrlFromPart(Part part) {
        this.imageUrlFromPart = this.getImageUrl(part);
    }
}