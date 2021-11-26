package org.pais.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.pais.daos.PaisDAO;
import org.pais.models.Pais;

@Named
@RequestScoped
public class AdminPaisBean {

		@Inject
		private PaisDAO paisDao;
		
		private Pais  pais =  new Pais();
		
		public String salvar() {
			this.paisDao.salvar(this.pais);
			return "Salvo";
		}
		public void eliminar() {
			this.paisDao.eliminar(this.pais);
		}
		public void actualizar() {
			this.paisDao.actualizar(this.pais);
		}
		public List<Pais> listar() {
			return this.paisDao.listar();
		}
}
