package com.retail.shop.discounts.assignment.entity;

/**
 * @author Shireen
 *
 */
public enum UserType {

	EMOLOYEE(1, 30), AFFILIATE(2, 10), OLD_CUSTOMER(3, 5);

	private final int typeId;
	private final int discountPercentage;
	
	UserType(int typeId, int discountPercentage) {
		this.typeId = typeId;
		this.discountPercentage = discountPercentage;
	}
	public int getTypeId() {
		return typeId;
	}
	public int getDiscountPercentage() {
		return discountPercentage;
	}

}
