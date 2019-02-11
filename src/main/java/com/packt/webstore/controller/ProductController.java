package com.packt.webstore.controller; 
 
import java.math.BigDecimal; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}