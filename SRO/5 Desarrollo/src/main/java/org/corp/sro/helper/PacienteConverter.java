package org.corp.sro.helper;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.corp.sro.domain.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesConverter("pacienteConverter")
public class PacienteConverter implements Converter{

	private static final Logger LOG = LoggerFactory.getLogger(PacienteConverter.class);
	 
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
		
	   LOG.trace("String value: {}", value);
       
       if(value=="0"){
    	   
    	   return new Paciente("Seleccionar",false);
       }
        
        return getObjectFromUIPickListComponent(component,value);
    }
 
    @SuppressWarnings("unused")
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string="";
        LOG.trace("Object value: {}", object);
        
        
               
        if(object == null){
            string="";
        }else{
            try{
               
            	Paciente c= (Paciente)object;
            	
            	if(c.getHistClinica()!=null)
            		string = c.getHistClinica().toString();
            }catch(ClassCastException cce){
            	            	
            	return "";
            }
        }
        return string;
    }
 
    @SuppressWarnings("unchecked")
    private Paciente getObjectFromUIPickListComponent(UIComponent component, String value) {
        final List<Paciente> list;
        try{
            list = (List<Paciente>)((UISelectItems)component.getChildren().get(1)).getValue();
            Paciente motor = getObjectFromList(list,String.valueOf(value));
            
             
            return motor;
        }catch(ClassCastException cce){
        	System.out.println(cce.getMessage());
        	throw new ConverterException();
            
        }catch(NumberFormatException nfe){
        	System.out.println(nfe.getMessage());
        	throw new ConverterException();
           
        }
    }
 
    private Paciente getObjectFromList(final List<?> list, final String identifier) {
        for(final Object object:list){
            final Paciente paciente = (Paciente) object;
            if(paciente.getHistClinica().equals(identifier)){
                return paciente;
            }
        }
        return null;
    }
}
