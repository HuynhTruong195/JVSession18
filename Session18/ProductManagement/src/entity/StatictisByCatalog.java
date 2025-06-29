package entity;

public class StatictisByCatalog {
    private String productCatalog;
    private int cnt;

    public StatictisByCatalog() {
    }

    public StatictisByCatalog(String productCatalog, int cnt) {
        this.productCatalog = productCatalog;
        this.cnt = cnt;
    }

    public String getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(String productCatalog) {
        this.productCatalog = productCatalog;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return String.format("Danh mục: %s | Số Luợng: %s", this.productCatalog, this.cnt);
    }
}
