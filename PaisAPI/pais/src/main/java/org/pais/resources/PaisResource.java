package org.pais.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.pais.daos.PaisDAO;
import org.pais.models.Pais;

@Path("paises")
public class PaisResource {

		@Inject
		private PaisDAO paisDao;
				
		@POST
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<Pais> adicionarPais(Pais pais) {
			System.out.println(pais);
			this.paisDao.salvar(pais);
			return paisDao.listar();
		}
		
		@DELETE
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public List<Pais> eliminarPais(Pais pais) {
			System.out.println(pais);
			this.paisDao.eliminar(pais);
			return paisDao.listar();
		}
		
		@PUT
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
		public List<Pais> actualizarPais(Pais pais) {
			System.out.println(pais);
			this.paisDao.actualizar(pais);
			return paisDao.listar();
		}
		
		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Wrapped(element="paises")
		public List<Pais> lista(){
			return this.paisDao.listar();
		}
		
		@GET
		@Path("/{id}")
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Wrapped(element="paises")
		public Pais findById(@PathParam("id") Integer id){
			return this.paisDao.findById(id);
		}
		
		@GET
		@Path("/query")
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Wrapped(element="paises")
		public List<Pais> OrdenarCrescente(
				@DefaultValue("id") @QueryParam("orderby") String orderBy,
				@DefaultValue("asc") @QueryParam("level") String level
		){
			
			return this.paisDao.listarOrdem(orderBy, level);
		}
}
