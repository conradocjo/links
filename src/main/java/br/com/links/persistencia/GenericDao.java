package br.com.links.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao <T> {

	/**
	 * o objeto abaixo será populado com informações do Persistence.xml Estas
	 * informações serão atribuidas caso o mesmo esteja vazio e o método novaFabrica
	 * irá retornar um gerenciador de T da unidade de persistencia
	 */

	private static EntityManagerFactory unidadePersistencia = null;

	public static EntityManager novaFabrica() {
		if (unidadePersistencia == null) {
			unidadePersistencia = Persistence.createEntityManagerFactory("linkcomconrado");
		}
		EntityManager gerenciadorDeT = unidadePersistencia.createEntityManager();

		return gerenciadorDeT;
	}

	/**
	 * As classes abaixo são para fazer o CRUD de maneira genérica.
	 * 
	 * @throws Exception
	 * 
	 */

	public void salvar(T T) throws Exception {
		EntityManager gerenciarT = novaFabrica();
		try {
			gerenciarT.getTransaction().begin();
			gerenciarT.persist(T);
			gerenciarT.getTransaction().commit();

		} catch (Exception e) {
			gerenciarT.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			gerenciarT.close();
		}
	}
	
	

	public void deletar(T T) {
		EntityManager gerenciarT = novaFabrica();
		try {
			gerenciarT.getTransaction().begin();
			gerenciarT.remove(T);
			gerenciarT.getTransaction().commit();

		} catch (Exception e) {
			gerenciarT.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			gerenciarT.close();
		}

	}
	
	
	public void deletarPorId(Class<T> classe, Integer id) {
		EntityManager gerenciadorT = novaFabrica();
		Object objeto = null;
		try {
			gerenciadorT.getTransaction().begin();
			objeto = gerenciadorT.find(classe, id);
			gerenciadorT.remove(objeto);
			gerenciadorT.getTransaction().commit();
		} catch (Exception e) {
			gerenciadorT.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			gerenciadorT.close();
		}
	}
	

	public void update(T T) {
		EntityManager gerenciarT = novaFabrica();
		try {
			gerenciarT.getTransaction().begin();
			gerenciarT.merge(T);
			gerenciarT.getTransaction().commit();

		} catch (Exception e) {
			gerenciarT.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			gerenciarT.close();
		}

	}

	@SuppressWarnings("unchecked")
	public T buscarPorID(Class<T> classe, Long id) {

		EntityManager gerenciarT = novaFabrica();
		Object objeto = null;
		try {
			objeto = gerenciarT.find(classe, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			gerenciarT.close();
		}
		return (T) objeto;
	}

	@SuppressWarnings("unchecked")
	public List<T> listar(T entidade) {
		EntityManager gerenciarT = novaFabrica();
		List<T> listaTGenerica = new ArrayList<>();
		try {
			listaTGenerica = gerenciarT.createQuery("FROM " + entidade.getClass().getName())
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTGenerica;
	}

	public Object listarPorId(Long id, Class<T> T) {
		EntityManager gerenciarT = novaFabrica();
		Object resultado = null;
		try {
			
			resultado =  gerenciarT.find(T, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			gerenciarT.close();
		}
		return resultado;
	}
	
}
