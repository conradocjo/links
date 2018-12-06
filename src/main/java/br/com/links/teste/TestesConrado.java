package br.com.links.teste;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;

import org.junit.Ignore;
import org.junit.Test;

import br.com.links.modelo.Cargo;
import br.com.links.modelo.Categoria;
import br.com.links.persistencia.CargoDao;
import br.com.links.persistencia.CategoriaDao;
import br.com.links.persistencia.GenericDao;

public class TestesConrado {

	@Ignore
	@Test
	public void testarConexao() {
		Persistence.createEntityManagerFactory("linkcomconrado");
	}
	
	@Ignore
	@Test
	public void testesdrion() throws Exception {
		Categoria cat = new Categoria();
		cat.setDescricao("testeconrado22");
		new GenericDao<Categoria>().salvar(cat);
	
	}
	
	@Ignore
	@Test
	public void tester24() {
		Character c = 'C';
		Character b = 'B';

		if (b.equals(c)) {
			System.out.println("Alguma coisa estranha está acontecendo");
		} else
			System.out.println("characteres são diferentes");
	}

	@Ignore
	@Test
	public void testerino14() {
		Cargo cargo = new Cargo();
		cargo.setCbo('C');
		cargo.setNome("Mecanico");
		System.out.println(new CargoDao().verificaSeExisteCargoRepetidoParaCbo(cargo));
	}
	
	
	@Ignore
	@Test
	public void testerion() {
//		Categoria ct = new Categoria();
//		ct.setDescricao("conrado Teste");
//		salvarCategoria(ct);
		CategoriaDao dao = new CategoriaDao();
		
		List<Categoria> lista = new ArrayList<>();
		lista = dao.listarCategoria();
		for (Categoria a : lista) {
			System.out.println(a.getCdCategoria() +   a.getDescricao());
		}
		
//		listarCategoria();
	}
	
	@Ignore
	@Test
	public void testerino() {
		CategoriaDao dao = new CategoriaDao();
		Categoria cat = new Categoria();
		cat.setDescricao("Conrado");
		dao.salvarCategoria(cat);
		
	}
	
	@Ignore
	@Test
	public void testerino2() {
		Categoria cate = new Categoria();
		cate.setCdCategoria(6);
		
		System.out.println(new CategoriaDao().listarCategoriaPorID(cate).getDescricao());
	}
	
	@Ignore
	@Test
	public void tester() {
		Categoria cat = new Categoria();
		cat.setCdCategoria(4);
		new CategoriaDao().deletarCategoria(cat);
	}
}
