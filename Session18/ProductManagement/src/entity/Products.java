package entity;

import java.sql.Date;
import java.time.LocalDate;

public class Products {
    private int productId;
    private String productName;
    private Float productPrice;
    private String product_Title;
    private LocalDate prodcutCreated;
    private String productCatalog;
    private Boolean productStatus;

    public Products() {
    }

    public Products(int productId, String productName, Float productPrice, String product_Title, LocalDate prodcutCreated, String productCatalog, Boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.product_Title = product_Title;
        this.prodcutCreated = prodcutCreated;
        this.productCatalog = productCatalog;
        this.productStatus = productStatus;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProduct_Title() {
        return product_Title;
    }

    public void setProduct_Title(String product_Title) {
        this.product_Title = product_Title;
    }

    public LocalDate getProdcutCreated() {
        return prodcutCreated;
    }

    public void setProdcutCreated(LocalDate prodcutCreated) {
        this.prodcutCreated = prodcutCreated;
    }

    public String getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(String productCatalog) {
        this.productCatalog = productCatalog;
    }

    public Boolean getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Boolean productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return String.format("Mã sản phẩm: %s | Tên sản phẩm: %s | Giá sản phẩm: %.1f | Tiêu đề sản phẩm: %s | Ngày tạo: %s | Danh mục: %s | Trạng thái: %s",
                this.productId, this.productName, this.productPrice, this.product_Title, this.prodcutCreated, this.productCatalog, this.productStatus ? "Hoạt động" : "Không hoạt động");
    }
}
