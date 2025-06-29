import service.ProductService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***********PRODUCT MANAGEMENT***********");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ProductService.getAllProducts();
                    break;
                case 2:
                    ProductService.addProduct(scanner);
                    break;
                case 3:
                    ProductService.updateProduct(scanner);
                    break;
                case 4:
                    ProductService.deleteProduct(scanner);
                    break;
                case 5:
                    ProductService.searchProduct(scanner);
                    break;
                case 6:
                    ProductService.sortProductByPrice();
                    break;
                case 7:
                    ProductService.statictisProDuct();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-8");
            }
        } while (true);
    }
}