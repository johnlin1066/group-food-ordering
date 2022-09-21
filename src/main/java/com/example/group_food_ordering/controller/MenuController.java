package com.example.group_food_ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.group_food_ordering_core.menu.MenuInteractors;
import com.example.group_food_ordering_core.menu.dto.MenuDS;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	MenuInteractors menuInteractors;
	
	@PostMapping
	public Long createMenu(@RequestBody MenuDS menuDS) {
		return menuInteractors.createMenu(menuDS);
	}
	
	@GetMapping("/{menuID}")
	public MenuDS readMenu(@PathVariable("menuID") Long menuID) {
		return menuInteractors.readMenu(menuID);
	}
	
	@GetMapping
	public List<MenuDS> readAllMenu() {
		return menuInteractors.readAllMenu();
	}

}
