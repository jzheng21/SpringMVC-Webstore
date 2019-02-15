package com.packt.webstore.controller; 
 
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
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
	
	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable(pathVar="params") Map<String, List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
}