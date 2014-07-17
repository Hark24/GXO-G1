package org.corp.sro.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.corp.sro.domain.Paciente;
import org.corp.sro.domain.Procedimiento;
import org.corp.sro.domain.Profesional;
import org.corp.sro.domain.Reserva;
import org.corp.sro.domain.Sala;
import org.corp.sro.service.IPacienteService;
import org.corp.sro.service.IProcedimientoService;
import org.corp.sro.service.IProfesionalService;
import org.corp.sro.service.IReservaService;
import org.corp.sro.service.ISalaService;

@ManagedBean (name="ReservaBean")
@ViewScoped
public class ReservaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty (value="#{ReservaService}")
	IReservaService reservaService;
	
	@ManagedProperty (value="#{PacienteService}")
	IPacienteService pacienteService;
	
	@ManagedProperty (value="#{ProcedimientoService}")
	IProcedimientoService procedimientoService;
	
	@ManagedProperty (value="#{ProfesionalService}")
	IProfesionalService profesionalService;
	
	@ManagedProperty (value="#{SalaService}")
	ISalaService salaService;
	
	List<Reserva> reservas;
	List<Procedimiento> procedimientos;
	List<Paciente> pacientes;
	List<Profesional> profesionals;
	List<Profesional> anestesiologos;
	List<Profesional> cirujanos;
	List<Profesional> instrumentistas;
	List<Sala> salas;

    private DispoBean dispoBean;
	
	int durHoras=1,durMin,durEstHoras,durEstMin;
	int durHorasEditar=1,durMinEditar,durEstHorasEditar,durEstMinEditar;
	
	Date fecha;
	boolean reprogramar=true;
	
	Profesional profesionalTemp= new Profesional();
	
	Reserva reserva=new Reserva();
	Reserva reservaNuevo=new Reserva();
	Reserva reservaEditar=new Reserva();
	
	Reserva detalle = new Reserva();

    private boolean habilitarReprogramar=true;

    @ManagedProperty(value="#{PacienteBean}")
	PacienteBean pacBean = new PacienteBean();

	public void setPacBean(PacienteBean p)
	{
		pacBean = p;
	}
	
	public PacienteBean getPacBean()
	{
		return pacBean;
	}
	
	@ManagedProperty(value="#{ProfesionalBean}")
	ProfesionalBean profBean = new ProfesionalBean();

	public void setProfBean(ProfesionalBean p)
	{
		profBean = p;
	}
		
	public ProfesionalBean getProfBean()
	{
		return profBean;
	}
	
	@ManagedProperty(value="#{SalaBean}")
	SalaBean salaBean = new SalaBean();

	public void setSalaBean(SalaBean p)
	{
		salaBean = p;
	}
		
	public SalaBean getSalaBean()
	{
		return salaBean;
	}
	
	@ManagedProperty(value="#{ProcedimientoBean}")
	ProcedimientoBean procBean = new ProcedimientoBean();

	public void setProcBean(ProcedimientoBean p)
	{
		procBean = p;
	}
		
	public ProcedimientoBean getProcBean()
	{
		return procBean;
	}
	
    public DispoBean getDispoBean() {
        return dispoBean;
    }

    public void setDispoBean(DispoBean dispoBean) {
        this.dispoBean = dispoBean;
    }

    public boolean isHabilitarReprogramar() {
        return habilitarReprogramar;
    }

    public void setHabilitarReprogramar(boolean habilitarReprogramar) {
        this.habilitarReprogramar = habilitarReprogramar;
    }

    public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getDurHoras() {
		return durHoras;
	}
	public void setDurHoras(int durHoras) {
		this.durHoras = durHoras;
	}
	public int getDurMin() {
		return durMin;
	}
	public void setDurMin(int durMin) {
		this.durMin = durMin;
	}
	public int getDurEstHoras() {
		return durEstHoras;
	}
	public void setDurEstHoras(int durEstHoras) {
		this.durEstHoras = durEstHoras;
	}
	public int getDurEstMin() {
		return durEstMin;
	}
	public void setDurEstMin(int durEstMin) {
		this.durEstMin = durEstMin;
	}
	public int getDurHorasEditar() {
		return durHorasEditar;
	}
	public void setDurHorasEditar(int durHorasEditar) {
		this.durHorasEditar = durHorasEditar;
	}
	public int getDurMinEditar() {
		return durMinEditar;
	}
	public void setDurMinEditar(int durMinEditar) {
		this.durMinEditar = durMinEditar;
	}
	public int getDurEstHorasEditar() {
		return durEstHorasEditar;
	}
	public void setDurEstHorasEditar(int durEstHorasEditar) {
		this.durEstHorasEditar = durEstHorasEditar;
	}
	public int getDurEstMinEditar() {
		return durEstMinEditar;
	}
	public void setDurEstMinEditar(int durEstMinEditar) {
		this.durEstMinEditar = durEstMinEditar;
	}
	public Profesional getProfesionalTemp() {
		return profesionalTemp;
	}
	public void setProfesionalTemp(Profesional profesionalTemp) {
		this.profesionalTemp = profesionalTemp;
	}
	public IReservaService getReservaService() {
		return reservaService;
	}
	public void setReservaService(IReservaService reservaService) {
		this.reservaService = reservaService;
	}
	public IPacienteService getPacienteService() {
		return pacienteService;
	}
	public void setPacienteService(IPacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}
	public IProcedimientoService getProcedimientoService() {
		return procedimientoService;
	}
	public void setProcedimientoService(IProcedimientoService procedimientoService) {
		this.procedimientoService = procedimientoService;
	}
	public IProfesionalService getProfesionalService() {
		return profesionalService;
	}
	public void setProfesionalService(IProfesionalService profesionalService) {
		this.profesionalService = profesionalService;
	}
	public ISalaService getSalaService() {
		return salaService;
	}
	public void setSalaService(ISalaService salaService) {
		this.salaService = salaService;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public List<Procedimiento> getProcedimientos() {
		return procedimientos;
	}
	public void setProcedimientos(List<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	public List<Profesional> getProfesionals() {
		return profesionals;
	}
	public void setProfesionals(List<Profesional> profesionals) {
		this.profesionals = profesionals;
	}
	public List<Profesional> getAnestesiologos() {
		return anestesiologos;
	}
	public void setAnestesiologos(List<Profesional> anestesiologos) {
		this.anestesiologos = anestesiologos;
	}
	public List<Profesional> getCirujanos() {
		return cirujanos;
	}
	public void setCirujanos(List<Profesional> cirujanos) {
		this.cirujanos = cirujanos;
	}
	public List<Profesional> getInstrumentistas() {
		return instrumentistas;
	}
	public void setInstrumentistas(List<Profesional> instrumentistas) {
		this.instrumentistas = instrumentistas;
	}
	public List<Sala> getSalas() {
		return salas;
	}
	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public Reserva getReservaNuevo() {
		return reservaNuevo;
	}
	public void setReservaNuevo(Reserva reservaNuevo) {
		this.reservaNuevo = reservaNuevo;
	}
	public Reserva getReservaEditar() {
		return reservaEditar;
	}
	public void setReservaEditar(Reserva reservaEditar) {
		this.reservaEditar = reservaEditar;
	}
	
	public boolean verificarReserva(Reserva r)
	{
		Reserva res=null;
		
		Calendar f=Calendar.getInstance();
	
		f.setTime(r.getFechaIni());
		
		f.add(Calendar.HOUR, durEstHoras);
		f.add(Calendar.MINUTE, durEstMin);
		
		r.setFechaFin(f.getTime());

		for(int i=0; i<reservas.size();i++){
			res = reservas.get(i);
			if(res.getSala().getIdSala().equals(r.getSala().getIdSala()))
			{
				if(res.getFechaIni().getDate() == r.getFechaIni().getDate())
				{
					if(
							(r.getFechaIni().compareTo(res.getFechaFin())<0 && r.getFechaIni().compareTo(res.getFechaIni())>=0)
							
							||
							
							(r.getFechaFin().compareTo(res.getFechaFin())<=0 && r.getFechaFin().compareTo(res.getFechaIni())>0)
							
							||
							
							(r.getFechaIni().compareTo(res.getFechaIni())<=0 && r.getFechaFin().compareTo(res.getFechaFin())>=0)
							
					)
					{
						if(!reserva.equals(res))
						return false;
					}
				}
			}
			
		}

		return true;
	}
	
	@PostConstruct
	public void init(){
		fecha=new Date();
		reservas=reservaService.getReservasByDay(fecha);
		pacientes=pacienteService.getPacientes();
		procedimientos=procedimientoService.getProcedimientos();
		profesionals=profesionalService.getProfesionals();
		anestesiologos=profesionalService.getAnestesiologos();
		cirujanos=profesionalService.getCirujanos();
		instrumentistas=profesionalService.getInstrumentistas();
		salas=salaService.getSalas();
	}
	
	//public void processPaciente(AjaxBehaviorEvent e)
	
	public void limpiarForms(){
		reserva=new Reserva();
		reservaNuevo=new Reserva();
		reservaEditar=new Reserva();
		durHoras=0;durMin=0;durEstHoras=0;durEstMin=0;
		durHorasEditar=0;durMinEditar=0;durEstHorasEditar=0;durEstMinEditar=0;
	}

	public void editarEvent(){
		try{
			if(getReservaNuevo()!=null){
				setReservaEditar(reservaService.getReservaById(getReservaNuevo().getIdReserva()));
				durEstHorasEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionEst().getHours();
				durEstMinEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionEst().getMinutes();
				durHorasEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionReal().getHours();
				durMinEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionReal().getMinutes();
				RequestContext.getCurrentInstance().execute("PF('dialogEditar').show()");
			}
			else{
				FacesMessage msg = null;  
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione una reserva");  
				FacesContext.getCurrentInstance().addMessage(null, msg);  
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	public void eliminarEvent(){
		if(getReservaNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione una reserva");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}
	
	public void refrescarReservas(){
		setReservas(reservaService.getReservasByDay(fecha));
	}
	public void refrescarByDay(SelectEvent event){
		setReservas(reservaService.getReservasByDay(fecha));
	}
	public void reservasTotales(){
		setReservas(reservaService.getReservas());
	}

	public void editarReprogramacion(){
		try{
			reprogramar=true;
			if(getReservaNuevo()!=null){
				setReservaEditar(reservaService.getReservaById(getReservaNuevo().getIdReserva()));
				durEstHorasEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionEst().getHours();
				durEstMinEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionEst().getMinutes();
				//durHorasEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionReal().getHours();
				//durMinEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionReal().getMinutes();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void procesarEditarReprogramar(){

		if(getReservaEditar().getFechaIni().compareTo(getReservaNuevo().getFechaIni())==0){
			reprogramar = false;
			System.out.println("\n\n\neditar");
		}
		else{
			reprogramar = true;
			System.out.println("\n\n\nreprogramar");
		}

		if(reprogramar){
			setReserva(getReservaEditar());
			if(getReservaNuevo().getReserva()==null){
				getReserva().setReserva(getReservaNuevo());
			}else{
				getReserva().setReserva(getReservaNuevo().getReserva());
			}
			getReserva().setVigente(true);
			
			durEstHoras=durEstHorasEditar;
			durEstMin=durEstMinEditar;
			durHoras=durHorasEditar;
			durMin=durMinEditar;
			
			
			setReservaEditar(reservaService.getReservaById(getReservaNuevo().getIdReserva()));
			getReservaEditar().setVigente(false);
			durEstHorasEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionEst().getHours();
			durEstMinEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionEst().getMinutes();
			//durHorasEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionReal().getHours();
			//durMinEditar=reservaService.getReservaById(getReservaNuevo().getIdReserva()).getDuracionReal().getMinutes();
			
			insertar();
			editar();
			RequestContext.getCurrentInstance().execute("PF('dialogEditar').hide();");
		}else{
			validarEditar();
		}
        if(dispoBean==null){
            refrescarReservas();
        }else{
            dispoBean.refrescarReservasVigentes();

        }
	}
	public void insertar(){
		
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean insertado = false;  
        
        try{
        	if(reserva.getSala() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Sala");  
	        }        	
        	else if (reserva.getFechaIni()==null){
        		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Fecha de Reserva");  
        	
        	}
        	else if(reserva.getProcedimiento() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Procedimiento");  
	        }
	        else if(reserva.getPaciente() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Paciente");  
	        }
	        else if(reserva.getProfesionalByProfesionalIdProfesional() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Instrumentista");  
	        }
	        else if(reserva.getProfesionalByProfesionalIdProfesional1() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Cirujano");  
	        }
	        else if(reserva.getProfesionalByProfesionalIdProfesional2()== null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Anestesiólogo");  
	        }
	        else if(verificarReserva(reserva)==false)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Sala Ocupada");  
	        }
	        else
	        {
	        	insertado=true;
	          
				Calendar f=Calendar.getInstance();
				f.set(reserva.getFechaIni().getYear(), reserva.getFechaIni().getMonth(), reserva.getFechaIni().getDate()
						, durEstHoras, durEstMin);
				reserva.setDuracionEst(f.getTime());
				//System.out.println(f.getTime());
				
				f.set(reserva.getFechaIni().getYear(), reserva.getFechaIni().getMonth(), reserva.getFechaIni().getDate()
						, durHoras, durMin);
				reserva.setDuracionReal(f.getTime());
				
				f.setTime(reserva.getFechaIni());
				//System.out.println(f.getTime());
				f.add(Calendar.HOUR, durEstHoras);
				f.add(Calendar.MINUTE, durEstMin);
				reserva.setFechaFin(f.getTime());
				//System.out.println(f.getTime());
				if(getReserva().getIdReserva()==null)
					System.out.println("Si es nulo");
				
				reservaService.addReserva(getReserva());
				RequestContext.getCurrentInstance().execute("PF('dialogNuevo').hide();");
				
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Reserva Insertada");
				
	        }   
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudo registrar reserva");  
        }
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("editado", insertado); 
	}

    public void procesarInsertar()
    {
        insertar();
       
        if(dispoBean==null){
            refrescarReservas();
        }
        else{
            dispoBean.refrescarReservasVigentes();
        }
    }
	public void editar(){
		Calendar f=Calendar.getInstance();
		f.set(reservaEditar.getFechaIni().getYear(), reservaEditar.getFechaIni().getMonth(), reservaEditar.getFechaIni().getDate()
				, durEstHorasEditar, durEstMinEditar);
		reservaEditar.setDuracionEst(f.getTime());
		System.out.println(f.getTime());
		
		f.set(reservaEditar.getFechaIni().getYear(), reservaEditar.getFechaIni().getMonth(), reservaEditar.getFechaIni().getDate()
				, durHorasEditar, durMinEditar);
		reservaEditar.setDuracionReal(f.getTime());
		
		f.setTime(reservaEditar.getFechaIni());
		System.out.println(f.getTime());
		f.add(Calendar.HOUR, durEstHorasEditar);
		f.add(Calendar.MINUTE, durEstMinEditar);
		reservaEditar.setFechaFin(f.getTime());
		System.out.println(f.getTime());
		
		getReservaEditar().setIdReserva(getReservaNuevo().getIdReserva());
		reservaService.updateReserva(getReservaEditar());
		setDetalle(reservaEditar);
		limpiarForms();

	}
	public void eliminar(){
		reservaService.deleteReservaLogico(getReservaNuevo());
        if(dispoBean==null){
            refrescarReservas();
        }else{
            dispoBean.refrescarReservasVigentes();

        }
	}

    public void onRowSelect(AjaxBehaviorEvent e){
        try{
            DataTable ui = (DataTable)e.getSource();

            Reserva p =(Reserva)(ui.getSelection()) ;
            
            setDetalle(p);
            System.out.print(detalle.getIdReserva());
            System.out.print(detalle.getCompania());
            System.out.print(detalle.getPaciente().getNombres());
            System.out.print(detalle.getFechAviso());
            System.out.print(detalle);
            
            habilitarReprogramar = !p.isVigente();
            
        } catch(Exception event){
            System.out.println("error");
        }
    }

	public Reserva getDetalle() {
		return detalle;
	}

	public void setDetalle(Reserva det) {
		detalle = det;
	}
	 
	 public void validarEditar() {  
	        RequestContext context = RequestContext.getCurrentInstance();  
	        FacesMessage msg = null;  
	        boolean editado = false;  
	    
	    try{

	        if(reservaNuevo==null){
	            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un profesional");  
	        }

	        else if(reservaEditar.getSala() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Sala");  
	        }
	        else if(reservaEditar.getProcedimiento() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Procedimiento");  
	        }
	        else if(reservaEditar.getPaciente() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Paciente");  
	        }
	        else if(reservaEditar.getProfesionalByProfesionalIdProfesional() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Instrumentista");  
	        }
	        else if(reservaEditar.getProfesionalByProfesionalIdProfesional1() == null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Cirujano");  
	        }
	        else if(reservaEditar.getProfesionalByProfesionalIdProfesional2()== null)
	        {
	        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese Anestesiólogo");  
	        }
	        else
	        {
	        	editado = true;  
	        	
	        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Reserva Editada");
	        	RequestContext.getCurrentInstance().execute("PF('dialogEditar').hide();");
	            editar();
	        }
	    }
	    catch(Exception e)
	    {
	    	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese correctamente los datos");  
	    }

	        
	    FacesContext.getCurrentInstance().addMessage(null, msg);  
	    context.addCallbackParam("editado", editado);  
	}  
		
		public void validarEliminar(ActionEvent actionEvent) {  
	        RequestContext context = RequestContext.getCurrentInstance();  
	        FacesMessage msg = null;  
	        boolean eliminado = false;  
	        
	        if(reservaNuevo==null){
	            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione una reserva");  
	        }
	        else
	        {
	        	eliminado = true;  
	        	
	        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Reserva Eliminada");
	        	
	        	eliminar();
	        }
	          
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	        context.addCallbackParam("eliminado", eliminado);  
	    }  
		
		public void actualizarInsertarPaciente(ActionEvent actionEvent){
			pacBean.validarNuevo(actionEvent);
			init();
		}
		
		public void actualizarInsertarSala(ActionEvent actionEvent){
			salaBean.validarNuevo(actionEvent);
			init();
		}
		
		public void actualizarInsertarProc(ActionEvent actionEvent){
			procBean.validarNuevo(actionEvent);
			init();
		}
		
		public void actualizarInsertarProf(ActionEvent actionEvent){
			profBean.validarNuevo(actionEvent);
			init();
		}
}
