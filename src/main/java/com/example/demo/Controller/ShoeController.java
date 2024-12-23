package com.example.demo.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Shoe;
import com.example.demo.Entity.Users;
import com.example.demo.Enum.Role;
import com.example.demo.Service.ShoeService;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api/shoes")
public class ShoeController {
	           
    @Autowired
    private ShoeService shoeService;
    
    @Autowired
    private UserService userService;
    
    public String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/images";
    
    @PostMapping("/addShoe")
    public Shoe saveShoe(@RequestParam("name") String shoeName,
                         @RequestParam("price") Double shoePrice,
                         @RequestParam("size") String shoeSize,
                         @RequestParam("shoeImage") MultipartFile file,
                         @RequestParam("quantity") int quantity,
                         @RequestHeader("Authorization") String jwt) throws Exception {
        
        Users authUser = userService.findUserprofileByJwt(jwt);
	    if(!authUser.getRole().equals(Role.ADMIN)) throw new Exception("admin can only access this route");
    	
    	
        // Handle file upload
        String originalFilename = file.getOriginalFilename();
       
        String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;
       
        Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
        
        // Save the file
        Files.write(fileNameAndPath, file.getBytes());
        
        // Create Shoe object and set values
        Shoe shoe = new Shoe();
        shoe.setName(shoeName);
        shoe.setPrice(shoePrice);
        shoe.setSize(shoeSize);
        shoe.setQuantity(quantity);
        shoe.setShoeImage(uniqueFilename); 

        Shoe addedShoe = shoeService.addShoe(shoe);
        
        return addedShoe;
    };
    
    @GetMapping("/getAllShoe")
    public List<Shoe> getAllShoe( ) throws Exception{

    	return shoeService.FindAll();
    }
    
    @DeleteMapping("/delete/{shoeId}")
    public ResponseEntity<String> deleteShoe(@PathVariable(name="shoeId") Long shoeId, @RequestHeader("Authorization") String jwt) throws Exception {
        Users authUser = userService.findUserprofileByJwt(jwt);
	    if(!authUser.getRole().equals(Role.ADMIN)) throw new Exception("admin can only access this route");
    	shoeService.deleteShoeById(shoeId);
    	return new ResponseEntity<>("shoe is deleted",HttpStatus.OK);
    }
    
    
    @PutMapping("/update/{shoeId}")
    public ResponseEntity<Shoe> UpdateShoe(@PathVariable(name="shoeId") Long shoeId,
    										 @RequestParam("name") String shoeName,
    										 @RequestParam("price") Double shoePrice,
    										 @RequestParam("size") String shoeSize,
    										 @RequestParam("shoeImage") MultipartFile file,
    										 @RequestParam("quantity") int quantity,
    										 @RequestHeader("Authorization") String jwt) throws Exception {
        Users authUser = userService.findUserprofileByJwt(jwt);
	    if(!authUser.getRole().equals(Role.ADMIN)) throw new Exception("admin can only access this route");
    	Shoe shoe=shoeService.findShoeById(shoeId);
    	String originalFilename = file.getOriginalFilename();
        String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;
        Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);
        
        // Save the file
        Files.write(fileNameAndPath, file.getBytes());
      
        shoe.setName(shoeName);
        shoe.setPrice(shoePrice);
        shoe.setSize(shoeSize);
        shoe.setQuantity(quantity);

        shoe.setShoeImage(uniqueFilename); 

        Shoe updatedShoe = shoeService.addShoe(shoe);
    	return new ResponseEntity<>(shoe,HttpStatus.OK);
    }
}
