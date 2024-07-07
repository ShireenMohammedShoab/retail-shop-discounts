/**
 * 
 */
package com.retail.shop.discounts.assignment.operations;

import static com.retail.shop.discounts.assignment.entity.ItemCategoryInStore.GROCERIES;

import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.retail.shop.discounts.assignment.entity.Bill;
import com.retail.shop.discounts.assignment.entity.ItemsInStore;
import com.retail.shop.discounts.assignment.entity.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shireen
 *
 */
@Slf4j
public class RetailShopBillInfo implements BillOperations {

	private static final Logger logger = LoggerFactory.getLogger(RetailShopBillInfo.class);
	
	private Bill bill;
	private User user;

	public RetailShopBillInfo(Bill bill, User user) {
	    this.bill = bill;
	    this.user = user;
	}
	/*
	 * collect user and bill information
	 */
	@Override
	public void collectUserInfo(User user) {
		this.bill = new Bill();
		this.user = user;
	}

	/*
	 * This function used to prepare shopping item list for bill calculation
	 * @param itemList list of item user buy
	 * @return total bill cost
	 */
	@Override
	public Double collectPurchasedItems(List<ItemsInStore> itemList) {
		bill.setItemList(itemList);
		return getTotalBillCost(itemList);
	}

	/*
	 * This function to calculate shopping item cost for all items
	 * 
	 * @param itemList list of item user buy
	 * @return total bill cost
	 */
	private Double getTotalBillCost(List<ItemsInStore> itemList) {
		  return itemList.stream()
                  .map(ItemsInStore::getItemCost)
                  .reduce(0.0, Double::sum);
	}

	/**
	 * This method used to apply discount per user as the following:
	 * Employee of the store, he gets a 30% discount 
	 * Affiliate of the store, he gets a 10% discount 
	 * Customer for over 2 years, he gets a 5% discount
	 * A user can get only one of the percentage based discounts on a bill.
	 * 
	 * @param cost total bill discount
	 * @return total bill cost after user type discount
	 */
	@Override
	public Double userTypeDiscountApply(Double cost) {
		bill.setTotalBill(cost);
		Double discountAmount;
		int discountPercentage = user.getUserType().getDiscountPercentage();
		Double groceriesAmount = getGroceriedItemCost(bill.getItemList());

		logger.info("Total Bill Amount is : {} " , bill.getTotalBill());
		logger.info("User Type is: {} and Discount: {} % " , user.getUserType().getTypeId() , discountPercentage);
		logger.info("Total GROCERIES items is : {}" , groceriesAmount);

		Double discountAmountWithoutGroceries = cost - groceriesAmount;
		logger.info("Total Bill Amount Without GROCERIES is: {}" , discountAmountWithoutGroceries);

		discountAmount = (discountAmountWithoutGroceries * discountPercentage) / 100;
		logger.info("Total user Type discount Amount: {} " , discountAmount);

		bill.setTotalBillAfterUserTypeDiscountOnBill(discountAmountWithoutGroceries - discountAmount);

		logger.info("Total Bill Amount After User Type Discount is on bill: {} " , bill.getTotalBillAfterUserTypeDiscountOnBill());

		logger.info("Total Bill Amount After User Type Discount including groceries amount is on bill: {} " , bill.getTotalBillAfterUserTypeDiscountOnBill() + groceriesAmount);
		
		return bill.getTotalBillAfterUserTypeDiscountOnBill();
	}

	/*
	 * This method used to calculate final discount on bill "For every $100 on the bill, there would be a 
	 * $5 discount (e.g. for $990, you get $45 as a discount).
	 * 
	 * @param billCost after apply first discount for user type
	 * @return final bill amount after apply discount
	 */
	@Override
	public Double totalBillDiscountApply(Double billCost) {
		logger.info("Bill Amount Before Final Discount is : {} " , billCost);
		double finalBillCost = IntStream.range(0, (int) Math.floor(billCost / 100))
		                                 .mapToDouble(i -> 5.0)
		                                 .sum();

		double finalBillCostAfterDiscount = billCost - finalBillCost;
		logger.info("Bill Amount After Final Discount is : {} " , finalBillCostAfterDiscount);

		return finalBillCostAfterDiscount;
	}

	/*
	 * This method used to return the total percentage of non groceries items
	 * 
	 * @param itemList from Iterm Store
	 * @return cost of groceries items
	 */
	@Override
	public Double getGroceriedItemCost(List<ItemsInStore> itemList) {
		return itemList.stream()
	            .filter(item -> item.getCategory().equals(GROCERIES))
	            .mapToDouble(ItemsInStore::getItemCost)
	            .sum();
	}
}