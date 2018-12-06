package br.com.links.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.links.controller.CategoriaCtr;
import br.com.links.modelo.Cargo;
import br.com.links.modelo.Categoria;
import br.com.links.modelo.SubCategoria;
import br.com.links.persistencia.CargoDao;
import br.com.links.persistencia.CategoriaDao;
import br.com.links.persistencia.SubCategoriaDao;

@ManagedBean
@ViewScoped
public class CategoriaBean {

	private List<Categoria> listaCategoria;

	private Categoria categoria;

	private Categoria categoriaSelecionada;

	private SubCategoria subcategoria;

	private SubCategoria subcategoriaSelecionada;

	private List<SubCategoria> listaSubcategoria;

	private List<SubCategoria> listaSubcategoriaSelectOneMenu;

	private Cargo cargo;

	private Cargo cargoSelecionado;

	private Cargo cargoBuscado;

	private List<String> status;

	private List<Cargo> listaDeCargos;

	private Boolean ativoFormatado;

	// Primeiro método a ser iniciado no JSF
	@PostConstruct
	public void inicializar() {
		listaSubcategoria = new ArrayList<>();
		listaCategoria = new ArrayList<>();
		subcategoriaSelecionada = new SubCategoria();
		subcategoria = new SubCategoria();
		setCategoria(new Categoria());
		categoriaSelecionada = new Categoria();
		listaSubcategoria = new SubCategoriaDao().listar(new SubCategoria());
		listaCategoria = new CategoriaDao().listarCategoria();
		cargo = new Cargo();
		cargoSelecionado = new Cargo();
		listaDeCargos = new ArrayList<>();
		setStatus(new ArrayList<>());
		cargoBuscado = new Cargo();
		listaDeCargos = new CargoDao().listar(cargo);
	}

	// Método para carregar as listas para rotinas ao mudar de tab

	public void cadastrarCategoria() throws Exception {
		try {
			CategoriaDao dao = new CategoriaDao();
			if (categoria.getDescricao() != null) {
				dao.salvarCategoria(categoria);
				Messages.addGlobalInfo("Categoria " + categoria.getDescricao() + " gravada com êxito!");
				listaCategoria = new CategoriaDao().listarCategoria();
				categoria = new Categoria();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao gravar a categoria " + categoria.getDescricao());
		}
	}

	public void excluirCategoria() {
		try {
			if (categoriaSelecionada != null) {
				CategoriaDao dao = new CategoriaDao();
				dao.deletarPorId(Categoria.class, categoriaSelecionada.getCdCategoria());
				Messages.addGlobalInfo("Categoria " + categoriaSelecionada.getDescricao() + " apagada!");
				listaCategoria = new CategoriaDao().listarCategoria();
				categoriaSelecionada = new Categoria();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Categoria " + categoriaSelecionada.getDescricao()
					+ " não pode ser excluído, já possui referências em outros registros do sistema.");
		}
	}

	public void editarCategoria() {
		try {
			if (categoriaSelecionada != null) {
				CategoriaDao dao = new CategoriaDao();
				dao.atualizarCategoria(categoriaSelecionada, categoriaSelecionada.getDescricao());
				Messages.addGlobalInfo("Categoria " + categoriaSelecionada.getDescricao() + " gravada com êxito!");
				listaCategoria = new CategoriaDao().listarCategoria();
				categoriaSelecionada = new Categoria();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao gravar a categoria " + categoriaSelecionada.getDescricao());
		}
	}

	// Métodos da SubCategoria

	public void cadastrarSubCategoria() throws Exception {
		try {
			SubCategoriaDao dao = new SubCategoriaDao();
			subcategoria.setCdCategoria(categoriaSelecionada);
			dao.salvar(subcategoria);
			Messages.addGlobalInfo("Subcategoria " + categoria.getDescricao() + " gravada com êxito!");
			listaSubcategoria = dao.listar(subcategoria);
			subcategoria = new SubCategoria();
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao gravar a subcategoria " + categoria.getDescricao());
		}
	}

	public void excluirSubCategoria() {
		try {
			if (subcategoriaSelecionada != null) {
				SubCategoriaDao dao = new SubCategoriaDao();
				dao.deletarPorId(SubCategoria.class, subcategoriaSelecionada.getCdSubcategoria());
				Messages.addGlobalInfo("SubCategoria " + subcategoriaSelecionada.getDescricao() + " apagada!");
				listaSubcategoria = new SubCategoriaDao().listar(subcategoria);
				subcategoriaSelecionada = new SubCategoria();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Subcategoria  " + subcategoriaSelecionada.getDescricao()
					+ " não pode ser excluído, já possui referências em outros registros do sistema.");
		}
	}

	public void editarSubCategoria() {
		try {
			if (subcategoriaSelecionada != null) {
				SubCategoriaDao dao = new SubCategoriaDao();
				subcategoriaSelecionada.setCdCategoria(categoriaSelecionada);
				dao.update(subcategoriaSelecionada);
				Messages.addGlobalInfo(
						"Subcategoria " + subcategoriaSelecionada.getDescricao() + " gravada com êxito!");
				listaSubcategoria = new SubCategoriaDao().listar(subcategoriaSelecionada);
				subcategoriaSelecionada = new SubCategoria();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao gravar a subcategoria " + subcategoriaSelecionada.getDescricao());
		}
	}

	// Métodos para Cadastro e consulta de Cargo

	public void popularSelectOneMenuSubcategoria() {
		listaSubcategoriaSelectOneMenu = new CategoriaCtr().popularSelectOneMenuSubcategoria(categoriaSelecionada);
	}

	public void cadastrarCargo() {
		try {
			if (ativoFormatado) {
				cargo.setAtivo("1");
			} else {
				cargo.setAtivo("0");
			}
			cargo.setCdSubcategoria(subcategoriaSelecionada);
			if (new CargoDao().verificaSeExisteCargoRepetidoParaCbo(cargo) == false) {
				new CargoDao().salvar(cargo);
				Messages.addGlobalInfo("Cargo "+cargo.getDescricao()+" cadastradado.");
			} else {
				Messages.addGlobalWarn("Cargo "+cargo.getDescricao()+" já cadastrado no sistema.");
			}
			cargo = new Cargo();
			listaDeCargos = new CargoDao().listar(cargo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 1 ativo 0 inativo
	public void consultarCargos() {
		try {
			listaDeCargos = new CategoriaCtr().consultarCargos(cargoBuscado, getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ecluirCargo() {
		CargoDao dao = new CargoDao();
		try {
			if (cargoSelecionado != null) {
				dao.deletarPorId(Cargo.class, cargoSelecionado.getCdCargo());
				Messages.addGlobalInfo("Cargo " +cargoSelecionado + " deletado com sucesso!" );
				listaDeCargos = new CargoDao().listar(cargoSelecionado);
				cargoSelecionado = new Cargo();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Cargo não pode ser excluído, já possui referências em outros registros do sistema.");
		}
	}

	public void editarCargo() {
		try {
			new CargoDao().update(cargoSelecionado);
			Messages.addGlobalInfo("Cargo " +cargoSelecionado + " atualizado com sucesso!" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters e Setters

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public SubCategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(SubCategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public SubCategoria getSubcategoriaSelecionada() {
		return subcategoriaSelecionada;
	}

	public void setSubcategoriaSelecionada(SubCategoria subcategoriaSelecionada) {
		this.subcategoriaSelecionada = subcategoriaSelecionada;
	}

	public List<SubCategoria> getListaSubcategoria() {
		return listaSubcategoria;
	}

	public void setListaSubcategoria(List<SubCategoria> listaSubcategoria) {
		this.listaSubcategoria = listaSubcategoria;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Cargo getCargoSelecionado() {
		return cargoSelecionado;
	}

	public void setCargoSelecionado(Cargo cargoSelecionado) {
		this.cargoSelecionado = cargoSelecionado;
	}

	public List<Cargo> getListaDeCargos() {
		return listaDeCargos;
	}

	public void setListaDeCargos(List<Cargo> listaDeCargos) {
		this.listaDeCargos = listaDeCargos;
	}

	public List<SubCategoria> getListaSubcategoriaSelectOneMenu() {
		return listaSubcategoriaSelectOneMenu;
	}

	public void setListaSubcategoriaSelectOneMenu(List<SubCategoria> listaSubcategoriaSelectOneMenu) {
		this.listaSubcategoriaSelectOneMenu = listaSubcategoriaSelectOneMenu;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public Cargo getCargoBuscado() {
		return cargoBuscado;
	}

	public void setCargoBuscado(Cargo cargoBuscado) {
		this.cargoBuscado = cargoBuscado;
	}

	public Boolean getAtivoFormatado() {
		return ativoFormatado;
	}

	public void setAtivoFormatado(Boolean ativoFormatado) {
		this.ativoFormatado = ativoFormatado;
	}
}
