package com.packt.webstore.controller; 
 
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
 
@RequestMapping("market")
@Controller 
public class ProductController {
	
	@Autowired
	private ProductService productService;
 
	@RequestMapping("/products") 
	public String list(Model model) {
	    model.addAttribute("products", productService.getAllProducts());	  
	    return "products"; 
	} 
	
	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();
		return "redirect:/market/products";
	}
	
	@RequestMapping("/products/{category}")
	public ModelAndView getProductByCategory(@PathVariable("category") String productCategory) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", productService.getProductsByCategory(productCategory));
		modelAndView.setViewName("products");
		return modelAndView;
	}
	
	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable(pathVar="params") Map<String, List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String prodcutId, Model model) {
		model.addAttribute("product", productService.getProductById(prodcutId));
		return "product";
	}
	
	@RequestMapping("products/{category}/{price}")
	public String getProductByCategoryPriceBrand(@PathVariable("category") String productCategory, 
			@MatrixVariable(pathVar="price") Map<String, String> priceRange, 
			@RequestParam("brand") String brand,
			Model model) {
		System.out.println("category = " + productCategory +
				"priceRange = " + priceRange.get("low") +
				" " + priceRange.get("high") +
				"brand = " + brand);
		model.addAttribute("products", productService.getProductsByCategoryPriceBrand(productCategory, priceRange, brand));
		return "products";
	}
}