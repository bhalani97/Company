	package com.example.demo.Controller;
	
	
	import java.util.List;
	import java.util.stream.Collectors;
	
	import javax.ws.rs.QueryParam;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
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
	@GetMapping("student")
	@ResponseBody
	public ResponseEntity<List<Students>> getByLocAndTech(@QueryParam("location")String location,@QueryParam("tech")String tech){
		System.out.println(location);
		System.out.println(tech);
		List<Students> list = repo.findAll();
		if(list.isEmpty()) {
			return new ResponseEntity<List<Students>>(HttpStatus.NOT_FOUND);
		}
		
		if(!location.isEmpty() && !tech.isEmpty()) {
			List<Students> ans = list.stream().filter(st-> st.getLoc().equals(location) && st.getTech().equals(tech)).collect(Collectors.toList());
			System.out.println(ans.toString());
			if(!ans.isEmpty()) {
				return new ResponseEntity<List<Students>>(ans,HttpStatus.FOUND);	
			}
		
		}
		System.out.println(list.toString());
		return new ResponseEntity<List<Students>>(list,HttpStatus.FOUND);
		
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
