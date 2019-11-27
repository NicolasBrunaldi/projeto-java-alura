package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

public class Pedido {

	private Integer id;
	
	private Double valor;
	
	private Calendar data;
	
	private List<Produto> produtos;
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}

	
	@Override
	public String toString() {
		return "[ Id: " + id + " Valor: " + valor + " Data: " + data + " produtos: " + produtos + " ]";
	}
}
