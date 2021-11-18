package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.model.Curso;

@RestController
public class CursosController {
	private List<Curso> cursos;
	@PostConstruct
	public void init() {
		cursos=new ArrayList<>();
		cursos.add(new Curso("Spring",25,"tarde"));
		cursos.add(new Curso("Spring boot",20,"tarde"));
		cursos.add(new Curso("Python",30,"tarde"));
		cursos.add(new Curso("Java EE",50,"fin de semana"));
		cursos.add(new Curso("Java básico",30,"ma�ana"));
	}
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)  //En google a través del navegador solo podemos ver peticiones tipo GET
	public List<Curso> getCursos(){
		return cursos;
	}
	
	@GetMapping(value="curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso getCurso() {
		return new Curso("Java",100,"ma�ana");
	}
	@GetMapping(value="cursos/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscarCursos(@PathVariable("name") String nombre){
		List<Curso> aux=new ArrayList<>();
		for(Curso c:cursos) {
			if(c.getNombre().contains(nombre)) {
				aux.add(c);
			}
		}
		return aux;
	}
	
	@DeleteMapping(value="curso/{name}")
	public void eliminarCurso(@PathVariable("name") String nombre) {
		//elimina de la colecci�n los elementos 
		//que cumplen la condici�n
		cursos.removeIf(c->c.getNombre().equals(nombre)); //Se eliminará en el caso de que sea true al comparar el nombre del método
	}
	@PostMapping(value="curso",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE )
	public List<Curso> altaCurso(@RequestBody Curso curso){ //@equestBody para el mappeo
		cursos.add(curso);
		return cursos;
	}
	@PutMapping(value="curso",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE ) //consumes para el tipo de contenido que va a llevar en el cuerpo
	public List<Curso> actualizaCurso(@RequestBody Curso curso){
		//recorre los cursos y sustituye aquel que 
		//coincida con el nombre
		for(int i=0;i<cursos.size();i++) {
			if(cursos.get(i).getNombre().equals(curso.getNombre())) {
				cursos.set(i, curso); //Cambia el objeto curso que coincida, por el nuevo que lee estamos pasando
			}
		}	
		return cursos;
	}
}

//POSTMAN: GET: http://localhost8080/cursos
//PARA UN CURSO EN CONCRETO, POST: http://localhost8080/curso Y EN EL BODY (RAW Y JSON) Y PEGAMOS AHÍ EL NUEVO CURSO QUE QUEREMOS DAR DE ALTA Y ABAJO NOS SALE LAS LISTA CON LA ACTUALIZACIÓN
//Análogamente con PUT