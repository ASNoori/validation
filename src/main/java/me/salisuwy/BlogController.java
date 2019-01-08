package me.salisuwy;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    BlogRespository blogRespository;
   
    @GetMapping("/blog")
    public List<Blog> index(){
        return blogRespository.findAll();
    }

    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id){
   int blogId = Integer.parseInt(id);
        return blogRespository.findOne(blogId);
    }
    
   
    
    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body){
    
        String username = body.get("username");
       
        String password = body.get("password");
        return blogRespository.save(new Blog(username,password));
    }
  
    @PutMapping("/blog/{id}")
    public Blog update(@PathVariable String id, @RequestBody Map<String,String> body){
        int blogId = Integer.parseInt(id);
        // getting blog
        Blog blog = blogRespository.findOne(blogId);
        blog.setUsername(body.get("username"));
        
        blog.setPassword(body.get("password"));
        return blogRespository.save(blog);
    }
    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        blogRespository.delete(blogId);
        return true;
    }
    


}