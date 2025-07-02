package Dao;

import entity.Products;
import entity.StatictisByCatalog;
import ultil.ConnectionDb;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    //lấy all products
    public static List<Products> getAllProducts() {
        List<Products> listProducts = null;
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDb.getConnection();
            callStmt = conn.prepareCall("{call get_all_products()}");
            ResultSet rs = callStmt.executeQuery();
            listProducts = new ArrayList<>();
            while (rs.next()) {
                Products product = new Products();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getFloat("product_price"));
                product.setProduct_Title(rs.getString("product_title"));
                product.setProdcutCreated(LocalDate.parse(rs.getString("product_created"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                product.setProductCatalog(rs.getString("product_catalog"));
                product.setProductStatus(rs.getBoolean("product_status"));
                listProducts.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callStmt);
        }
        return listProducts;
    }

    // check tồn tại tên product
    public static int isProductNameExist(String productName) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDb.getConnection();
            callStmt = conn.prepareCall("{call check_name_isExist(?,?)}");
            callStmt.setString(1, productName);
            callStmt.registerOutParameter(2, Types.INTEGER);
            callStmt.execute();

            return callStmt.getInt(2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callStmt);
        }
        return -1;
    }


    //thêm mới
    public static Boolean addProducts(Products products) {

        Connection conn = null;
        CallableStatement callStmt = null;
        boolean result = false;
        try {
            conn = ConnectionDb.getConnection();
            callStmt = conn.prepareCall("{call add_product(?,?,?,?,?)}");
            callStmt.setString(1, products.getProductName());
            callStmt.setFloat(2, products.getProductPrice());
            callStmt.setString(3, products.getProduct_Title());
            callStmt.setDate(4, Date.valueOf(products.getProdcutCreated()));
            callStmt.setString(5, products.getProductCatalog());
            callStmt.executeUpdate();

            result = true;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callStmt);
        }
        return result;
    }

    //update
    public static Boolean updateProducts(Products products) {
        Connection conn = null;
        CallableStatement callStmt = null;
        boolean result = false;
        try {
            conn = ConnectionDb.getConnection();
            callStmt = conn.prepareCall("{call update_product(?,?,?,?,?,?,?)}");
            callStmt.setInt(1, products.getProductId());
            callStmt.setString(2, products.getProductName());
            callStmt.setFloat(3, products.getProductPrice());
            callStmt.setString(4, products.getProduct_Title());
            callStmt.setDate(5, Date.valueOf(products.getProdcutCreated()));
            callStmt.setString(6, products.getProductCatalog());
            callStmt.setBoolean(7, products.getProductStatus());
            callStmt.executeUpdate();

            result = true;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callStmt);
        }
        return result;
    }

    //getproductbyID
    public static Products getProductById(int id) {
//        List<Products> productList =  null;
        Connection conn = null;
        CallableStatement callStmt = null;
        Products product = null;
        try {
            conn = ConnectionDb.getConnection();
            callStmt = conn.prepareCall("{call get_product_byid(?)}");
            callStmt.setInt(1, id);
            ResultSet rs = callStmt.executeQuery();
            while (rs.next()) {
                product = new Products();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getFloat("product_price"));
                product.setProduct_Title(rs.getString("product_title"));
                product.setProdcutCreated(rs.getDate("product_created").toLocalDate());
                product.setProductCatalog(rs.getString("product_catalog"));
                product.setProductStatus(rs.getBoolean("product_status"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callStmt);
        }
        return product;
    }

//    public static void main(String[] args) {
//        try {
//            System.out.println(getProductById(13));
//
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            System.out.println("lỗi null");
//        }
//    }


    //delete product
    public static Boolean deleteProductById(int id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        boolean result = false;
        try {
            conn = ConnectionDb.getConnection();
            callStmt = conn.prepareCall("{call delete_product(?)}");
            callStmt.setInt(1, id);
            callStmt.executeUpdate();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callStmt);
        }

        return result;
    }

    //findProductByName

    public static List<Products> searchProductByName(String productName) {
        List<Products> listProducts = null;
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDb.getConnection();
            callStmt = conn.prepareCall("{call search_product_byname(?)}");
            callStmt.setString(1, productName);
            ResultSet rs = callStmt.executeQuery();
            listProducts = new ArrayList<>();
            while (rs.next()) {
                Products product = new Products();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getFloat("product_price"));
                product.setProduct_Title(rs.getString("product_title"));
                product.setProdcutCreated(LocalDate.parse(rs.getString("product_created"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                product.setProductCatalog(rs.getString("product_catalog"));
                product.setProductStatus(rs.getBoolean("product_status"));
                listProducts.add(product);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callStmt);
        }
        return listProducts;
    }

    //thống kê theo giá tăng dần
    public static List<Products> getProductByPrice(Products products) {
        List<Products> listProducts = null;
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDb.getConnection();
            callStmt = conn.prepareCall("{call get_all_products()}");
            ResultSet rs = callStmt.executeQuery();
            listProducts = new ArrayList<>();
            while (rs.next()) {
                Products product = new Products();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductPrice(rs.getFloat("product_price"));
                product.setProduct_Title(rs.getString("product_title"));
                product.setProdcutCreated(LocalDate.parse(rs.getString("product_created"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                product.setProductCatalog(rs.getString("product_catalog"));
                product.setProductStatus(rs.getBoolean("product_status"));
                listProducts.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callStmt);
        }
        return listProducts;
    }

    //thống kê số lượkg theo danh mục

    public static List<StatictisByCatalog> statictisByCatalog() {

        Connection conn = null;
        CallableStatement callStmt = null;
        List<StatictisByCatalog> listStatictis = null;
        try {
            conn = ConnectionDb.getConnection();
            callStmt = conn.prepareCall("{call statictis_by_catalog()}");
            ResultSet rs = callStmt.executeQuery();
            listStatictis = new ArrayList<>();
            while (rs.next()) {
                StatictisByCatalog listStt = new StatictisByCatalog();
                listStt.setProductCatalog(rs.getString("product_catalog"));
                listStt.setCnt(Integer.parseInt(rs.getString("cnt")));
                listStatictis.add(listStt);


            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDb.closeConnection(conn, callStmt);
        }
        return listStatictis;
    }
}
