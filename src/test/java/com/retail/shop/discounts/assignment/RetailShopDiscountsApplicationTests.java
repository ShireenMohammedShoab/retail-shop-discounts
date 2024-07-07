package com.retail.shop.discounts.assignment;

import static com.retail.shop.discounts.assignment.entity.ItemCategoryInStore.CAR_ACCESSORIES;
import static com.retail.shop.discounts.assignment.entity.ItemCategoryInStore.CLOTHS;
import static com.retail.shop.discounts.assignment.entity.ItemCategoryInStore.ELECTRONICS;
import static com.retail.shop.discounts.assignment.entity.ItemCategoryInStore.GROCERIES;
import static com.retail.shop.discounts.assignment.entity.ItemCategoryInStore.KITCHEN_ACCESSORIES;
import static com.retail.shop.discounts.assignment.entity.ItemCategoryInStore.OTHER;
import static com.retail.shop.discounts.assignment.entity.UserType.AFFILIATE;
import static com.retail.shop.discounts.assignment.entity.UserType.EMOLOYEE;
import static com.retail.shop.discounts.assignment.entity.UserType.OLD_CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.retail.shop.discounts.assignment.entity.Bill;
import com.retail.shop.discounts.assignment.entity.Bill.BillBuilder;
import com.retail.shop.discounts.assignment.entity.ItemCategoryInStore;
import com.retail.shop.discounts.assignment.entity.ItemsInStore;
import com.retail.shop.discounts.assignment.entity.User.UserBuilder;
import com.retail.shop.discounts.assignment.operations.RetailShopBillInfo;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class RetailShopDiscountsApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(RetailShopDiscountsApplicationTests.class);
	
	@Autowired UserBuilder userBuilder;
	@Autowired BillBuilder billBuilder;
	@Autowired RetailShopBillInfo retailShopBillInfo;
	@Mock Bill bill;
	@Mock List<ItemsInStore> items;
	@InjectMocks RetailShopBillInfo retailShopBill;
	
	@BeforeEach
	void before() {

		logger.info("Storing items in ItemsInStore List");
		items = new ArrayList<>();

		items.add(createItem("Seat Covers", 300.50, CAR_ACCESSORIES));
		items.add(createItem("Shoe Matt", 30.50, CAR_ACCESSORIES));
		items.add(createItem("Ceramic Plates", 400.00, KITCHEN_ACCESSORIES));
		items.add(createItem("Shirts", 180.00, CLOTHS));
		items.add(createItem("Fruits", 50.00, GROCERIES));
		items.add(createItem("Basmati Rice 10KG", 250.00, GROCERIES));
		items.add(createItem("Onion 1KG", 40.00, GROCERIES));
		items.add(createItem("Iphone 15 pro max", 1200.99, ELECTRONICS));
		items.add(createItem("Wooden Sticks For BBQ", 0.5, OTHER));

		bill = billBuilder.build(items, 0.0, 0.0, 0.0);

	}
	
	/* Method to create an item */
	private ItemsInStore createItem(String itemName, double itemCost, ItemCategoryInStore category) {
	    ItemsInStore item = new ItemsInStore();
	    item.setItemName(itemName);
	    item.setItemCost(itemCost);
	    item.setCategory(category);
	    return item;
	}
	
	@Test
	void contextLoads() {
		logger.info("---------- Context Loaded ----------");}

	@Test
	void main() {
		logger.info("---- Inside RetailShopDiscountsApplicationTests.main() ----");
		RetailShopDiscountsApplication.main(new String[] {});
	}

	/**
	 * 30% discount to the Employee
	 */
	@Test
	void testEmployeeUserDiscount() {
		retailShopBill.collectUserInfo(userBuilder.build("Mohammed", "971 5223334452", EMOLOYEE));
		
		Double billCost = retailShopBill.collectPurchasedItems(items);
		
		Double billCostAfterDiscount = retailShopBill.userTypeDiscountApply(billCost);
		Double groceriesAmount = retailShopBillInfo.getGroceriedItemCost(bill.getItemList());
		
		Double netPayAmount = calculateNetPayableAmount(billCostAfterDiscount+ groceriesAmount);
		
		logger.info("UserType Employee and its 30% discount without grocery items : {} "
				+ "and For every $100 on the bill, there would be a $ 5 discount : {} ", billCostAfterDiscount, netPayAmount);
	}

	/**
	 * 10% discount to the Affiliate
	 */
	@Test
	void testAffiliateUserDiscount() {
		retailShopBill.collectUserInfo(userBuilder.build("Mohammed Shoab", "971 5223334450", AFFILIATE));

		Double billCost = retailShopBill.collectPurchasedItems(items);

		Double billCostAfterDiscount = retailShopBill.userTypeDiscountApply(billCost);

		Double deductionAmount = (billCost - retailShopBill.getGroceriedItemCost(items));

		assertEquals(billCost - (deductionAmount * 0.10), billCostAfterDiscount);

	}

	/**
	 * 5% discount to old customer
	 */
	@Test
	void testOldCustomerSegmentDicount() {
		retailShopBill.collectUserInfo(userBuilder.build("Shireen M", "971 5223334890", OLD_CUSTOMER));

		Double billCost = retailShopBill.collectPurchasedItems(items);

		Double billCostAfterDiscount = retailShopBill.userTypeDiscountApply(billCost);

		Double deductionAmount = (billCost - retailShopBill.getGroceriedItemCost(items));

		assertEquals(Double.valueOf(billCost - (deductionAmount * 0.05)), billCostAfterDiscount);
	}

	@Test
	void testCollectPurchasedItems() {
		Double billCost = retailShopBill.collectPurchasedItems(items);
		assertEquals(Double.valueOf(2452.49), billCost);
	}

	@Test
	void testFinalDiscount() {
		
		retailShopBill.collectUserInfo(userBuilder.build("Shireen M", "971 5223334890", OLD_CUSTOMER));
		
		Double billCost = retailShopBill.collectPurchasedItems(items);

		Double billCostAfterDiscount = retailShopBill.userTypeDiscountApply(billCost);
		Double billCostAfterFinalDiscount = retailShopBill.totalBillDiscountApply(billCostAfterDiscount);

		Double expectedValue = billCostAfterDiscount - (Math.floor(Math.floor(billCostAfterDiscount) / 100) * 5);

		assertEquals(expectedValue, billCostAfterFinalDiscount);

	}
	
	private double calculateNetPayableAmount(double billAmount) {
	        double discount = 0;
	        
	        // Calculate $5 discount for every $100 on the bill
	        discount += (int) (billAmount / 100) * 5;
	        
	        return billAmount - discount;
	    }

}
