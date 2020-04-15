package com.avlija.hotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.aspectj.weaver.reflect.ReflectionBasedResolvedMemberImpl;
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
import com.avlija.hotel.service.ReservationServiceImpl;
import com.avlija.hotel.service.UserService;

@Controller
public class ReservationController {

 @Autowired
 private AddServiceRepository addServiceRepository;
 
 @Autowired
 private RoomRepository roomRepository;
 
 @Autowired
 private ReservationServiceImpl reservationImpl;
 
 @Autowired
 private DateRepository dateRepository;
 

 
 @RequestMapping(value= {"/allreservations"}, method=RequestMethod.GET)
 public ModelAndView showAllReservations() {
  ModelAndView model = new ModelAndView();
  List<Reservation> listReservations = reservationImpl.findAllReservations();
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
     //bookingForm.setServices(serviceList);
     mav.addObject("bookingForm", bookingForm);
     mav.addObject("serviceList", serviceList);
     return mav;
 }
 
 @RequestMapping(value = "/addservice", method = RequestMethod.POST)
 public ModelAndView bookRoom(@ModelAttribute("command") BookingForm bookingForm) throws ParseException {
     ModelAndView mav = new ModelAndView("user/services_confirmation");
    
     List<AddService> selectedServices = bookingForm.getServices();
     double totalCostServices = 0;
     for(AddService service: selectedServices) {
    	 totalCostServices += service.getCost();
     }
     
     Reservation res = reservationImpl.findReservationById(bookingForm.getReservationId());
     double resCost = res.getTotalCost();
     resCost += totalCostServices;
     res.setTotalCost(resCost);
     
     reservationImpl.saveReservation(res);
     
     res.getServices().addAll(selectedServices);
	 
	 reservationImpl.saveReservation(res);

	mav.addObject("reservation", res);
     return mav;    
 }
 
 @RequestMapping("/search/{id}")
 public ModelAndView searchByDate(@PathVariable(name = "id") Integer id) {
     ModelAndView mav = new ModelAndView("user/search_by_date");
     BookingForm bookingForm = new BookingForm();
     bookingForm.setUserId(id);
     mav.addObject("bookingForm", bookingForm);
     return mav;
 }
 
 @RequestMapping(value = "/search", method = RequestMethod.POST)
 public ModelAndView searchByDate(@ModelAttribute("command") BookingForm bookingForm) throws ParseException {
     ModelAndView mav = new ModelAndView("user/search_by_date");
     int userId = bookingForm.getUserId();
  
     String inputFromDate = bookingForm.getFromDate();
     String inputToDate = bookingForm.getToDate();

     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
     LocalDate checkIn = LocalDate.parse(inputFromDate, formatter);
	 LocalDate checkOut = LocalDate.parse(inputToDate, formatter);
	 
	 System.out.println("TEST 1, TEST 1, TEST 1");

	 List<Date> allDates = (List<Date>) dateRepository.findAll();
	 List<Long> bookedRoomsIds = new ArrayList<>();
	 
	 for(int i = 0; i < allDates.size(); i++) {
		 if(allDates.get(i).getStart().equals(checkIn) && allDates.get(i).getEnd().equals(checkOut)){
			 bookedRoomsIds.add(allDates.get(i).getRoom().getId());
		 }
	 }

	 System.out.println("TEST 3, TEST 3, TEST 3");
	 List<Long> availableRoomsIds = new ArrayList<Long>();
	 List<Room> allRooms = roomRepository.findAll();
	 for(Room room: allRooms) {
		 availableRoomsIds.add(room.getId());
	 }
	 System.out.println("TEST 4, TEST 4, TEST 4");

	 availableRoomsIds.removeAll(bookedRoomsIds);
	 
	 List<Room> availableRooms = new ArrayList<>();
	 for(long availableRoomId: availableRoomsIds) {
		 availableRooms.add(roomRepository.findById(availableRoomId).get());
	 }

	 mav.addObject("availableRooms", availableRooms);
	 mav.addObject("bookingForm", bookingForm);
     return mav;    
 }
 
}