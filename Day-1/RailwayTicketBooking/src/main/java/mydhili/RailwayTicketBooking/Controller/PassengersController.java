package mydhili.RailwayTicketBooking.Controller;

import mydhili.RailwayTicketBooking.Entity.Passengers;
import mydhili.RailwayTicketBooking.Service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PassengersController {
    @Autowired
    private PassengersService service;

    @RequestMapping("/")
    public String index(){
        return "home";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public String register(HttpServletRequest req, Model model){
        String name=req.getParameter("name");
        String address=req.getParameter("address");
        String emailId=req.getParameter("emailId");
        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        Long phoneNumber=Long.parseLong(req.getParameter("phNo"));
        Passengers passenger=new Passengers(name,userName,password,address,emailId,phoneNumber);
        service.savePassenger(passenger);
        model.addAttribute("message","Successfully registered!!!!!!");
        return "login";



    }
   @RequestMapping("/login")
   public String login(){
       return "login";
   }

    @PostMapping("/login")
    public String login(HttpServletRequest req,Model model)
    {
        Passengers passenger;
        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        if (service.existsById(userName)) {

            passenger = service.findById(userName);
            if (password.equals(passenger.getPassword())) {

                model.addAttribute("message", "Successfully logged in!!!!!!!");
//                model.addAttribute("userName",userName);
//                model.addAttribute("movies",trainService.listAllMovies());
                return "trainsListPage";

            } else {
                model.addAttribute("message", "Invalid credentials!!!!!!!");
                return "login";
            }
        } else {
            model.addAttribute("message", "Invalid credentials!!!!!!!");
            return "login";
        }



    }




}
