package com.avlija.hotel.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.avlija.hotel.form.BookingForm;
import com.avlija.hotel.model.NoteReservation;
import com.avlija.hotel.model.Role;
import com.avlija.hotel.model.Room;
import com.avlija.hotel.model.User;
import com.avlija.hotel.repository.RoomRepository;
import com.avlija.hotel.service.UserService;

@Controller
public class UserController {

 @Autowired
 private UserService userService;
 
 @Autowired
 private RoomRepository roomRepository;

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
     mav.addObject("roles", rolesList);
     mav.addObject("userProfile", userProfile);
     return mav;
 }
 
 @RequestMapping("/active/{id}")
 public ModelAndView active(@PathVariable(name = "id") Integer id) {
     ModelAndView mav = new ModelAndView("user/profile_page");
     User userProfile = userService.findUserById(id);
     	if(userProfile.getActive() == 0) {
     		userProfile.setActive(1);
     		userService.updateUser(userProfile);
     	} else {
     		userProfile.setActive(0);
     		userService.updateUser(userProfile);
     	}
     Set<Role> rolesList = userProfile.getRoles();
     mav.addObject("roles", rolesList);
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
  model.setViewName("admin/list_all_users");
  
  return model;
 }
 
 @RequestMapping(value = "/resinfo/{id}")
 public ModelAndView editRes1(@PathVariable(name = "id") Long id) {
     ModelAndView mav = new ModelAndView("home/list_all_reservations");
     User user = userService.findUserById(id);
     Set<NoteReservation> listReservations = user.getNoteReservations();
     mav.addObject("listReservations", listReservations);
     if(listReservations.size() == 0) {
    	 String message = "There are no reservations for user " + user.getFirst_name();
    	 mav.addObject("message", message);
    	 return mav;
     }
     return mav;
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

@RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }
}