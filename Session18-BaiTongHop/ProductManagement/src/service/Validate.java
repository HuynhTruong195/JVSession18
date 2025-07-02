package service;

import Dao.ProductDao;

public class Validate {

    public static Boolean validateName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            if (name.length() < 100) {
                int checkName = ProductDao.isProductNameExist(name);
                if (checkName == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean validatePrice(String price) {
        if (price != null && !price.isEmpty()) {
            if (price.length() > 0) {
                return true;
            }
        }
        return false;
    }

    public static Boolean validateTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            if (title.length() < 200) {

                return true;
            }
        }

        return false;
    }

}

