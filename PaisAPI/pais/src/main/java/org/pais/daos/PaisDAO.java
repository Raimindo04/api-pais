package org.pais.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.pais.models.Pais;

public class PaisDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void salvar(Pais pais) {
		this.entityManager.persist(pais);
	}
	
	@Transactional
	public void  eliminar(Pais pais) {
		pais = entityManager.merge(pais);
		this.entityManager.remove(pais);
	}
	
	@Transactional
	public void actualizar(Pais pais) {
		this.entityManager.merge(pais);
	}
	
	public List<Pais> listar(){
		return this.entityManager.createQuery("select p from Pais p",Pais.class).getResultList();
	}
	
	public Pais findById(Integer id){
		TypedQuery<Pais> query = this.entityManager.createQuery("select p from Pais p where id = :id ", Pais.class);
		return (boolean) query.setParameter("id", id).getResultList().isEmpty() ? null : query.setParameter("id", id).getSingleResult();
	}
	public List<Pais> listarOrdem(String params, String ordem){
		return this.entityManager.createQuery("select p from Pais p order by "+params+" "+ ordem,Pais.class).getResultList();
	}
	
}
