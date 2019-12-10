package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

public class Relatorio {

	private Calendar dataGeracao;
	private int quantidade;
	private List<Produto> produtos;
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Calendar getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(Calendar dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
}
