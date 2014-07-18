package org.corp.sro.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.corp.sro.domain.Sala;
import org.corp.sro.service.ISalaService;

@ManagedBean(name="SalaBean")
@ViewScoped
public class SalaBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	@ManagedProperty (value="#{SalaService}")
	ISalaService salaService;
	
	List<Sala> salas;
	
	Sala sala=new Sala();
	Sala salaNuevo=new Sala();
	Sala salaEditar=new Sala();
	public ISalaService getSalaService() {
		return salaService;
	}
	public void setSalaService(ISalaService salaService) {
		this.salaService = salaService;
	}
	public List<Sala> getSalas() {
		return salas;
	}
	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Sala getSalaNuevo() {
		return salaNuevo;
	}
	public void setSalaNuevo(Sala salaNuevo) {
		this.salaNuevo = salaNuevo;
	}
	public Sala getSalaEditar() {
		return salaEditar;
	}
	public void setSalaEditar(Sala salaEditar) {
		this.salaEditar = salaEditar;
	}
	
	@PostConstruct
	public void init(){
		salas=salaService.getSalas();
	}
	
	public void editarEvent(){
		if(getSalaNuevo()!=null){
			setSalaEditar(salaService.getSalaById(getSalaNuevo().getIdSala()));
			RequestContext.getCurrentInstance().execute("PF('dialogEditar').show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione una sala");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}

	public void eliminarEvent(){
		if(getSalaNuevo()!=null){

		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione una sala");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}
	
	public void limpiarForms(){
		sala=new Sala();
		salaNuevo=new Sala();
		salaEditar=new Sala();
	}
	
	public void refrescarSalas(){
		salaService.updateEstado();
		setSalas(salaService.getSalas());
	}
	
	public void insertar(){
		salaService.addSala(getSalaNuevo());
		refrescarSalas();
	}
	
	public void editar(){
		getSalaEditar().setIdSala(getSalaNuevo().getIdSala());
		salaService.updateSala(getSalaEditar());
		refrescarSalas();
	}
	
	public void eliminar(){
		salaService.deleteSalaLogico(getSalaNuevo());
		refrescarSalas();
	}
	
	public void validarNuevo(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
         
        if(salaNuevo.getDescripcion().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Descripcion de Sala incorrecto");  
        }
        else
        {
        	registrado = true;  
        	RequestContext.getCurrentInstance().execute("PF('dialogNuevo').hide()");
            insertar();
            refrescarSalas();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Sala "+Integer.toString(sala.getIdSala()));
        } 
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("registrado", registrado);  
    }  
	
	public void validarEditar(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean editado = false;  
          
        if(sala==null){  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione una sala");  
        } 
        else if(salaEditar.getDescripcion().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Descripcion de Sala incorrecto");  
        }
        else
        {
        	editado = true;  
        	RequestContext.getCurrentInstance().execute("PF('dialogEditar').hide()");
            editar();
            refrescarSalas();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", "Sala "+Integer.toString(sala.getIdSala()));
        } 
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("editado", editado);  
    }  
	
	public void validarEliminar(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean eliminado = false;  
          
        if(salaNuevo!=null) {  
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Sala "+Integer.toString(salaNuevo.getIdSala()));
            eliminar();
            refrescarSalas();
        } else {  
        	eliminado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione una sala");  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("eliminado", eliminado);  
    }  
}
