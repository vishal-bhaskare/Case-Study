package com.unext.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unext.model.Train;
import com.unext.model.Passengers;
import com.unext.model.Ticket;
import com.unext.repository.TrainRepository;

@RestController
@RequestMapping("/api")
public class TicketController {

	Ticket ticket1=new Ticket();
	Train train1=new Train();
	@Autowired
	TrainRepository trainRepository;
/*
	@GetMapping("/trains")
	public Train getTrainNumberAndDate(@RequestParam int trainNumber,@RequestParam String dateInString,@RequestParam String name,@RequestParam int age,@RequestParam String gender) throws Exception {
		Optional<Train> trainData = trainRepository.findById(trainNumber);
	    try {
	    	if(trainData.isPresent()) {
	    		 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 	        LocalDate travelDate = LocalDate.parse(dateInString, dateFormatter);
	    		  {
	    			if(travelDate.compareTo(LocalDate.now()) < 0)   
	    			{  
	    				String msg="Date is before present date";
	    				NoResultException e2=new NoResultException(msg);
	    				throw e2;
	    			}
	    			else 
	    				
	    			train1 = trainData.get();
	    			ticket1=new Ticket(travelDate, train1);
	    			ticket1.addPassenger(name,age,gender.charAt(0));
	    			ticket1.writeTicket();
	    			System.out.println("Ticket is written with name"+ticket1.getPnr()+".txt");
	    		}
	    		  
	    	}
	    	else {
	    		String msg="Invalid Input";
				NoResultException e2=new NoResultException(msg);
				throw e2;
	    	}
	    		
	    }catch(NoResultException e2) {
			e2.printStackTrace();
			System.exit(1);
		}
	    System.out.println("Ticket Successfully Generated");
	return null;
	}
	*/
	@GetMapping("/trains")
	public Train getTrainNumberAndDate(@RequestParam int trainNumber,@RequestParam String dateInString,@RequestParam int numberOfPassengers,@RequestBody Passengers[] passenger) throws Exception {
		Optional<Train> trainData = trainRepository.findById(trainNumber);
	    try {
	    	if(trainData.isPresent()) {
	    		 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 	        LocalDate travelDate = LocalDate.parse(dateInString, dateFormatter);
	    		  {
	    			if(travelDate.compareTo(LocalDate.now()) < 0)   
	    			{  
	    				String msg="Date is before present date";
	    				NoResultException e2=new NoResultException(msg);
	    				throw e2;
	    			}
	    			else 
	    				
	    			train1 = trainData.get();
	    			ticket1=new Ticket(travelDate, train1);
	    			for(int i=0;i<numberOfPassengers;i++) {
	    			ticket1.addPassenger(passenger[i].getName(),passenger[i].getAge(),passenger[i].getGender());
	    			
	    			}
	    			ticket1.writeTicket();
	    			System.out.println("Ticket is written with name"+ticket1.getPnr()+".txt");
	    		}
	    		  
	    	}
	    	else {
	    		String msg="Invalid Input";
				NoResultException e2=new NoResultException(msg);
				throw e2;
	    	}
	    		
	    }catch(NoResultException e2) {
			e2.printStackTrace();
			System.exit(1);
		}
	    System.out.println("Ticket Successfully Generated");
	return null;
	}
	
	

	
}
