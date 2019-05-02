package org.pace.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.pace.configuration.GlobalVariables;
import org.pace.custom.CustomErrorType;
import org.pace.model.Category;
import org.pace.model.Item;
import org.pace.model.Max;
import org.pace.model.Po;
import org.pace.model.PoItem;
import org.pace.model.UserInfo;
import org.pace.service.primary.CategoryServicePri;
import org.pace.service.primary.ItemServicePri;
import org.pace.service.primary.MaxServicePri;
import org.pace.service.primary.PoItemServicePri;
import org.pace.service.primary.PoServicePri;
import org.pace.service.primary.UserInfoServicePri;
import org.pace.service.secondary.CategoryServiceSec;
import org.pace.service.secondary.ItemServiceSec;
import org.pace.service.secondary.MaxServiceSec;
import org.pace.service.secondary.PoItemServiceSec;
import org.pace.service.secondary.PoServiceSec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value="/api")
public class ApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired HttpSession session; //autowiring session
	
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
	
	@RequestMapping(value="/categorypage" , method = RequestMethod.GET )
	public List<Category>  categoryPage(@PageableDefault(value=10, page=0) Pageable pageable) {
		
		Page<Category> page = categoryServicePri.findByPageSize(pageable);
	    return page.getContent();
	    
	    
	    
	}
	
	@RequestMapping(value="/categoryid/{id}" , method = RequestMethod.GET )
	public Category  category(@PathVariable("id") int id) {
		
		return categoryServicePri.findBycategoryId(id);
	}
	
	@RequestMapping(value="/createcategory" ,  method = RequestMethod.POST)
	public ResponseEntity<?> createCategory( @RequestBody Category category ,UriComponentsBuilder ucBuilder) {
		
		int  userId=(Integer)session.getAttribute("userId");
		
		category.setFlagStatus(0);
		category.setCreatedUsercode(userId);				 
		logger.info("Creating Category	: {} ",category);
		categoryServicePri.save(category);
		
		if(GlobalVariables.cloudFlag) { categoryServiceSec.save(category);}
			
		HttpHeaders headers = new HttpHeaders();
	//	headers.setLocation(ucBuilder.path("/api/userlist/{username}").buildAndExpand(user.getUsername()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/updatecategory/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
		
		int  userId=(Integer)session.getAttribute("userId");
		 logger.info("Updating category with id {}", id);
		 
		 Category currentCategory = categoryServicePri.findBycategoryId(id);
		 
	     if (currentCategory == null) {
	          logger.error("Unable to update. category with id {} not found.", id);
	          return new ResponseEntity(new CustomErrorType("Unable to upate. category with id " + id + " not found."),HttpStatus.NOT_FOUND);
	     }
	 
	     currentCategory.setCategoryName(category.getCategoryName());
	     currentCategory.setFlagStatus(1);	  	
	     currentCategory.setSendFlag(0);	  	
	     currentCategory.setModifiedUsercode(userId);
	     categoryServicePri.update(currentCategory);
	     if(GlobalVariables.cloudFlag) { categoryServiceSec.update(currentCategory);	}
	        
	     return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
	}
	
	@RequestMapping(value="/removecategory/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id ) {
		
		int userId=(Integer)session.getAttribute("userId");
		
		 logger.info("Deleting category with id {}", id);
		 
		 Category currentCategory = categoryServicePri.findBycategoryId(id);
		 
	     if (currentCategory == null) {
	          logger.error("Unable to delete. category with id {} not found.", id);
	          return new ResponseEntity(new CustomErrorType("Unable to upate. category with id " + id + " not found."),HttpStatus.NOT_FOUND);
	     }
	 
	     currentCategory.setFlagStatus(2);	  	
	     currentCategory.setSendFlag(0);	  	
	     currentCategory.setModifiedUsercode(userId);
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
		
		int userId=(Integer)session.getAttribute("userId");
		
		item.setFlagStatus(0);			
		item.setCreatedUsercode(userId);	
		logger.info("Creating item	: {} ",item);
		itemServicePri.save(item);
		
		if(GlobalVariables.cloudFlag) { itemServiceSec.save(item);	}
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/updateitem/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<?> updateItem(@PathVariable("id") int id, @RequestBody Item item) {
		
		int userId=(Integer)session.getAttribute("userId");
		
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
	     currentItem.setModifiedUsercode(userId);
		 
	     itemServicePri.update(currentItem);
	     if(GlobalVariables.cloudFlag) { itemServiceSec.update(currentItem);	}
	        
	     return new ResponseEntity<Item>(currentItem, HttpStatus.OK);
	}
	
	@RequestMapping(value="/removeitem/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> removeItem(@PathVariable("id") int id) {
		
		int userId=(Integer)session.getAttribute("userId");
		
		 logger.info("Updating Item with id {}", id);		 
		 Item currentItem = itemServicePri.findByitemId(id);
		 
	     if (currentItem == null) {
	          logger.error("Unable to update. Item with id {} not found.", id);
	          return new ResponseEntity(new CustomErrorType("Unable to update. Item with id " + id + " not found."),HttpStatus.NOT_FOUND);
	     }
	 	     
	     currentItem.setFlagStatus(2);		
	     currentItem.setSendFlag(0);		
	     currentItem.setModifiedUsercode(userId);
		 
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

		int userId=(Integer)session.getAttribute("userId");
		
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
			poitem.setCreatedUsercode(userId);	
			totalAmt+=poitem.getAmount();
		//	poItemServicePri.save(poitem); //	if(GlobalVariables.cloudFlag) {  poItemServiceSec.save(poitem); }
		}
		Po po =new Po();
		po.setId(maxValue);
		po.setAmount(totalAmt);
		po.setFlagStatus(0);	
		po.setPoItem(itemList);
		po.setCreatedUsercode(userId);	
		
		logger.info("Creating Po	: {} ",po);
		poServicePri.save(po);
		if(GlobalVariables.cloudFlag) { poServiceSec.save(po);}
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/updatepo" ,  method = RequestMethod.PUT)
	public ResponseEntity<?> updatePo( @RequestBody List<PoItem> itemList ) {

		int userId=(Integer)session.getAttribute("userId");
		
		double totalAmt=0;
		for (PoItem poitem : itemList) {
			poitem.setFlagStatus(0);			
			poitem.setModifiedUsercode(userId);	
			totalAmt+=poitem.getAmount();
		}

		Po po =new Po();
		po.setId(itemList.get(0).getPoId());
		po.setAmount(totalAmt);
		po.setFlagStatus(0);	
		po.setPoItem(itemList);
		po.setModifiedUsercode(userId);	
		
		logger.info("Updating Po	: {} ",po);
		
		poServicePri.save(po);
		if(GlobalVariables.cloudFlag) { poServiceSec.save(po);}
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/removepo/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> removePo(@PathVariable("id") int id) {
		
		int userId=(Integer)session.getAttribute("userId");
		
		 logger.info("Removing Po with id {}", id);		 
		 Po currentPo = poServicePri.findByid(id);
		 
	     if (currentPo == null) {
	          logger.error("Unable to update. Po with id {} not found.", id);
	          return new ResponseEntity(new CustomErrorType("Unable to update. Po with id " + id + " not found."),HttpStatus.NOT_FOUND);
	     }
	 	     
	     currentPo.setFlagStatus(2);		
	     currentPo.setModifiedUsercode(userId);
		 
	     poServicePri.update(currentPo);
	     if(GlobalVariables.cloudFlag) { poServiceSec.update(currentPo);	}
	        
	     return new ResponseEntity<Po>(currentPo, HttpStatus.OK);
	}
	
}
