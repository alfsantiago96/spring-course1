package com.educandoweb.course.resources;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController //Informa que esta classe é um Recurso web a qual é implementada por um controlador Rest
@RequestMapping(value = "/categories") //Nome do recurso
public class CategoryResources {

    @Autowired
    private CategoryService categoryService;

    //Endpoints do controllador Rest

    @GetMapping //Indica que o metodo responte a requisição do tipo Get do Http
    public ResponseEntity<List<Category>> findAll(){ //ResponseEntity é um tipo especifico do Sping que retorna as respostas de requisições web.
        List<Category> list = categoryService.findAll();
        return ResponseEntity.ok().body(list); //".ok()" = retorna resposta com sucesso no http, ".body" retorna o corpo da resposta
    }

    @GetMapping(value = "/{id}") //Indica que a requisição http vai indicar o endereço de ID
    public ResponseEntity<Category> findById(@PathVariable Long id) { //@PathVariable = Para que o Spring considere o /id http/url como parametro
        Category obj = categoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
