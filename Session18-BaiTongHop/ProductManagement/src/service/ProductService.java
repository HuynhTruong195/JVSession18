package service;

import Dao.ProductDao;
import entity.Products;
import entity.StatictisByCatalog;

import java.sql.ResultSet;
import java.sql.SQLOutput;
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
    public static int isProductNameExist2(Scanner scanner) {

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
        System.out.println("Nhập vào ID product cần update");
        int id = Integer.parseInt(scanner.nextLine());
        Products product = ProductDao.getProductById(id);
        if (product == null) {
            System.out.println("ID không tồn tại vui lòng kiểm tra lại");
            return;
        } else {
            Boolean isExit = false;
            do {
                System.out.println("***** Cập Nhật *****");
                System.out.println("1. Cập nhật tên");
                System.out.println("2. Cập nhật giá");
                System.out.println("3. Cập nhật tiêu đề");
                System.out.println("4. Cập nhật ngày tạo");
                System.out.println("5. Cập nhật danh mục mới ");
                System.out.println("6. Cập nhật trạng thái");
                System.out.println("7. Thoát");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        do {
                            System.out.println("Nhập tên mới");
                            String name = scanner.nextLine();
                            if (Validate.validateName(name)) {
                                product.setProductName(name);
                                break;
                            } else {
                                System.err.println("Tên không được để trống, không trùng lặp, độ dài không vượt quá 100 ký tự, vui lòng nhập lại");
                            }
                        } while (true);
                        break;
                    case 2:
                        do {
                            System.out.println("Nhập giá mới:");
                            String price = scanner.nextLine();
                            if (Validate.validatePrice(price)) {
                                product.setProductPrice(Float.valueOf(price));
                                break;
                            } else {
                                System.out.println("Giá phải lớn hơn 0, là số nguyên và không được trống");
                            }
                        } while (true);
                        break;
                    case 3:
                        System.out.println("Nhập tiêu đề mới");
                        String title = scanner.nextLine();
                        if (Validate.validateTitle(title)) {
                            product.setProduct_Title(title);
                            break;
                        }else {
                            System.err.println("Tiêu đề không được trống, tối đa 200 ký tự");
                        }
                        break;
                    case 4:
                        System.out.println("Nhập ngày tạo ");
                        product.setProdcutCreated(LocalDate.parse(scanner.nextLine()));
                        break;
                    case 5:
                        System.out.println("Nhập tên danh mục mới");
                        product.setProductCatalog(scanner.nextLine());
                        break;
                    case 6:
                        System.out.println("Nhập trạng thái mới (true/false)");
                        product.setProductStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 7:
                        isExit = true;
                        break;
                    default:
                        System.out.println("Vui lòng chọn từ 1-7");
                }
            } while (isExit);
        }

        Boolean productUpdate = ProductDao.updateProducts(product);
        if (productUpdate) {
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Có lỗi trong quá tình cập nhật");
        }
    }

    // delete product
    public static void deleteProduct(Scanner scanner) {
        System.out.println("Nhập ID product cần xoá");
        int id = Integer.parseInt(scanner.nextLine());
        Products product = ProductDao.getProductById(id);
        if (product != null) {
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

    public static void statictisProDuct() {
        List<StatictisByCatalog> listStatictis = ProductDao.statictisByCatalog();
        listStatictis.forEach(System.out::println);
    }


}

