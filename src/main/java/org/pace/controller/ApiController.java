package org.pace.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.pace.configuration.GlobalVariables;
import org.pace.model.*;
import org.pace.service.primary.*;
import org.pace.service.secondary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.pace.custom.CustomErrorType;

@RestController
@RequestMapping(value="/api")
public class ApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	
	
	
	@Autowired
	UserInfoServicePri userInfoServicePri;
	
	
	@RequestMapping(value="/userlist" , method = RequestMethod.GET )
	public List<UserInfo>  userlist() {
		
		return userInfoServicePri.findAllUserInfo();
	}
	
	@RequestMapping(value="/userlist/{username}" , method = RequestMethod.GET )
	public UserInfo  userlist(@PathVariable("username") String username) {
		
		return userInfoServicePri.findByuserName(username);
	}
	 	
	@Autowired
	CategoryServicePri categoryServicePri;
	
	@Autowired
	CategoryServiceSec categoryServiceSec;
	
	@RequestMapping(value="/categorylist" , method = RequestMethod.GET )
	public List<Category>  category() {
		
		return categoryServicePri.findAllCategory();
	}
	
	@RequestMapping(value="/singlecategory/{id}" , method = RequestMethod.GET )
	public Category  category(@PathVariable("id") int id) {
		
		return categoryServicePri.findBycategoryId(id);
	}
	
	@RequestMapping(value="/createcategory" ,  method = RequestMethod.POST)
	public ResponseEntity<?> createCategory( @RequestBody Category category ,UriComponentsBuilder ucBuilder) {
		category.setFlagStatus(0);
		category.setCreatedUsercode(1);				 
		logger.info("Creating Category	: {} ",category);
		categoryServicePri.saveCategory(category);
		
		if(GlobalVariables.cloudFlag) { categoryServiceSec.saveCategory(category);}
			
		HttpHeaders headers = new HttpHeaders();
	//	headers.setLocation(ucBuilder.path("/api/userlist/{username}").buildAndExpand(user.getUsername()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/updatecategory/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
		
		 logger.info("Updating category with id {}", id);
		 
		 Category currentCategory = categoryServicePri.findBycategoryId(id);
		 
	     if (currentCategory == null) {
	          logger.error("Unable to update. category with id {} not found.", id);
	          return new ResponseEntity(new CustomErrorType("Unable to upate. category with id " + id + " not found."),HttpStatus.NOT_FOUND);
	     }
	 
	     currentCategory.setCategoryName(category.getCategoryName());
	     currentCategory.setFlagStatus(1);	  	
	     currentCategory.setSendFlag(0);	  	
	     currentCategory.setModifiedUsercode(1);
	     categoryServicePri.updateCategory(currentCategory);
	     if(GlobalVariables.cloudFlag) { categoryServiceSec.updateCategory(currentCategory);	}
	        
	     return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
	}
	
	@RequestMapping(value="/removecategory/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id ) {
		
		 logger.info("Deleting category with id {}", id);
		 
		 Category currentCategory = categoryServicePri.findBycategoryId(id);
		 
	     if (currentCategory == null) {
	          logger.error("Unable to delete. category with id {} not found.", id);
	          return new ResponseEntity(new CustomErrorType("Unable to upate. category with id " + id + " not found."),HttpStatus.NOT_FOUND);
	     }
	 
	     currentCategory.setFlagStatus(2);	  	
	     currentCategory.setSendFlag(0);	  	
	     currentCategory.setModifiedUsercode(1);
	     categoryServicePri.updateCategory(currentCategory);
	     if(GlobalVariables.cloudFlag) { categoryServiceSec.updateCategory(currentCategory);	}
	        
	     return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
	}
	
	@Autowired
	ItemServicePri itemServicePri;
	
	@Autowired
	ItemServiceSec itemServiceSec;
	
	@RequestMapping(value="/itemlist" , method = RequestMethod.GET )
	public List<Item>  item() {		
		return itemServicePri.findAllItem();
	}
	
	@RequestMapping(value="/singleitem/{id}" , method = RequestMethod.GET )
	public Item  item(@PathVariable("id") int id) {		
		return itemServicePri.findByitemId(id);
	}
	
	@RequestMapping(value="/createitem" ,  method = RequestMethod.POST)
	public ResponseEntity<?> createItem( @RequestBody Item item ,UriComponentsBuilder ucBuilder) {
		item.setFlagStatus(0);			
		item.setCreatedUsercode(1);	
		logger.info("Creating item	: {} ",item);
		itemServicePri.saveItem(item);
		
		if(GlobalVariables.cloudFlag) { itemServiceSec.saveItem(item);	}
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/updateitem/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<?> updateItem(@PathVariable("id") int id, @RequestBody Item item) {
		
		 logger.info("Updating Item with id {}", id);		 
		 Item currentItem = itemServicePri.findByitemId(id);
		 
	     if (currentItem == null) {
	          logger.error("Unable to update. Item with id {} not found.", id);
	          return new ResponseEntity(new CustomErrorType("Unable to update. Item with id " + id + " not found."),HttpStatus.NOT_FOUND);
	     }
	 
	     currentItem.setCategoryId(item.getCategoryId());
	     currentItem.setItemCode(item.getItemCode());
	     currentItem.setItemName(item.getItemName());
	     currentItem.setItemPrice(item.getItemPrice());
	     currentItem.setFlagStatus(1);		
	     currentItem.setSendFlag(0);		
	     currentItem.setModifiedUsercode(1);
		 
	     itemServicePri.updateItem(currentItem);
	     if(GlobalVariables.cloudFlag) { itemServiceSec.updateItem(currentItem);	}
	        
	     return new ResponseEntity<Item>(currentItem, HttpStatus.OK);
	}
	
	@RequestMapping(value="/removeitem/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> removeItem(@PathVariable("id") int id) {
		
		 logger.info("Updating Item with id {}", id);		 
		 Item currentItem = itemServicePri.findByitemId(id);
		 
	     if (currentItem == null) {
	          logger.error("Unable to update. Item with id {} not found.", id);
	          return new ResponseEntity(new CustomErrorType("Unable to update. Item with id " + id + " not found."),HttpStatus.NOT_FOUND);
	     }
	 	     
	     currentItem.setFlagStatus(2);		
	     currentItem.setSendFlag(0);		
	     currentItem.setModifiedUsercode(1);
		 
	     itemServicePri.updateItem(currentItem);
	     if(GlobalVariables.cloudFlag) { itemServiceSec.updateItem(currentItem);	}
	        
	     return new ResponseEntity<Item>(currentItem, HttpStatus.OK);
	}
	

	

		
}
