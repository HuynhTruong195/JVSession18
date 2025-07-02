package service;

import Dao.ProductDao;
import entity.Products;
import entity.StatictisByCatalog;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProductService {
    static Scanner scanner = new Scanner(System.in);

    //lấy danh sách
    public static void getAllProducts() {
        List<Products> listProducts = ProductDao.getAllProducts();
        if (listProducts == null) {
            System.err.println("Có lỗi xãy ra  trong quá trình xử lý... Vui lòng thử lại");
        } else {
            System.out.println("Danh Sách Products");
            listProducts.forEach(System.out::println);
        }

    }

    // kiểm tra sự tồn tại tên sản phẩm
    public static int isProductNameExist(Scanner scanner) {

        System.out.println("Nhập tên sản phẩm cần kiểm tra");
        String productName = scanner.nextLine();
        int check = ProductDao.isProductNameExist(productName);

        if (check != 0) {
            System.out.printf("Sản phẩm %s có tồn tại %n", productName);
            return check;
        } else {
            System.out.println("Sản phẩm không tồn tại, vui lòng thử lại");

        }
        return check;
    }

    //addproduct
    public static void addProduct(Scanner scanner) {
        Products products = new Products();
        System.out.println("Nhập tên sản phẩm mới");
        products.setProductName(scanner.nextLine());
        System.out.println("Nhập giá sản phẩm");
        products.setProductPrice(Float.parseFloat(scanner.nextLine()));
        System.out.println("Nhập tiêu đề");
        products.setProduct_Title(scanner.nextLine());
        System.out.println("Nhập ngày tạo");
        products.setProdcutCreated(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println("Nhập danh mục");
        products.setProductCatalog(scanner.nextLine());

        Boolean resutl = ProductDao.addProducts(products);
        if (resutl) {
            System.out.println("Thêm sản phẩm thành công");
        } else {
            System.out.println("Có lỗi trong quá trình thêm sản phẩm");
        }

    }

    //update product
    public static void updateProduct(Scanner scanner) {
        Products products = new Products();
        System.out.println("Nhập vào ID product cần update");
        int id = Integer.parseInt(scanner.nextLine());
        Boolean product = ProductDao.getProductById(id);
        if (product) {
            System.out.println("ID không tồn tại vui lòng kiểm tra lại");

        } else {
            products.setProductId(id);
            System.out.println("Nhập tên mới:");
            products.setProductName(scanner.nextLine());
            System.out.println("Nhập giá mới:");
            products.setProductPrice(Float.parseFloat(scanner.nextLine()));
            System.out.println("Nhập tiêu đề mới");
            products.setProduct_Title(scanner.nextLine());
            System.out.println("Nhập ngày tạo ");
            products.setProdcutCreated(LocalDate.parse(scanner.nextLine()));
            System.out.println("Nhập tên danh mục mới");
            products.setProductCatalog(scanner.nextLine());
            System.out.println("Nhập trạng thái mới (true/false)");
            products.setProductStatus(Boolean.parseBoolean(scanner.nextLine()));

            Boolean result = ProductDao.updateProducts(products);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Có lỗi trong quá trình cập nhật");
            }
        }
    }

    // delete product
    public static void deleteProduct(Scanner scanner) {
        System.out.println("Nhập ID product cần xoá");
        int id = Integer.parseInt(scanner.nextLine());
        boolean product = ProductDao.getProductById(id);
        if (product) {
            Boolean result = ProductDao.deleteProductById(id);
            if (result) {
                System.out.println("Xoá thành công Product có ID là " + id);
            } else {
                System.err.println("Có lỗi trong quá trình xoá");
            }
        } else {
            System.err.println("ID không tồn tại vui lòng kiểm tra lại");
        }
    }


    //findProductByName
    public static void searchProduct(Scanner scanner) {
        System.out.println("Nhập tên sản phẩm cần tìm");
        List<Products> products = ProductDao.searchProductByName(scanner.nextLine());
        if (!products.isEmpty()) {
            products.forEach(System.out::println);
        } else {
            System.out.println("Sản phẩm không tồn tại");
        }

    }

    public static void sortProductByPrice() {
        List<Products> products = ProductDao.getAllProducts();
        products.stream().sorted(Comparator.comparing(Products::getProductPrice)).forEach(System.out::println);
    }

    //thống kê soos lượng theo danh mục

    public static void statictisProDuct(){
        List<StatictisByCatalog> listStatictis = ProductDao.statictisByCatalog();
        listStatictis.forEach(System.out::println);
    }



}

