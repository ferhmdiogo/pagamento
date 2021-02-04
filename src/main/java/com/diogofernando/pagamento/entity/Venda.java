package com.diogofernando.pagamento.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.diogofernando.pagamento.data.vo.VendaVO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "venda")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Venda implements Serializable {

	private static final long serialVersionUID = -8413032058968617835L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "MM/dd/yyy")
	@Column(name = "data", nullable = false, length = 10)
	private Date data;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH)
	private List<ProdutoVenda> produtos;
	
	@Column(name = "valorTotal", nullable = false, length = 10)
	private Double valorTotal;
	
	public static Venda create(VendaVO vendaVO) {
		return new ModelMapper().map(vendaVO, Venda.class);
	}

}
