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

import org.corp.sro.domain.Profesional;
import org.corp.sro.service.IProfesionalService;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name="ProfesionalBean")
@ViewScoped
public class ProfesionalBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty (value="#{ProfesionalService}")
	IProfesionalService profesionalService;
	
	List<Profesional> profesionals;
	
	Profesional profesi=new Profesional();
	Profesional profesiNuevo=new Profesional();
	Profesional profesiEditar=new Profesional();
	public IProfesionalService getProfesionalService() {
		return profesionalService;
	}
	public void setProfesionalService(IProfesionalService profesionalService) {
		this.profesionalService = profesionalService;
	}
	public List<Profesional> getProfesionals() {
		return profesionals;
	}
	public void setProfesionals(List<Profesional> profesionals) {
		this.profesionals = profesionals;
	}
	public Profesional getProfesi() {
		return profesi;
	}
	public void setProfesi(Profesional profesi) {
		this.profesi = profesi;
	}
	public Profesional getProfesiNuevo() {
		return profesiNuevo;
	}
	public void setProfesiNuevo(Profesional profesiNuevo) {
		this.profesiNuevo = profesiNuevo;
	}
	public Profesional getProfesiEditar() {
		return profesiEditar;
	}
	public void setProfesiEditar(Profesional profesiEditar) {
		this.profesiEditar = profesiEditar;
	}
	
	@PostConstruct
	public void init(){
		profesionals=profesionalService.getProfesionals();
	}
	
	public void editarEvent(){
		if(getProfesiNuevo()!=null){
			setProfesiEditar(profesionalService.getProfesionalById(getProfesiNuevo().getIdProfesional()));
			RequestContext.getCurrentInstance().execute("dialogEditar.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un profesional");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}
	
	public void eliminarEvent(){
		if(getProfesiNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un profesional");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}

	public void limpiarForms(){
		profesi=new Profesional();
		profesiNuevo=new Profesional();
		profesiEditar=new Profesional();
	}
	public void refrescarProfesionals(){
		setProfesionals(profesionalService.getProfesionals());
	}
	
	public void insertar(){
		profesionalService.addProfesional(getProfesi());
		refrescarProfesionals();
	}
	
	public void editar(){
		profesionalService.updateProfesional(getProfesiEditar());
		refrescarProfesionals();
	}
	public void eliminar(){
		profesionalService.deleteProfesionalLogico(getProfesiNuevo());
		refrescarProfesionals();
	}
	
	public void validarNuevo(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
          
        if(profesi.getNombres().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Nombre de Profesional");  
        }
        else if(profesi.getApellidos().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Apellidos de Profesional");  
        }
        else if(profesi.getTipo().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Profesión");  
        }
        else if(profesi.getColegio().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Codigo de Colegio Medico");  
        }
        else
        {
        	registrado = true;  

            if(profesi.getTipo().equals("Cirujano")){
        		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Cirujano "+profesi.getNombres());
        	}
        	else if(profesi.getTipo().equals("Instrumentista")){
        		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Instrumentista "+profesi.getNombres());
        	}
        	else 
        		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Anestesiólogo "+profesi.getNombres());
        	
            try{
            	refrescarProfesionals();
            	insertar();
            	RequestContext.getCurrentInstance().execute("PF('dialogNuevo').hide()");
            }catch(ConstraintViolationException  e)
            {
            	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Registro no insertado");
            }

        }
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("registrado", registrado);  
    }  
	
	public void validarEditar(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean editado = false;  
        
        if(profesiNuevo==null){
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un profesional");  
        }
        else if(profesiEditar.getNombres().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Nombre de Profesional");  
        }
        else if(profesiEditar.getApellidos().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Apellidos de Profesional");  
        }
        else if(profesiEditar.getTipo().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Profesión");  
        }
        else if(profesi.getColegio().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Codigo de Colegio Medico");  
        }
        else
        {
        	editado = true;  
        	
        	if(profesiEditar.getTipo().equals("Cirujano")){
        		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", "Cirujano "+profesiEditar.getNombres());
        	}
        	else if(profesiEditar.getTipo().equals("Instrumentista")){
        		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", "Instrumentista "+profesiEditar.getNombres());
        	}
        	else 
        		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", "Anestesiólogo "+profesiEditar.getNombres());
        	
            editar();
            refrescarProfesionals();
            RequestContext.getCurrentInstance().execute("PF('dialogEditar').hide()");
        }

        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("editado", editado);  
    }  
	
	public void validarEliminar(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean eliminado = false;  
          
        if(profesiNuevo!=null) {  
        	eliminado = true;  
        	
        	if(profesiNuevo.getTipo().equals("Cirujano")){
        		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Cirujano "+profesiNuevo.getNombres());
        	}
        	else if(profesiNuevo.getTipo().equals("Instrumentista")){
        		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Instrumentista "+profesiNuevo.getNombres());
        	}
        	else 
        		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Anestesiólogo "+profesiNuevo.getNombres());
   
            eliminar();
            refrescarProfesionals();
            
        } else {  
        	eliminado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un profesional");  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("eliminado", eliminado);  
    }  
	
}
