package com.tbone.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Model class for Stock
 */
public class Stock implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	private Integer _stockId;
	private String stockCode;
	private String stockName;
 
	public Stock() {}
 
	public Stock(String stockCode, String stockName) {
		this.stockCode = stockCode;
		this.stockName = stockName;
	}
	
	public String toString(){
		return "stockId="+getStockId()+", stockCode="+getStockCode()+", stockName=" + getStockName(); 
	}
 
	@Id
	@GeneratedValue
	@Column(name = "stock_ID", unique = true, nullable = false)
	public Integer getStockId() {
		return this._stockId;
	}
 
	public void setStockId(Integer stockId) {
		this._stockId = stockId;
	}
 
	public String getStockCode() {
		return this.stockCode;
	}
 
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
 
	public String getStockName() {
		return this.stockName;
	}
 
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
 
}
