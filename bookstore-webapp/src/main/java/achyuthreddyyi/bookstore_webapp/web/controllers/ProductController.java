package achyuthreddyyi.bookstore_webapp.web.controllers;

import achyuthreddyyi.bookstore_webapp.clients.catalog.CatalogServiceClient;
import achyuthreddyyi.bookstore_webapp.clients.catalog.PagedResult;
import achyuthreddyyi.bookstore_webapp.clients.catalog.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
    private final CatalogServiceClient catalogServiceClient;

    public ProductController(CatalogServiceClient catalogServiceClient) {
        this.catalogServiceClient = catalogServiceClient;
    }

    @GetMapping
    String index() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    String productsPage(@RequestParam(name = "page", defaultValue = "1") int pageNo, Model model) {
        model.addAttribute("pageNo", pageNo);
        return "products";
    }

    @GetMapping("/api/products")
    @ResponseBody
    PagedResult<Product> products(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return catalogServiceClient.getProducts(pageNo);
    }
}
