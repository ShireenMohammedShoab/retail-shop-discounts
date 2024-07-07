package com.retail.shop.discounts.assignment.entity;

/**
 * @author Shireen
 *
 */
public class ItemsInStore {

	private String itemName;
	private Double itemCost;
	private ItemCategoryInStore category;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemCost() {
		return itemCost;
	}

	public void setItemCost(Double itemCost) {
		this.itemCost = itemCost;
	}

	public ItemCategoryInStore getCategory() {
		return category;
	}

	public void setCategory(ItemCategoryInStore category) {
		this.category = category;
	}
}
