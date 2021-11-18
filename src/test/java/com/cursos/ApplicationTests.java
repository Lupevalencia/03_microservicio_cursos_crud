package com.cursos;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;   //Cuando no se trata de una petición GET desde squí podemos hacer la prueba de cualquiera de los recursos del servicio
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc; //para la autoconfiguración
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) //para realizar las pruebas con un orden determinado en la ejecución de los métodos
class ApplicationTests {
	
	@Autowired
	MockMvc mock; //objeto para realizar las peticiones y poder comprobar los resultados

	@Test
	@Order(0)
	void eliminarCurso()throws Exception {
		mock.perform(delete("/curso/Python")); //Sobre esta prueba no se va a
	}
	
	@Test
	@Order(1)
	void testCursos() throws Exception{ //para no meter un try catch
		mock.perform(get("/cursos")).andDo(print()); //método estático get con la url de lo que queremos probar y después nos escribe el resultado en la consola al arrancar
	}
	@Test
	@Order(2)
	void testAlta() throws Exception{
		mock.perform(post("/curso")
				.contentType(MediaType.APPLICATION_JSON) //el tipo Json
				.content("{\"nombre\":\"Angular 10\",\"duracion\":40,\"horario\":\"tarde\"}") //El objeto JSON
				).andDo(print());
	}
	@Test
	@Order(3)
	void testActualizacion() throws Exception{ //Igual al anterior pero modificado
		mock.perform(put("/curso")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nombre\":\"Angular 10\",\"duracion\":80,\"horario\":\"mañana\"}")
				).andDo(print());
	}

}
