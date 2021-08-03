package com.unext.model;


import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Ticket {

	private int counter;
	private String pnr;
	private LocalDate travelDate;
	private Train train;
	private TreeMap<Passengers,Integer> treeMap=new TreeMap<>();

	public Ticket() {
		
	}

	public Ticket(LocalDate travelDate, Train train) {
		super();
		this.travelDate = travelDate;
		this.train = train;
	}

	public String generatePnr() throws IOException {
		String c1=String.valueOf(this.getTrain().getSource().charAt(0));
		String c2=String.valueOf(this.getTrain().getDestination().charAt(0));
		File file = new File("counter.txt");
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		String line,str=null;
		while((line = br.readLine()) != null){
			
			str=line;
		}
		br.close();
		this.pnr=c1+c2+"_"+this.travelDate.getYear()+this.travelDate.getMonthValue()+this.travelDate.getDayOfMonth()+"_"+str;
		
        int k=Integer.valueOf(str);
        k++;
        String counterStr=null;
        counterStr = Integer.toString(k);
        File file1=new File("counter.txt");
		FileOutputStream fos = new FileOutputStream(file1);
		PrintWriter pr = new PrintWriter(fos);
		pr.write(counterStr);
		pr.close();
		return this.pnr;


	}

	public double calculatePassengerFare(Passengers passenger) {
		double fare;
		if(passenger.age<=12) {
			if(passenger.gender=='F'||passenger.gender=='f') {
				return fare= train.ticketPrice*0.25;
			}
			else {
				return fare=train.ticketPrice*0.50;
			}
		}
		else if(passenger.age>=60) {
			if(passenger.gender=='F'||passenger.gender=='f') {
				fare= train.ticketPrice*0.60;
				return fare=fare*0.5;
			}
			else {
				return fare=train.ticketPrice*0.60;
			}		
		}
		else {
			return fare=train.ticketPrice;
		}
	}

	public void addPassenger(String name,int age,char gender) {
		Passengers passenger = new Passengers(name, age, gender);
		double fare = calculatePassengerFare(passenger);
		this.treeMap.put(passenger, (int)fare);
	}

	public double calculateTotalTicketPrice() {
		double total = 0;
		for(Passengers passenger : this.treeMap.keySet()) {
			total += this.treeMap.get(passenger);
		}
		return total;
	}

	public StringBuilder generateTicket(String pnr) throws IOException {
		StringBuilder sb=new StringBuilder();
		sb.append("PNR: "+pnr+"\n");
		sb.append("Train no: "+this.train.getTrainNo()+"\n");
		sb.append("Train Name: "+this.train.getTrainName()+"\n");
		sb.append("From: " + this.train.getSource() + "\n");
		sb.append("To: " + this.train.getDestination() + "\n");
		sb.append("Travel Date: "+this.getTravelDate()+"\n");
		sb.append("Passengers:\n");
		sb.append("Name\t  Age\tGender\tFare\n");
		for(Passengers passenger : this.treeMap.keySet()) {
			sb.append(passenger.getName()+"\t"+passenger.getAge()+"\t"+passenger.getGender()+"\t"+this.treeMap.get(passenger)+"\n");
		}
		sb.append("\nTotal Fare: "+this.calculateTotalTicketPrice());
		return sb;
	}

	public void writeTicket() throws IOException {
		String pnr=this.generatePnr();
		String str = this.generateTicket(pnr).toString();
		String f1=pnr+".txt";
		File file=new File(f1);
		FileOutputStream fos = new FileOutputStream(file);
		PrintWriter pr = new PrintWriter(fos);
		pr.write(str);
		pr.close();
	}

	//getters and setters
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public TreeMap<Passengers, Integer> getTreeMap() {
		return treeMap;
	}

	public void setTreeMap(TreeMap<Passengers, Integer> treeMap) {
		this.treeMap = treeMap;
	}



}
