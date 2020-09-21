package com.renan.bartendersmart.enitites;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.renan.bartendersmart.entities.enums.OrderStatus;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;	
	private Instant moment;	
	private Integer orderStatus;	
	private Table table;
	private Set<OrderItem> items = new HashSet<>();
	
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment,OrderStatus orderStatus, Table table) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.table = table;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}	

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public Table getTable() {
		return table;
	}

	public void setClient(Table table) {
		this.table = table;
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getTotal() {
		double soma = 0.0;
		for(OrderItem x : items) {
			soma += x.getSubTotal();
		}
		return soma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", moment=" + moment + ", table=" + table + "]";
	}

}
