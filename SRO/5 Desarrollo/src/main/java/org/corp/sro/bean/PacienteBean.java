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

import org.corp.sro.domain.Paciente;
import org.corp.sro.service.IPacienteService;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name="PacienteBean")
@ViewScoped
public class PacienteBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	@ManagedProperty (value="#{PacienteService}")
	IPacienteService pacienteService;
	
	List<Paciente> pacientes;
	
	Paciente paciente=new Paciente();
	Paciente pacienteNuevo=new Paciente();
	Paciente pacienteEditar=new Paciente();
	public IPacienteService getPacienteService() {
		return pacienteService;
	}
	public void setPacienteService(IPacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Paciente getPacienteNuevo() {
		return pacienteNuevo;
	}
	public void setPacienteNuevo(Paciente pacienteNuevo) {
		this.pacienteNuevo = pacienteNuevo;
	}
	public Paciente getPacienteEditar() {
		return pacienteEditar;
	}
	public void setPacienteEditar(Paciente pacienteEditar) {
		this.pacienteEditar = pacienteEditar;
	}
	
	@PostConstruct
	public void init(){
		pacientes=pacienteService.getPacientes();
	}
	
	public void editarEvent(){
		if(getPacienteNuevo()!=null){
			setPacienteEditar(pacienteService.getPacienteById(getPacienteNuevo().getIdPaciente()));
			RequestContext.getCurrentInstance().execute("dialogEditar.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un paciente");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}
	
	public void limpiarForms(){
		paciente=new Paciente();
		pacienteNuevo=new Paciente();
		pacienteEditar=new Paciente();
	}
	public void refrescarPacientes(){
		setPacientes(pacienteService.getPacientes());
	}
	
	public void insertar(){
		pacienteService.addPaciente(getPaciente());
		refrescarPacientes();
		limpiarForms();
	}
	
	public void editar(){
		pacienteService.updatePaciente(getPacienteEditar());
		refrescarPacientes();
		limpiarForms();
	}
	
	public void eliminar(){
		pacienteService.deletePacienteLogico(getPacienteNuevo());
		refrescarPacientes();
		limpiarForms();
	}
	
	public void validarNuevo(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
          
        if(paciente.getNombres().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Nombre de Paciente");  
        }
        else if(paciente.getApellidos().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Apellidos de Paciente");  
        }
        else if(paciente.getFechNac()==null)
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Fecha de Nacimiento");  
        }
        else if(pacienteEditar.getHistClinica().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Codigo de Historia Clinica de Paciente");  
        }
        else
        {
        	registrado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Paciente "+paciente.getNombres());
            
            try{
            	insertar();
            	refrescarPacientes();
            	RequestContext.getCurrentInstance().execute("PF('dialogNuevo').hide()");
            }catch(ConstraintViolationException e)
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
        
        if(pacienteNuevo==null){
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un paciente");  
        }
        else if(pacienteEditar.getNombres().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Nombre de Paciente");  
        }
        else if(pacienteEditar.getApellidos().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Apellidos de Paciente");  
        }
        else if(pacienteEditar.getHistClinica().equals(""))
        {
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Codigo de Historia Clinica de Paciente");  
        }
        else
        {
        	editado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", "Paciente "+pacienteEditar.getNombres());
            editar();
            refrescarPacientes();
            RequestContext.getCurrentInstance().execute("PF('dialogEditar').hide()");
        }
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("editado", editado);  
    }  
	
	public void validarEliminar(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean eliminado = false;  
          
        if(pacienteNuevo!=null) {  
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Paciente "+pacienteNuevo.getNombres());
            eliminar();
            refrescarPacientes();
        } else {  
        	eliminado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un paciente");  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("eliminado", eliminado);  
    }  
	
	
}