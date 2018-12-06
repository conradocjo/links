package br.com.links.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.links.modelo.Categoria;

public class CategoriaDao extends GenericDao<Categoria>{

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
	
	public void atualizarCategoria(Categoria categoria, String descricao) {
		EntityManager fb = novaFabrica();
		try {
			fb.getTransaction().begin();
			Query query = fb.createNativeQuery(" UPDATE categoria SET descricao = :descricao WHERE cdcategoria = :id ");
			query.setParameter("id", categoria.getCdCategoria());
			query.setParameter("descricao", descricao);
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
