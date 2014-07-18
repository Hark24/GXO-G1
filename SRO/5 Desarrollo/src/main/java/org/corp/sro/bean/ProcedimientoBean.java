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

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.corp.sro.domain.Procedimiento;
import org.corp.sro.service.IProcedimientoService;

@ManagedBean(name="ProcedimientoBean")
@ViewScoped
public class ProcedimientoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty (value="#{ProcedimientoService}")
	IProcedimientoService procedimientoService;
	
	List<Procedimiento> procedimientos;
	
	Procedimiento proced=new Procedimiento();
	Procedimiento procedNuevo=new Procedimiento();
	Procedimiento procedEditar=new Procedimiento();
	
	public IProcedimientoService getProcedimientoService() {
		return procedimientoService;
	}
	public void setProcedimientoService(IProcedimientoService procedimientoService) {
		this.procedimientoService = procedimientoService;
	}
	public List<Procedimiento> getProcedimientos() {
		return procedimientos;
	}
	public void setProcedimientos(List<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}
	public Procedimiento getProced() {
		return proced;
	}
	public void setProced(Procedimiento proced) {
		this.proced = proced;
	}
	public Procedimiento getProcedNuevo() {
		return procedNuevo;
	}
	public void setProcedNuevo(Procedimiento procedNuevo) {
		this.procedNuevo = procedNuevo;
	}
	public Procedimiento getProcedEditar() {
		return procedEditar;
	}
	public void setProcedEditar(Procedimiento procedEditar) {
		this.procedEditar = procedEditar;
	}
	
	@PostConstruct
	public void init(){
		procedimientos=procedimientoService.getProcedimientos();
	}
	
	public void editarEvent(){
		if(getProcedNuevo()!=null){
			setProcedEditar(procedimientoService.getProcedimientoById(getProcedNuevo().getIdProcedimiento()));
			RequestContext.getCurrentInstance().execute("PF('dialogEditar').show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un procedimiento");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}


	public void limpiarForms(){
		proced=new Procedimiento();
		procedNuevo=new Procedimiento();
		procedEditar=new Procedimiento();
	}
	public void refrescarProcedimientos(){
		setProcedimientos(procedimientoService.getProcedimientos());
	}
	
	public void insertar(){
		procedimientoService.addProcedimiento(getProced());
		refrescarProcedimientos();
	}
	
	public void editar(){
		procedimientoService.updateProcedimiento(getProcedEditar());
		refrescarProcedimientos();
	}
	
	public void eliminar(){
		procedimientoService.deleteProcedimientoLogico(getProcedNuevo());
		refrescarProcedimientos();
	}
	
	public void validarNuevo(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
        
        if(proced.getNombre().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Nombre de procedimiento");  
        }
        else
        {
        	registrado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Procedimiento "+proced.getNombre());
            
            try{
            	insertar();
            	refrescarProcedimientos();
            	RequestContext.getCurrentInstance().execute("PF('dialogNuevo').hide()");
            }catch(ConstraintViolationException  e)
            {
            	e.printStackTrace();
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
        
        if(procedNuevo==null){
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un procedimiento"); 
        }
        else if(procedEditar.getNombre().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Nombre de procedimiento");  
        }
        else
        {
        	editado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", "Procedimiento "+procedEditar.getNombre());
            RequestContext.getCurrentInstance().execute("PF('dialogEditar').hide()");
            editar();
            refrescarProcedimientos();
        } 
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("editado", editado);  
    }  
	
	public void validarEliminar(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean eliminado = false;  
          
        if(procedNuevo!=null) {  
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Procedimiento "+ procedNuevo.getNombre());
            eliminar();
            refrescarProcedimientos();
        } else {  
        	eliminado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un procedimiento");  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("eliminado", eliminado);  
    }  
}
