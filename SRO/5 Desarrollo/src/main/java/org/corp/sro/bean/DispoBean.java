package org.corp.sro.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.corp.sro.domain.Reserva;
import org.corp.sro.service.IReservaService;

@ManagedBean(name="DispoBean")
@ViewScoped
public class DispoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;


    @ManagedProperty("#{ReservaBean}")
    ReservaBean reservaBean;

	private ScheduleModel eventModel;
    private String lastCss;
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    List<Reserva> reservas;

    public ReservaBean getReservaBean() {
        return reservaBean;
    }

    public void setReservaBean(ReservaBean reservaBean) {
        this.reservaBean = reservaBean;
    }

    public ScheduleModel getEventModel() {
		return eventModel;
	}
	
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}
	
	public DispoBean(){
		
	}

    public IReservaService getReservaService() {
        return reservaBean.getReservaService();
    }

    public void setReservaService(IReservaService reservaService) {
        this.reservaBean.setReservaService(reservaService);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @PostConstruct
	public void init(){	
		eventModel = new DefaultScheduleModel();
        refrescarReservasVigentes();
        reservaBean.setDispoBean(this);
		/*eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm(),"emp1"));
        eventModel.addEvent(new DefaultScheduleEvent("1", today1Pm(), today6Pm(),"emp1"));
        eventModel.addEvent(new DefaultScheduleEvent("2", today1Pm(), today6Pm(),"emp2"));  
        eventModel.addEvent(new DefaultScheduleEvent("3", today1Pm(), today6Pm(),"emp3"));
        eventModel.addEvent(new DefaultScheduleEvent("4", today1Pm(), today6Pm(),"emp4"));
        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am(),"emp1"));
                                                                                                                       */
		
	}

    public void refrescarReservasVigentes()
    {
        eventModel = new DefaultScheduleModel();
        reservaBean.setReservas(reservaBean.getReservaService().getReservasVigente());

        for(Reserva res:reservaBean.getReservas()){
            String temp = "Sala "+res.getSala().getCodSala().toString()+"\n"+res.getProcedimiento().getNombre();
            
            Integer t= res.getSala().getIdSala()%5;
            DefaultScheduleEvent dse = new DefaultScheduleEvent(temp,
                    res.getFechaIni(),res.getFechaFin(),"sala" + t.toString() );
            dse.setData(res);           
            eventModel.addEvent(dse);
        }
    }
	
	public void onDateSelect(SelectEvent selectEvent)
	{
		
	}
	
	public void onEventSelect(SelectEvent selectEvent)
	{

        if(lastCss!="" && lastCss!=null){
            ((DefaultScheduleEvent) event).setStyleClass(lastCss);
        }

        event = (ScheduleEvent)selectEvent.getObject();
        lastCss=event.getStyleClass();
        ((DefaultScheduleEvent) event).setStyleClass("select");

        reservaBean.setReservaNuevo((Reserva)event.getData());

    }
	
	
	 private Calendar today() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }  
      

     public TimeZone getTimeZone(){
    	 return Calendar.getInstance().getTimeZone();
     }

      


}
