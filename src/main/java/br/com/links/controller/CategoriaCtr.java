package br.com.links.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.links.modelo.Cargo;
import br.com.links.modelo.Categoria;
import br.com.links.modelo.SubCategoria;
import br.com.links.persistencia.CargoDao;
import br.com.links.persistencia.SubCategoriaDao;

public class CategoriaCtr {

	// Métodos Controllers

	CargoDao cargoDao = new CargoDao();

	public String cadastrarCargo(Cargo cargo) {
		String mensagem = null;
		try {
			if (cargoDao.verificaSeExisteCargoRepetidoParaCbo(cargo)) {
				mensagem = "Cargo já cadastrado no sistema.";
			} else {
				cargoDao.salvar(cargo);
				mensagem = "Cargo cadastrado com sucesso.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensagem;
	}

	public List<SubCategoria> popularSelectOneMenuSubcategoria(Categoria categoria) {
		SubCategoriaDao subCategoriaDao = new SubCategoriaDao();
		List<SubCategoria> subcategorias = new ArrayList<>();
		try {
			if (categoria != null) {
				subcategorias = subCategoriaDao.procurarSubcategoriasDeUmaCategoria(categoria);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subcategorias;
	}

	// Ativo 1 Inativo 0
	public List<Cargo> consultarCargos(Cargo cargo, List<String> status) {
		List<Cargo> resultado = new ArrayList<>();
		if (cargo != null) {
			if (cargo.getCbo() == null) {
				cargo.setCbo('X');
			}
		}
		try {
			if (cargo != null) {
				resultado = new CargoDao().consultarCargos(cargo.getNome(), cargo.getCodigoFolha(), cargo.getCbo(),
						status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public void editarCargos() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
