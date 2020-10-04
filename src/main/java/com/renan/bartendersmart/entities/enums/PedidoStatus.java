package com.renan.bartendersmart.entities.enums;

public enum PedidoStatus {
	
	WAITING_PRODUCTION(1),
	READY(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	private PedidoStatus(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static PedidoStatus valueOf(int code) {
		for (PedidoStatus value: PedidoStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("PedidoStatus invalido");
	}
}
