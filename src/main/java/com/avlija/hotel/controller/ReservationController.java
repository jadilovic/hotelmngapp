package com.avlija.hotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.avlija.hotel.form.BookingForm;
import com.avlija.hotel.model.AddService;
import com.avlija.hotel.model.Date;
import com.avlija.hotel.model.Reservation;
import com.avlija.hotel.model.Room;
import com.avlija.hotel.model.User;
import com.avlija.hotel.repository.AddServiceRepository;
import com.avlija.hotel.repository.DateRepository;
import com.avlija.hotel.repository.ReservationRepository;
import com.avlija.hotel.repository.RoomRepository;
import com.avlija.hotel.repository.UserRepository;
import com.avlija.hotel.service.UserService;

@Controller
public class ReservationController {

 @Autowired
 private AddServiceRepository addServiceRepository;
 
 @Autowired
 private RoomRepository roomRepository;
 
 @Autowired
 private ReservationRepository reservationRepository;
 
 @Autowired
 private DateRepository dateRepository;
 

 
 @RequestMapping(value= {"/allreservations"}, method=RequestMethod.GET)
 public ModelAndView showAllReservations() {
  ModelAndView model = new ModelAndView();
  List<Reservation> listReservations = (List<Reservation>) reservationRepository.findAll();
  model.addObject("listReservations", listReservations);
  model.setViewName("home/list_all_reservations");
  
  return model;
 }
 
 @RequestMapping(value= {"/alladdservices"}, method=RequestMethod.GET)
 public ModelAndView showAllAddServices() {
  ModelAndView model = new ModelAndView();
  List<AddService> listServices = (List<AddService>) addServiceRepository.findAll();
  model.addObject("listServices", listServices);
  model.setViewName("home/list_all_services");
  
  return model;
 }
 
 @RequestMapping("/addservice/{id}")
 public ModelAndView bookRoom(@PathVariable(name = "id") Long id) {
     ModelAndView mav = new ModelAndView("user/adding_service");
     BookingForm bookingForm = new BookingForm();
     bookingForm.setReservationId(id);
     List<AddService> serviceList = (List<AddService>) addServiceRepository.findAll();
     bookingForm.setServices(serviceList);
     mav.addObject("bookingForm", bookingForm);
     return mav;
 }
 
 @RequestMapping(value = "/addservice", method = RequestMethod.POST)
 public ModelAndView bookRoom(@ModelAttribute("command") BookingForm bookingForm) throws ParseException {
     ModelAndView mav = new ModelAndView("user/booking_confirmation");
     int userId = bookingForm.getUserId();
     int roomNum = bookingForm.getRoomNum();
     int daysBetween;
     
     //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM dd yyyy");
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
     String inputFromDate = bookingForm.getFromDate();
     String inputToDate = bookingForm.getToDate();
     System.out.println(inputFromDate);
     System.out.println(inputToDate);

     LocalDate checkIn = LocalDate.parse(inputFromDate, formatter);
	 LocalDate checkOut = LocalDate.parse(inputToDate, formatter);
	 
	 daysBetween = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
	 System.out.println ("Days: " + daysBetween);
	 double roomCost = roomRepository.findByNum(roomNum).getRoomType().getCost();
	 System.out.println(roomCost);
	 double totalRoomCost = roomCost * daysBetween;
	    
	 	java.util.Date dateNow = new java.util.Date();  
	 
	 System.out.println("TEST 1, TEST 1, TEST 1");
	// Reservation res = new Reservation(dateNow, daysBetween, totalRoomCost, userService.findUserById(userId), roomRepository.findByNum(roomNum));
	// reservationRepository.save(res);
	 
	 System.out.println("TEST 2, TEST 2, TEST 2");
	 Date date = new Date(checkIn, checkOut);
	 dateRepository.save(date);
	 
	 System.out.println("TEST 3, TEST 3, TEST 3");
	// res.getDates().add(date);
	// reservationRepository.save(res);
	// mav.addObject("reservation", res);
     return mav;    
 }
 
}