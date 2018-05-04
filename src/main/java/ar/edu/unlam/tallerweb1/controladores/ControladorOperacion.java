package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorOperacion {

	@RequestMapping("operacion/{numero1}/{operacion}/{numero2}")
	public ModelAndView operacion(@PathVariable("numero1") Integer numero1,
								  @PathVariable("operacion") String operacion,
								  @PathVariable("numero2") Integer numero2){
			
		ModelMap modelo = new ModelMap();
		modelo.put("numero1", numero1);
		modelo.put("operacion", operacion);
		modelo.put("numero2", numero2);
		
		if(operacion.equals("sumar")){
			return new ModelAndView("operacionExitosa", modelo);
		}
		
		return new ModelAndView("operacionInvalida", modelo);	
	}
}
