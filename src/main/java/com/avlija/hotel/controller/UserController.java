package com.avlija.hotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

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
import com.avlija.hotel.model.Date;
import com.avlija.hotel.model.LocalDateAttributeConverter;
import com.avlija.hotel.model.Reservation;
import com.avlija.hotel.model.Role;
import com.avlija.hotel.model.Room;
import com.avlija.hotel.model.User;
import com.avlija.hotel.repository.DateRepository;
import com.avlija.hotel.repository.ReservationRepository;
import com.avlija.hotel.repository.RoomRepository;
import com.avlija.hotel.repository.UserRepository;
import com.avlija.hotel.service.UserService;

@Controller
public class UserController {

 @Autowired
 private UserService userService;
 
 @Autowired
 private RoomRepository roomRepository;
 
 @Autowired
 private ReservationRepository reservationRepository;
 
 @Autowired
 private DateRepository dateRepository;
 
 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("user/login");
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
 public ModelAndView signup() {
  ModelAndView model = new ModelAndView();
  User user = new User();
  model.addObject("user", user);
  model.setViewName("admin/signup");
  
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
 public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  User userExists = userService.findUserByEmail(user.getEmail());
  if(userExists != null) {
   bindingResult.rejectValue("email", "error.user", "This email already exists!");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("admin/signup");
  } else {
   userService.saveUser(user);
   model.addObject("msg", "User has been registered successfully!");
   model.addObject("user", new User());
   model.setViewName("admin/signup");
  }
  
  return model;
 }
 
 @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
 public ModelAndView home() {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByEmail(auth.getName());
  long userId = user.getId();
  model.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
  model.addObject("userId", userId);
  model.setViewName("home/home");
  return model;
 }
 
 @RequestMapping("/profile/{id}")
 public ModelAndView profilePage(@PathVariable(name = "id") Integer id) {
     ModelAndView mav = new ModelAndView("user/profile_page");
     User userProfile = userService.findUserById(id);
     Set<Role> rolesList = userProfile.getRoles();
     
     mav.addObject("userProfile", userProfile);
     return mav;
 }
 
 @RequestMapping(value= {"/admin"}, method=RequestMethod.GET)
 public ModelAndView admin() {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByEmail(auth.getName());
  
  model.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
  model.setViewName("admin/adminPage");
  return model;
 }
 
 @RequestMapping(value= {"/allusers"}, method=RequestMethod.GET)
 public ModelAndView showAllUsers() {
  ModelAndView model = new ModelAndView();
  List<User> listUsers = userService.finaAllUsers();
  model.addObject("listUsers", listUsers);
  User user = new User();
  model.addObject("user", user);
  model.setViewName("home/list_all_users");
  
  return model;
 }
 
 
 @RequestMapping(value= {"/createroom"}, method=RequestMethod.GET)
 public ModelAndView createRoom() {
  ModelAndView model = new ModelAndView();
  Room room = new Room();
  model.addObject("room", room);
  model.setViewName("admin/create_room");
  
  return model;
 }
 
 @RequestMapping(value= {"/createroom"}, method=RequestMethod.POST)
 public ModelAndView createUser(@Valid Room room, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  Room roomExists = roomRepository.findByNum(room.getNum());
  if(roomExists != null) {
   bindingResult.rejectValue("num", "error.room", "This room number already exists!");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("admin/create_room");
  } else {
   roomRepository.save(room);
   model.addObject("msg", "Room has been registered successfully!");
   model.addObject("room", new Room());
   model.setViewName("admin/create_room");
  }
  
  return model;
 }
 
 @RequestMapping(value= {"/allrooms"}, method=RequestMethod.GET)
 public ModelAndView showAllRooms() {
  ModelAndView model = new ModelAndView();
  List<Room> listRooms = roomRepository.findAll();
  model.addObject("listRooms", listRooms);
  Room room = new Room();
  model.addObject("room", room);
  model.setViewName("home/list_all_rooms");
  
  return model;
 }
 
 @RequestMapping("/book/{id}")
 public ModelAndView bookRoom(@PathVariable(name = "id") Integer id) {
     ModelAndView mav = new ModelAndView("user/room_booking");
     BookingForm bookingForm = new BookingForm();
     bookingForm.setUserId(id);
     mav.addObject("bookingForm", bookingForm);
     return mav;
 }
 
 @RequestMapping(value = "/book", method = RequestMethod.POST)
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
	 Reservation res = new Reservation(dateNow, daysBetween, totalRoomCost, userService.findUserById(userId));
	 reservationRepository.save(res);
	 
	 System.out.println("TEST 2, TEST 2, TEST 2");
	 Room bookedRoom = roomRepository.findByNum(roomNum);
	 LocalDateAttributeConverter converter = new LocalDateAttributeConverter();
	 java.sql.Date startDate = converter.convertToDatabaseColumn(checkIn);
	 java.sql.Date endDate = converter.convertToDatabaseColumn(checkOut);
	 
	 Date date = new Date(startDate, endDate, bookedRoom);
	 dateRepository.save(date);
	 
	 System.out.println("TEST 3, TEST 3, TEST 3");
	 res.getDates().add(date);
	 reservationRepository.save(res);
	 mav.addObject("reservation", res);
     return mav;    
 }
 

@RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }
}