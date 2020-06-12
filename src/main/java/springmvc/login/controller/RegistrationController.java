package springmvc.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springmvc.login.dao.UserDao;
import springmvc.login.model.User;

@Controller
public class RegistrationController {

	@Autowired
	private UserDao userDao;
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView userLogin(@RequestParam("userId") String userId, @RequestParam("password") String password,  @RequestParam("repassword") String repassword) {

		
		ModelAndView mv = new ModelAndView();
		int counter = 0;
		
//		System.out.println(registration.getUserId());
		
		if(password.equals(repassword)) {
			User user = new User();
			user.setUserId(userId);
			user.setPassword(password);

			counter = userDao.registerUser(user);
		}
		

		if (counter > 0) {
			mv.addObject("msg", "User registration successful.");
		} else {
			mv.addObject("msg", "Incorrect Registration.");
		}

		mv.setViewName("login");

		return mv;

	}
}
