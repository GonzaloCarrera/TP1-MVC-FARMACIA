package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorOperacion {

	@RequestMapping("operacion/{numero1}/{operacion}/{numero2}")
	public ModelAndView operacion(@PathVariable("numero1") Double numero1,
								  @PathVariable("operacion") String operacion,
								  @PathVariable("numero2") Double numero2){
			
		ModelMap modelo = new ModelMap();
		modelo.put("numero1", numero1);
		modelo.put("operacion", operacion.toLowerCase());
		modelo.put("numero2", numero2);
		Double resultado=0.0;
		
		switch(operacion.toLowerCase()){
		case "suma":
			resultado = numero1+numero2;
			modelo.put("resultado", resultado);
			return new ModelAndView("operacionExitosa", modelo);
		case "resta":
			resultado = numero1-numero2;
			modelo.put("resultado", resultado);
			return new ModelAndView("operacionExitosa", modelo);
		case "multiplicacion":
			resultado = numero1*numero2;
			modelo.put("resultado", resultado);
			return new ModelAndView("operacionExitosa", modelo);
		case "division":
			if(numero2!=0){
				resultado = numero1/numero2;
				modelo.put("resultado", resultado);
				return new ModelAndView("operacionExitosa", modelo);
			}
			else{
				modelo.put("operacion", "division por 0");
				return new ModelAndView("operacionInvalida", modelo);	
			}
			default:
				return new ModelAndView("operacionInvalida", modelo);	
		}	
	}
}
