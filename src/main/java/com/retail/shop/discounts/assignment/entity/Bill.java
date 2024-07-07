package com.retail.shop.discounts.assignment.entity;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Shireen
 *
 */
public class Bill {

	private List<ItemsInStore> itemList;
	private Double totalBill;
	private Double totalBillAfterUserTypeDiscountOnBill;
	private Double finalbillCost;

	public Bill(List<ItemsInStore> itemList, Double totalBill, Double totalBillAfterUserTypeDiscountOnBill,
			Double finalbillCost) {
		this.itemList = itemList;
		this.totalBill = totalBill;
		this.totalBillAfterUserTypeDiscountOnBill = totalBillAfterUserTypeDiscountOnBill;
		this.finalbillCost = finalbillCost;
	}
	
	public Bill() {
	}

	public List<ItemsInStore> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemsInStore> itemList) {
		this.itemList = itemList;
	}

	public Double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(Double totalBill) {
		this.totalBill = totalBill;
	}

	public Double getTotalBillAfterUserTypeDiscountOnBill() {
		return totalBillAfterUserTypeDiscountOnBill;
	}

	public void setTotalBillAfterUserTypeDiscountOnBill(Double totalBillAfterUserTypeDiscountOnBill) {
		this.totalBillAfterUserTypeDiscountOnBill = totalBillAfterUserTypeDiscountOnBill;
	}

	public Double getFinalbillCost() {
		return finalbillCost;
	}

	public void setFinalbillCost(Double finalbillCost) {
		this.finalbillCost = finalbillCost;
	}
	
	@Component
	public static class BillBuilder {
		public Bill build(List<ItemsInStore> itemList, double totalBill, double totalBillAfterUserTypeDiscountOnBill,
				double finalbillCost) {
			return new Bill(itemList, totalBill, totalBillAfterUserTypeDiscountOnBill, finalbillCost);
		}
	}

}
