package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping("/customers")
	public String list(Model model) {
		model.addAttribute("customers", service.getAllCustomers());
		return "customers";
	}
}
