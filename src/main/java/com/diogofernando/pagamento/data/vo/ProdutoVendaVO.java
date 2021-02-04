package com.diogofernando.pagamento.data.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.diogofernando.pagamento.entity.Produto;
import com.diogofernando.pagamento.entity.ProdutoVenda;
import com.diogofernando.pagamento.entity.Venda;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","estoque"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable  {
	
	private static final long serialVersionUID = -4858157193253950901L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("id")
	private Long idProduto;
	
	@JsonProperty("quantidade")
	private Integer quantidade;
	
	public static ProdutoVendaVO create(ProdutoVenda produtoVenda) {
		return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
	}
	
	

}
