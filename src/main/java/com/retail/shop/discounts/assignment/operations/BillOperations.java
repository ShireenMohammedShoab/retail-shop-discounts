package com.retail.shop.discounts.assignment.operations;

import java.util.List;

import com.retail.shop.discounts.assignment.entity.ItemsInStore;
import com.retail.shop.discounts.assignment.entity.User;

/**
 * @author Shireen
 *
 */
public interface BillOperations {

	public Double userTypeDiscountApply(Double cost);

	public Double totalBillDiscountApply(Double cost);

	Double getGroceriedItemCost(List<ItemsInStore> itemList);

	Double collectPurchasedItems(List<ItemsInStore> itemList);

	void collectUserInfo(User user);

}
