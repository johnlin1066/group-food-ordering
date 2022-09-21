package com.example.group_food_ordering.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.group_food_ordering_core.menu.dao.MenuRepository;
import com.example.group_food_ordering_core.menu.dto.MenuDS;
import com.example.group_food_ordering_core.menu.dto.MenuItemDS;

@SpringBootTest
public class MenuRepositoryImpTest {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Test
	@Disabled
	public void createMenu() {
		List<MenuItemDS> menuItemDSList = new ArrayList<>();
		MenuItemDS menuItemDS = new MenuItemDS(null, "milk", 10);
		menuItemDSList.add(menuItemDS);
		MenuDS menuDS = new MenuDS(null, "milk store", "0912345678", menuItemDSList);
		
		Long menuID = menuRepository.createMenu(menuDS);
		
		assertEquals(true, (menuID>0));
	}
	
	@Test
	@Disabled
	public void readMenu() {
		Long menuID = 1L;
		
		MenuDS menuDS = menuRepository.readMenu(menuID);
		
		assertEquals(true, (menuDS!=null));
	}
	
	@Test
	@Disabled
	public void readAllMenu() {
		
		List<MenuDS> menuDSList = menuRepository.readAllMenu();
		
		assertEquals(true, (menuDSList!=null));
	}

}
