package br.com.links.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import br.com.links.modelo.Categoria;
import br.com.links.modelo.SubCategoria;

public class SubCategoriaDao extends GenericDao<SubCategoria> {

	public List<SubCategoria> procurarSubcategoriasDeUmaCategoria(Categoria categoria) {
		EntityManager fb = novaFabrica();
		StringBuilder sb = new StringBuilder();
		List<SubCategoria> subCategorias = new ArrayList<>();
		try {
			sb.append(" SELECT * FROM subcategoria \n");
			sb.append(" WHERE cdcategoria = :id  \n");
			Query query = fb.createNativeQuery(sb.toString(),SubCategoria.class);
			query.setParameter("id", categoria.getCdCategoria());
			subCategorias = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fb.close();
		}
		return subCategorias;
	}
	
	@Test
	public void testerino() {
		Categoria categoria = new Categoria();
		categoria.setCdCategoria(37);
		List<SubCategoria> subcategorias = procurarSubcategoriasDeUmaCategoria(categoria);
	}

	public void salvarCategoria(Categoria categoria) {
		EntityManager fb = novaFabrica();

		StringBuilder sb = new StringBuilder();
		try {
			sb.append(" INSERT INTO categoria \n");
			sb.append(" (descricao) VALUES (:descricao); \n");
			fb.getTransaction().begin();
			Query query = fb.createNativeQuery(sb.toString());
			query.setParameter("descricao", categoria.getDescricao());
			query.executeUpdate();
			fb.getTransaction().commit();
		} catch (Exception e) {
			fb.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			fb.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> listarCategoria() {
		EntityManager fb = novaFabrica();
		List<Categoria> categorias = new ArrayList<>();
		try {
			Query query = fb.createNativeQuery(" SELECT * FROM  categoria ", Categoria.class);
			categorias = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fb.close();
		}
		return categorias;
	}

	public Categoria listarCategoriaPorID(Categoria categoria) {
		Categoria cat = new Categoria();
		EntityManager fb = novaFabrica();
		try {
			Query query = fb.createNativeQuery(" SELECT * FROM  categoria WHERE cdcategoria = :id ", Categoria.class);
			query.setParameter("id", categoria.getCdCategoria());
			cat = (Categoria) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fb.close();
		}
		return cat;
	}

	public void deletarCategoria(Categoria categoria) {
		EntityManager fb = novaFabrica();
		try {
			fb.getTransaction().begin();
			Query query = fb.createNativeQuery(" DELETE FROM categoria WHERE cdcategoria = :id  ");
			query.setParameter("id", categoria.getCdCategoria());
			query.executeUpdate();
			fb.getTransaction().commit();
		} catch (Exception e) {
			fb.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			fb.close();
		}
	}

}
