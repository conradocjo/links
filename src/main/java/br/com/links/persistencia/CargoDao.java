package br.com.links.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import br.com.links.modelo.Cargo;

public class CargoDao extends GenericDao<Cargo> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> statusMostrar;

	// True = existe cargo com o CBO
	// False = não existe cargo com CBO
	public Boolean verificaSeExisteCargoRepetidoParaCbo(Cargo cargo) {
		Boolean retorno = null;
		StringBuilder sb = new StringBuilder();
		EntityManager fb = novaFabrica();
		Cargo retornoCargoDaQuery = new Cargo();
		try {
			sb.append(" SELECT * FROM CARGO \n");
			sb.append(" WHERE NOME = :cargo \n");
			sb.append(" AND CBO = :cbo \n");
			Query query = fb.createNativeQuery(sb.toString(), cargo.getClass());
			query.setParameter("cargo", cargo.getNome());
			query.setParameter("cbo", cargo.getCbo());
			retornoCargoDaQuery = (Cargo) query.getSingleResult();
			if (retornoCargoDaQuery == null) {
				retorno = false;
			} else {
				retorno = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		} finally {
			fb.close();
		}
		return retorno;
	}

	// Ativo 1 Inativo 0
	@SuppressWarnings("unchecked")
	public List<Cargo> consultarCargos(String nomeCargo, Integer codigoFolha, Character cbo,
			List<String> statusMostrar) {
		List<Cargo> resultado = new ArrayList<>();
		EntityManager fabrica = novaFabrica();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(" SELECT * FROM CARGO \n");
			sb.append(" WHERE 1=1 \n");
			if (!nomeCargo.equals(null)) {
				if (!nomeCargo.equals("")) {
					sb.append(" AND nome LIKE :nomeCargo \n");
				}
			}

			if (codigoFolha != null) {
				sb.append(" AND codigo_folha = :codigoFolha \n");
			}

			if (!cbo.equals('X')) {
				sb.append(" AND cbo = :cbo \n");
			}

			if (statusMostrar.size() > 0) {
				sb.append(" AND ativo in (:statusMostrar) \n");
			}

			Query query = fabrica.createNativeQuery(sb.toString(), Cargo.class);
			if (!nomeCargo.equals(null)) {
				if (!nomeCargo.equals("")) {
					query.setParameter("nomeCargo", "%" + nomeCargo + "%");
				}
			}

			if (!cbo.equals('X')) {
				query.setParameter("cbo", cbo);
			}

			if (codigoFolha != null) {
				query.setParameter("codigoFolha", codigoFolha);
			}
			
			if (statusMostrar.size() > 0) {
				query.setParameter("statusMostrar", statusMostrar);
			}
			
			resultado = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fabrica.close();
		}
		return resultado;
	}

	
	
	@Test
	public void tessterion() {
		String nomeCargo = "";
		Integer codigoFolha = 12;
		Character cbo = 'C';
		List<String> statusMostrar = new ArrayList<>(); 
		
		List<Cargo> lista = consultarCargos(nomeCargo, codigoFolha, cbo, statusMostrar);
	}
	
	

}
