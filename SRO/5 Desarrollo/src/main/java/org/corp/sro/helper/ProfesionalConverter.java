package org.corp.sro.helper;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.corp.sro.domain.Profesional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesConverter("profesionalConverter")
public class ProfesionalConverter implements Converter{

	private static final Logger LOG = LoggerFactory.getLogger(ProfesionalConverter.class);
	 
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
		
	   LOG.trace("String value: {}", value);
       
       if(value=="0"){
    	   
    	   return new Profesional("Seleccionar","Seleccionar","Seleccionar","Seleccionar",false);
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
               
            	Profesional c= (Profesional)object;
            	
            	if(c.getColegio()!=null)
            		string = c.getColegio().toString();
            }catch(ClassCastException cce){
            	            	
            	return "";
            }
        }
        return string;
    }
 
    @SuppressWarnings("unchecked")
    private Profesional getObjectFromUIPickListComponent(UIComponent component, String value) {
        final List<Profesional> list;
        try{
            list = (List<Profesional>)((UISelectItems)component.getChildren().get(1)).getValue();
            Profesional motor = getObjectFromList(list,value);
            
             
            return motor;
        }catch(ClassCastException cce){
        	System.out.println(cce.getMessage());
        	throw new ConverterException();
            
        }catch(NumberFormatException nfe){
        	System.out.println(nfe.getMessage());
        	throw new ConverterException();
           
        }
    }
 
    private Profesional getObjectFromList(final List<?> list, final String identifier) {
        for(final Object object:list){
            final Profesional profesional = (Profesional) object;
            if(profesional.getColegio().equals(identifier)){
                return profesional;
            }
        }
        return null;
    }
}
