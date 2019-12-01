package com.example.demo;


import java.lang.annotation.Repeatable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Model.DataModel;
import com.example.demo.Model.Students;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
public class HomeController {
@Autowired
DataModel repo;
@RequestMapping("/")
@ResponseBody
public ModelAndView get() {
	ModelAndView mv = new ModelAndView();
	mv.setViewName("index.html");
	return mv;
}
@GetMapping("students")
@ResponseBody
public List<Students> getData(){
	return  repo.findAll();
}

@PostMapping("student")
@ResponseBody
public Students addData(@RequestBody Students stud) {
	repo.save(stud);
	return stud;
}

@GetMapping("student/{id}")
@ResponseBody
public java.util.Optional<Students> getStudent(@PathVariable int id) {
	java.util.Optional<Students> stud = repo.findById(id);
	return stud;
}
@PutMapping("student")
@ResponseBody
public Students saveOrUpdate(@RequestBody Students stud) {
	repo.save(stud);
	return stud;
	
}
@DeleteMapping("student/{id}")
@ResponseBody
public String deleteStudent(@PathVariable int id) {
	repo.deleteById(id);
	return "Deleted";
}
}
