package org.ruqu.siro.helper;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.ruqu.siro.domain.Sala;
import org.ruqu.siro.helper.SalaConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesConverter("salaConverter")
public class SalaConverter implements Converter{

	private static final Logger LOG = LoggerFactory.getLogger(SalaConverter.class);
	 
	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       
		
	   LOG.trace("String value: {}", value);
       
       if(value=="0"){
    	   
    	   return new Sala(false);
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
               
            	Sala c= (Sala)object;
            	
            	if(c.getIdSala()!=null)
            		string = c.getIdSala().toString();
            }catch(ClassCastException cce){
            	            	
            	return "";
            }
        }
        return string;
    }
 
    @SuppressWarnings("unchecked")
    private Sala getObjectFromUIPickListComponent(UIComponent component, String value) {
        final List<Sala> list;
        try{
            list = (List<Sala>)((UISelectItems)component.getChildren().get(1)).getValue();
            Sala motor = getObjectFromList(list,Integer.valueOf(value));
            
             
            return motor;
        }catch(ClassCastException cce){
        	System.out.println(cce.getMessage());
        	throw new ConverterException();
            
        }catch(NumberFormatException nfe){
        	System.out.println(nfe.getMessage());
        	throw new ConverterException();
           
        }
    }
 
    private Sala getObjectFromList(final List<?> list, final Integer identifier) {
        for(final Object object:list){
            final Sala sala = (Sala) object;
            if(sala.getIdSala().equals(identifier)){
                return sala;
            }
        }
        return null;
    }
}
