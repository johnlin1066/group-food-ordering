package com.example.group_food_ordering.data.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.group_food_ordering.data.dao.MenuDAO;
import com.example.group_food_ordering.data.entity.Menu;
import com.example.group_food_ordering.data.entity.MenuItem;
import com.example.group_food_ordering_core.menu.dao.MenuRepository;
import com.example.group_food_ordering_core.menu.dto.MenuDS;
import com.example.group_food_ordering_core.menu.dto.MenuItemDS;

public class MenuRepositoryImp implements MenuRepository{
	
	@Autowired
	MenuDAO menuDAO;

	@Override
	public Long createMenu(MenuDS menuDS) {
		Menu menu = convertToMenu(menuDS);
		for(MenuItemDS menuItemDS : menuDS.getMenuItemDSList()) {
			MenuItem menuItem = new MenuItem(menuItemDS.getName(), menuItemDS.getPrice());
			menu.getMenuItemSet().add(menuItem);
		}
		menuDAO.save(menu);
		return menu.getId();
	}

	@Override
	public MenuDS readMenu(Long menuID) {
		return convertToMenuDS(menuDAO.findById(menuID).orElse(null));
	}
	
	@Override
	public List<MenuDS> readAllMenu() {
		return convertToMenuDSList(menuDAO.findAll());
	}
	
	Menu convertToMenu(MenuDS menuDS) {
		return new Menu(menuDS.getStoreName(), menuDS.getStorePhone());
	}
	
	List<MenuDS> convertToMenuDSList(List<Menu> menuList) {
		List<MenuDS> menuDSList = new ArrayList<>();
		for(Menu menu : menuList)
			menuDSList.add(convertToMenuDS(menu));
		return menuDSList;
	}
	MenuDS convertToMenuDS(Menu menu) {
		List<MenuItem> menuItemList  = new ArrayList<>(menu.getMenuItemSet());
		Collections.sort(menuItemList, new Comparator<MenuItem>() {
			@Override
		    public int compare(MenuItem s1, MenuItem s2) {
		        return (int) (s1.getId()-s2.getId());
		    }
		});
		return new MenuDS(menu.getId(), menu.getStoreName(), menu.getStorePhone(), convertToMenuItemDSList(menuItemList));
	}
	List<MenuItemDS> convertToMenuItemDSList(List<MenuItem> menuItemList) {
		List<MenuItemDS> menuItemDSList = new ArrayList<>();
		for(MenuItem menuItem : menuItemList)
			menuItemDSList.add(convertToMenuItemDS(menuItem));
		return menuItemDSList;
	}
	MenuItemDS convertToMenuItemDS(MenuItem menuItem) {
		return new MenuItemDS(menuItem.getId(), menuItem.getName(), menuItem.getPrice());
	}
}
