package org.corp.sro.helper;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.corp.sro.domain.Procedimiento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesConverter("procedimientoConverter")
public class ProcedimientoConverter implements Converter{
	private static final Logger LOG = LoggerFactory.getLogger(ProcedimientoConverter.class);
	 
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
		
	   LOG.trace("String value: {}", value);
       
       if(value=="0"){
    	   
    	   return new Procedimiento("Seleccionar","Seleccionar",false);
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
               
            	Procedimiento c= (Procedimiento)object;
            	
            	if(c.getCie()!=null)
            		string = c.getCie().toString();
            }catch(ClassCastException cce){
            	            	
            	return "";
            }
        }
        return string;
    }
 
    @SuppressWarnings("unchecked")
    private Procedimiento getObjectFromUIPickListComponent(UIComponent component, String value) {
        final List<Procedimiento> list;
        try{
            list = (List<Procedimiento>)((UISelectItems)component.getChildren().get(1)).getValue();
            Procedimiento motor = getObjectFromList(list,value);
            
             
            return motor;
        }catch(ClassCastException cce){
        	System.out.println(cce.getMessage());
        	throw new ConverterException();
            
        }catch(NumberFormatException nfe){
        	System.out.println(nfe.getMessage());
        	throw new ConverterException();
           
        }
    }
 
    private Procedimiento getObjectFromList(final List<?> list, final String identifier) {
        for(final Object object:list){
            final Procedimiento procedimiento = (Procedimiento) object;
            if(procedimiento.getCie().equals(identifier)){
                return procedimiento;
            }
        }
        return null;
    }
}
