package com.example.demo.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Shoe;
import com.example.demo.Repository.ShoeRepository;

@Service
public class ShoeService {
									// root dir+
	  public String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/images";

	@Autowired
	private ShoeRepository shoeRepository;

	public Shoe addShoe(Shoe shoe) {
		
	return 	shoeRepository.save(shoe);
	}

	public List<Shoe> FindAll() {
		
		return shoeRepository.findAll();
	}

	public Shoe findShoeById(Long shoeId) throws Exception {
		Shoe shoe= shoeRepository.findById(shoeId).orElse(null);
		if(shoe==null) throw new Exception("Cannot found shoe by id");
		 return null;
		
	}

	public void deleteShoeById(Long shoeId) {
		shoeRepository.deleteById(shoeId);
		
	}

	
	
	
	

	
	
	
	
}
