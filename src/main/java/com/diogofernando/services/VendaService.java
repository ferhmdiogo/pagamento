package com.diogofernando.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.diogofernando.pagamento.data.vo.VendaVO;
import com.diogofernando.pagamento.entity.ProdutoVenda;
import com.diogofernando.pagamento.entity.Venda;
import com.diogofernando.pagamento.repository.ProdutoRepository;
import com.diogofernando.pagamento.repository.ProdutoVendaRepository;
import com.diogofernando.pagamento.repository.ResourceNotFoundException;
import com.diogofernando.pagamento.repository.VendaRepository;



@Service
public class VendaService {
	
private final VendaRepository vendaRepository;
private final ProdutoVendaRepository produtoVendaRepository;
	
	@Autowired
	public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
		this.vendaRepository = vendaRepository;
		this.produtoVendaRepository = produtoVendaRepository;
	}
	
	public VendaVO create(VendaVO vendaVO) {
		Venda venda = vendaRepository.save(Venda.create(vendaVO));
		
		List<ProdutoVenda> produtosSalvos = new ArrayList<>();
		vendaVO.getProdutos().forEach(p -> {
			ProdutoVenda pv = new ProdutoVenda.create(p);
			pv.setVenda(venda);
			produtosSalvos.add(produtoRepository.save(pv));
			
		});
		venda.setProdutos(produtosSalvos);
		
		return VendaVO.create(venda);
	}
	
	public Page<VendaVO> findAll(Pageable pageable) {
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertToVendaVO);
	}
	
	private VendaVO convertToVendaVO(Venda venda) {
		return VendaVO.create(venda);
	}
	
	public VendaVO findById(Long id) {
		var entity = vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this IF"));
		return VendaVO.create(entity);
				
	}

}
