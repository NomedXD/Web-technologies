package by.bsuir.enums;

public interface EshopConstants {
    String SHOPPING_CART = "cart";
    String USER = "user";
    String SEARCH_ENTITY = "searchEntity";
    String ORDER = "order";
    String ORDERS = "orders";
    Integer MIN_PAGE_SIZE = 5;
    Integer TOTAL_PAGINATED_VISIBLE_PAGES = 5;
    String successfulImportMessage = "Successful imported";
    String errorOrdersExportMessage = "Cannot export orders to file";
    String errorOrdersImportMessage = "Cannot import orders from file";
    String errorFileNullMessage = "Cannot import orders because file is empty";
    String errorProductsExportMessage = "Cannot export products to file";
    String errorProductsImportMessage = "Cannot import products from file";
    String errorCategoriesExportMessage = "Cannot export categories to file";
    String errorCategoriesImportMessage = "Cannot import categories from file";
    String errorDiscountCodeMessage = "Discount code was not found";
}
