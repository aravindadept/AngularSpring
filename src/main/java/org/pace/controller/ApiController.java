package org.pace.controller;

import java.util.ArrayList;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
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
	MaxServicePri maxServicePri;
	
	@Autowired
	MaxServiceSec maxServiceSec;
	
	@Autowired
	UserInfoServicePri userInfoServicePri;
	
	@Autowired
	CategoryServicePri categoryServicePri;
	
	@Autowired
	CategoryServiceSec categoryServiceSec;
	
	@Autowired
	ItemServicePri itemServicePri;
	
	@Autowired
	ItemServiceSec itemServiceSec;
	
	@Autowired
	PoItemServicePri poItemServicePri;
	
	@Autowired
	PoItemServiceSec poItemServiceSec;
	
	@Autowired
	PoServicePri poServicePri;
	
	@Autowired
	PoServiceSec poServiceSec;
	
	@RequestMapping(value="/userlist" , method = RequestMethod.GET )
	public List<UserInfo>  userlist() {
		
		return userInfoServicePri.findAll();
	}
	
	@RequestMapping(value="/username/{username}" , method = RequestMethod.GET )
	public UserInfo  userlist(@PathVariable("username") String username) {
		
		return userInfoServicePri.findByuserName(username);
	}
	 	
/*=================================================================================================================*/
	
	@RequestMapping(value="/categorylist" , method = RequestMethod.GET )
	public List<Category>  category() {
		
		return categoryServicePri.findAll();
	}
	
	@RequestMapping(value="/categoryid/{id}" , method = RequestMethod.GET )
	public Category  category(@PathVariable("id") int id) {
		
		return categoryServicePri.findBycategoryId(id);
	}
	
	@RequestMapping(value="/createcategory" ,  method = RequestMethod.POST)
	public ResponseEntity<?> createCategory( @RequestBody Category category ,UriComponentsBuilder ucBuilder) {
		category.setFlagStatus(0);
		category.setCreatedUsercode(1);				 
		logger.info("Creating Category	: {} ",category);
		categoryServicePri.save(category);
		
		if(GlobalVariables.cloudFlag) { categoryServiceSec.save(category);}
			
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
	     categoryServicePri.update(currentCategory);
	     if(GlobalVariables.cloudFlag) { categoryServiceSec.update(currentCategory);	}
	        
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
	     categoryServicePri.update(currentCategory);
	     if(GlobalVariables.cloudFlag) { categoryServiceSec.update(currentCategory);	}
	        
	     return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
	}
	
/*=================================================================================================================*/
	
	
	@RequestMapping(value="/itemlist" , method = RequestMethod.GET )
	public List<Item>  item() {		
		return itemServicePri.findAll();
	}
	
	@RequestMapping(value="/itemid/{id}" , method = RequestMethod.GET )
	public Item  item(@PathVariable("id") int id) {		
		return itemServicePri.findByitemId(id);
	}
	
	@RequestMapping(value="/createitem" ,  method = RequestMethod.POST)
	public ResponseEntity<?> createItem( @RequestBody Item item ,UriComponentsBuilder ucBuilder) {
		item.setFlagStatus(0);			
		item.setCreatedUsercode(1);	
		logger.info("Creating item	: {} ",item);
		itemServicePri.save(item);
		
		if(GlobalVariables.cloudFlag) { itemServiceSec.save(item);	}
		
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
		 
	     itemServicePri.update(currentItem);
	     if(GlobalVariables.cloudFlag) { itemServiceSec.update(currentItem);	}
	        
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
		 
	     itemServicePri.update(currentItem);
	     if(GlobalVariables.cloudFlag) { itemServiceSec.update(currentItem);	}
	        
	     return new ResponseEntity<Item>(currentItem, HttpStatus.OK);
	}
	
	
/*=================================================================================================================*/
	
	@RequestMapping(value="/poitemlist" , method = RequestMethod.GET )
	public List<PoItem>  poItem() {		
		return poItemServicePri.findAll();
	}
	
	@RequestMapping(value="/poitemid/{id}" , method = RequestMethod.GET )
	public List<PoItem>  poItem(@PathVariable("id") int id) {		
		return poItemServicePri.findBypoId(id);
	}
	
	

/*=================================================================================================================*/
	
	@RequestMapping(value="/polist" , method = RequestMethod.GET )
	public List<Po>  poList() {		
		return poServicePri.findAll();
	}
	
	@RequestMapping(value="/poid/{id}" , method = RequestMethod.GET )
	public Po  poId(@PathVariable("id") int id) {		
		return poServicePri.findByid(id);
	}
	@RequestMapping(value="/createpo" ,  method = RequestMethod.POST)
	public ResponseEntity<?> createPo( @RequestBody List<PoItem> itemList ) {

		int maxValue=000;
		double totalAmt=0;
		
		Max max=maxServicePri.findByid(1);
		maxValue=max.getMaxValue();
		max.setMaxValue(maxValue+1);
		maxServicePri.update(max);
		
		if(GlobalVariables.cloudFlag) { maxServiceSec.update(max);}
		
		for (PoItem poitem : itemList) {
			poitem.setPoId(maxValue);
			poitem.setFlagStatus(0);			
			poitem.setCreatedUsercode(1);	
			totalAmt+=poitem.getAmount();
		//	poItemServicePri.save(poitem); //	if(GlobalVariables.cloudFlag) {  poItemServiceSec.save(poitem); }
		}
		Po po =new Po();
		po.setId(maxValue);
		po.setAmount(totalAmt);
		po.setFlagStatus(0);	
		po.setPoItem(itemList);
		po.setCreatedUsercode(1);	
		
		logger.info("Creating Po	: {} ",po);
		poServicePri.save(po);
		if(GlobalVariables.cloudFlag) { poServiceSec.save(po);}
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/updatepo" ,  method = RequestMethod.PUT)
	public ResponseEntity<?> updatePo( @RequestBody List<PoItem> itemList ) {

		double totalAmt=0;
		for (PoItem poitem : itemList) {
			poitem.setFlagStatus(0);			
			poitem.setModifiedUsercode(1);	
			totalAmt+=poitem.getAmount();
		}

		Po po =new Po();
		po.setId(itemList.get(0).getPoId());
		po.setAmount(totalAmt);
		po.setFlagStatus(0);	
		po.setPoItem(itemList);
		po.setModifiedUsercode(1);	
		
		logger.info("Creating Po	: {} ",po);
		
		poServicePri.save(po);
		if(GlobalVariables.cloudFlag) { poServiceSec.save(po);}
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/removepo/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> removePo(@PathVariable("id") int id) {
		
		 logger.info("Updating Item with id {}", id);		 
		 Po currentPo = poServicePri.findByid(id);
		 
	     if (currentPo == null) {
	          logger.error("Unable to update. Item with id {} not found.", id);
	          return new ResponseEntity(new CustomErrorType("Unable to update. Item with id " + id + " not found."),HttpStatus.NOT_FOUND);
	     }
	 	     
	     currentPo.setFlagStatus(2);		
	     currentPo.setModifiedUsercode(1);
		 
	     poServicePri.update(currentPo);
	     if(GlobalVariables.cloudFlag) { poServiceSec.update(currentPo);	}
	        
	     return new ResponseEntity<Po>(currentPo, HttpStatus.OK);
	}
	
}
