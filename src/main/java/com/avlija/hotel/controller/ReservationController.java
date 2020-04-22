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
import com.avlija.hotel.model.LocalDateAttributeConverter;
import com.avlija.hotel.model.NoteReservation;
import com.avlija.hotel.model.Reservation;
import com.avlija.hotel.model.Room;
import com.avlija.hotel.model.User;
import com.avlija.hotel.repository.AddServiceRepository;
import com.avlija.hotel.repository.DateRepository;
import com.avlija.hotel.repository.NoteReservationRepository;
import com.avlija.hotel.repository.ReservationRepository;
import com.avlija.hotel.repository.RoomRepository;
import com.avlija.hotel.repository.UserRepository;
import com.avlija.hotel.service.ReservationServiceImpl;
import com.avlija.hotel.service.UserService;
import com.avlija.hotel.service.UserServiceImpl;

import net.bytebuddy.asm.Advice.Local;

@Controller
public class ReservationController {

 @Autowired
 private AddServiceRepository addServiceRepository;
 
 @Autowired
 private RoomRepository roomRepository;
 
 @Autowired
 private ReservationServiceImpl reservationImpl;
 
 @Autowired
 private NoteReservationRepository noteReservationRepository;
 
 @Autowired
 private DateRepository dateRepository;
 
 	@Autowired
 	private UserServiceImpl userServiceImpl;
 
 @RequestMapping(value= {"/allreservations"}, method=RequestMethod.GET)
 public ModelAndView showAllReservations() {
  ModelAndView model = new ModelAndView();
  List<NoteReservation> listReservations = (List<NoteReservation>) noteReservationRepository.findAll();
  
  for(NoteReservation res: listReservations) {
	  System.out.println(res.toString());
  }
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
 
 @RequestMapping("/search")
 public ModelAndView searchByDate() {
     ModelAndView mav = new ModelAndView("user/search_by_date");
	 List<Room> availableRooms = new ArrayList<>();
	 mav.addObject("availableRooms", availableRooms);
     BookingForm bookingForm = new BookingForm();
     mav.addObject("bookingForm", bookingForm);
     return mav;
 }
 
 @RequestMapping(value = "/search", method = RequestMethod.POST)
 public ModelAndView searchByDate(@ModelAttribute("command") BookingForm bookingForm) throws ParseException {
     ModelAndView mav = new ModelAndView("user/search_by_date");
  
     String inputFromDate = bookingForm.getFromDate();
     String inputToDate = bookingForm.getToDate();

     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
     LocalDate In = LocalDate.parse(inputFromDate, formatter);
	 LocalDate Out = LocalDate.parse(inputToDate, formatter);
	 In = In.plusDays(1);
	 Out = Out.plusDays(1);
	 
	 System.out.println("TEST 1, TEST 1, TEST 1");
	 System.out.println("Check in: " + In.toString() + ", Check out: " + Out.toString());
	 
	 LocalDateAttributeConverter converter = new LocalDateAttributeConverter();
	 java.sql.Date start = converter.convertToDatabaseColumn(In);
	 java.sql.Date end = converter.convertToDatabaseColumn(Out);
	 
	 System.out.println("TEST 1.5, TEST 1.5, TEST 1.5");
	 System.out.println("Check in: " + start.toString() + ", Check out: " + end.toString());
	 
	 List<NoteReservation> reservations = noteReservationRepository.findByDateFromTo(start, end);
	 List<Long> bookedRoomsIds = new ArrayList<>();
	 
	for(NoteReservation reservation: reservations) {
		bookedRoomsIds.add(reservation.getRoom().getId());
		 System.out.println("RESERVATION FROM TO DATES: Start:" + reservation.toString() + ", End: " + reservation.toString());
	 }

	 System.out.println("TEST 3, TEST 3, TEST 3");
	 List<Long> allRoomsIds = roomRepository.findAllIds();

	 System.out.println("TEST 4, TEST 4, TEST 4");

	 System.out.println("ALL ROOMS IDS: " + allRoomsIds.toString());
	 System.out.println("BOOKED ROOMS IDS: " + bookedRoomsIds.toString());
	 
	 // Removing booked IDs from all room IDs
	 	allRoomsIds.removeAll(bookedRoomsIds);
	 
	 System.out.println("AVAILABLE ROOMS IDS: " + allRoomsIds.toString());
	 
	 List<Room> availableRooms = new ArrayList<>();
	 for(long availableRoomId: allRoomsIds) {
		 availableRooms.add(roomRepository.findById(availableRoomId).get());
	 }

	 String message = null;
	 if(availableRooms.size() == 0) {
		 message = "There are no available rooms on the selected date";
	 }
	 
	 mav.addObject("message", message);
	 mav.addObject("availableRooms", availableRooms);
	 mav.addObject("bookingForm", bookingForm);
     return mav;    
 }
 
 @RequestMapping("/reserve/{id}")
 public ModelAndView reserveRoom(@PathVariable(name = "id") Integer id) {
     ModelAndView mav = new ModelAndView("user/reserve_room");
     BookingForm bookingForm = new BookingForm();
     bookingForm.setUserId(id);
     mav.addObject("bookingForm", bookingForm);
     return mav;
 }
 
 @RequestMapping(value = "/reserve", method = RequestMethod.POST)
 public ModelAndView reserveRoom(@ModelAttribute("command") BookingForm bookingForm) throws ParseException {
     ModelAndView mav = new ModelAndView("user/reserve_confirmation");
     int userId = bookingForm.getUserId();
     User user = userServiceImpl.findUserById(userId);
     int roomNum = bookingForm.getRoomNum();
     Room room = roomRepository.findByNum(roomNum);
     //int daysBetween;
     
     //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM dd yyyy");
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
     String inputFromDate = bookingForm.getFromDate();
     String inputToDate = bookingForm.getToDate();

     LocalDate In = LocalDate.parse(inputFromDate, formatter);
	 LocalDate Out = LocalDate.parse(inputToDate, formatter);
	 LocalDate checkIn = In.plusDays(1);
	 LocalDate checkOut = Out.plusDays(1);
	 
	 LocalDateAttributeConverter converter = new LocalDateAttributeConverter();
	 java.sql.Date start = converter.convertToDatabaseColumn(checkIn);
	 java.sql.Date end = converter.convertToDatabaseColumn(checkOut);
	 
     System.out.println(start.toString());
     System.out.println(end.toString());
	 
	// daysBetween = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
	// System.out.println ("Days: " + daysBetween);
	// double roomCost = roomRepository.findByNum(roomNum).getRoomType().getCost();
	// System.out.println(roomCost);
	// double totalRoomCost = roomCost * daysBetween;
	    
	 	java.util.Date dateNow = new java.util.Date();  
	 
	 System.out.println("TEST 1, TEST 1, TEST 1");
	 NoteReservation res = new NoteReservation(true, dateNow, start, end, room, user);
	 noteReservationRepository.save(res);
	 start = converter.convertToDatabaseColumn(In);
	 end = converter.convertToDatabaseColumn(Out);
	 res.setCheckIn(start);
	 res.setCheckOut(end);
	 /*
	 System.out.println("TEST 2, TEST 2, TEST 2");
	 Room bookedRoom = roomRepository.findByNum(roomNum);
	 LocalDateAttributeConverter converter = new LocalDateAttributeConverter();
	 java.sql.Date startDate = converter.convertToDatabaseColumn(checkIn);
	 java.sql.Date endDate = converter.convertToDatabaseColumn(checkOut);
	 
	 Date date = new Date(startDate, endDate, bookedRoom);
	 dateRepository.save(date);
	 
	 System.out.println("TEST 3, TEST 3, TEST 3");
	 res.getDates().add(date);
	 reservationRepository.save(res); */
	 mav.addObject("reservation", res);
     return mav;    
 }
 
}