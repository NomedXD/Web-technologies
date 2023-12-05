package by.bsuir.project.services.impl;

import by.bsuir.project.domain.Cart;
import by.bsuir.project.domain.Product;
import by.bsuir.project.domain.Search;
import by.bsuir.project.enums.EshopConstants;
import by.bsuir.project.enums.PagesPathEnum;
import by.bsuir.project.enums.RequestParamsEnum;
import by.bsuir.project.repositories.ProductRepository;
import by.bsuir.project.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product entity) {
        return productRepository.create(entity);
    }

    @Override
    public List<Product> read() {
        return productRepository.read();
    }

    @Override
    public Product update(Product entity) {
        return productRepository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> getCategoryProducts(int categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Long getCountOfAllProducts() {
        return productRepository.countAll();
    }

    @Override
    public Long getCountAppropriateProducts(Search search) {
        return productRepository.countAllBySearch(search);
    }

    @Override
    public ModelAndView getSearchedPaginatedProducts(Search search, Integer currentPage, Integer pageSize) {
        if (Optional.ofNullable(currentPage).isEmpty() || Optional.ofNullable(pageSize).isEmpty()) {
            currentPage = 1;
            pageSize = EshopConstants.MIN_PAGE_SIZE;
        }
        ModelMap model = new ModelMap();
        Long count;
        List<Product> productList;
        if ((search == null) || (search.getSearchString() == null)) {
            count = getCountOfAllProducts();
            productList = productRepository.findAllInOrderByName(currentPage, pageSize);
        } else {
            count = getCountAppropriateProducts(search);
            productList = productRepository.findAllSearchedInOrderByName(search, currentPage, pageSize);
            model.addAttribute(EshopConstants.SEARCH_ENTITY, search);
        }
        model.addAttribute(RequestParamsEnum.TOTAL_SEARCH_RESULTS.getValue(), count);
        model.addAttribute(RequestParamsEnum.PAGE_SIZE.getValue(), pageSize);
        model.addAttribute(RequestParamsEnum.CURRENT_PAGE.getValue(), currentPage);
        model.addAttribute(RequestParamsEnum.TOTAL_PAGINATED_VISIBLE_PAGES.getValue(), EshopConstants.TOTAL_PAGINATED_VISIBLE_PAGES);
        model.addAttribute(RequestParamsEnum.LAST_PAGE_NUMBER.getValue(), Math.ceil(count / pageSize.doubleValue()));
        model.addAttribute(RequestParamsEnum.PRODUCTS.getValue(), productList);
        return new ModelAndView(PagesPathEnum.SEARCH_PAGE.getPath(), model);
    }

    @Override
    public ModelAndView getPaginatedProductsByCategoryId(Integer categoryId, Integer currentPage, Integer pageSize) {
        if (Optional.ofNullable(currentPage).isEmpty() || Optional.ofNullable(pageSize).isEmpty()) {
            currentPage = 1;
            pageSize = EshopConstants.MIN_PAGE_SIZE;
        }
        ModelMap model = new ModelMap();
        model.addAttribute(RequestParamsEnum.CURRENT_PAGE.getValue(), currentPage);
        model.addAttribute(RequestParamsEnum.PAGE_SIZE.getValue(), pageSize);
        model.addAttribute(RequestParamsEnum.TOTAL_PAGINATED_VISIBLE_PAGES.getValue(), EshopConstants.TOTAL_PAGINATED_VISIBLE_PAGES);
        model.addAttribute(RequestParamsEnum.LAST_PAGE_NUMBER.getValue(), Math.ceil(productRepository.countAllByCategoryId(categoryId) / pageSize.doubleValue()));
        model.addAttribute(RequestParamsEnum.PRODUCTS.getValue(), productRepository.findAllByCategoryIdOrderByName(categoryId, currentPage, pageSize));
        return new ModelAndView(PagesPathEnum.CATEGORY_PAGE.getPath(), model);
    }

    /*
          Внедрение конкретно HttpServletRequest, так как заранее неизвестно, сколько будет параметров в post запросе
         */
    @Override
    public ModelAndView applyProductsQuantity(Cart cart, HttpServletRequest request) {
        for (Product product : cart.getCartProductsInList()) {
            String quantity = request.getParameter(product.getId() + "quantity");
            if (quantity != null) {
                Integer currentQuantity = cart.getProductQuantities().get(product.getId());
                cart.setTotalPrice(cart.getTotalPrice() + product.getPrice() * (Integer.parseInt(quantity) - currentQuantity));
                cart.getProductQuantities().replace(product.getId(), Integer.parseInt(quantity));
            }
        }
        Cart.removeZeroQuantityProducts(cart);
        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath());
    }
}