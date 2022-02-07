package mydhili.RailwayTicketBooking.Controller;

import mydhili.RailwayTicketBooking.Entity.TrainSchedule;
import mydhili.RailwayTicketBooking.Entity.Trains;
import mydhili.RailwayTicketBooking.Service.TrainScheduleService;
import mydhili.RailwayTicketBooking.Service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;

@Controller
public class TrainScheduleController {
    @Autowired
    private TrainsService trainsService;

    @Autowired
    private TrainScheduleService trainScheduleService;

    @RequestMapping("/{userName}/trainSchedule/{trainNumber}")
    public String trainSchedule(@PathVariable String userName, @PathVariable String trainNumber, Model model){
        model.addAttribute("schedules", trainScheduleService.trainScheduleDetails(trainNumber));
        model.addAttribute("userName", userName);
        Trains trains = trainsService.getByTrainNumber(trainNumber);
        model.addAttribute("trainName", trains.getTrainName());
        return "trainSchedule";
    }
    @RequestMapping("/{userName}/adminViewTrainSchedule/{trainNumber}")
    public String adminViewTrainSchedule(@PathVariable String userName, @PathVariable String trainNumber, Model model){
        model.addAttribute("schedules", trainScheduleService.trainScheduleDetails(trainNumber));
        model.addAttribute("userName", userName);
        Trains trains = trainsService.getByTrainNumber(trainNumber);
        model.addAttribute("trainName", trains.getTrainName());
        return "adminViewTrainSchedule";
    }

    @RequestMapping("/{userName}/updateTrainSchedule/{id}")
    public String updateTrainScheduleForm(@PathVariable String userName, @PathVariable Long id,Model model){
        model.addAttribute("userName", userName);
        model.addAttribute("trainSchedule", trainScheduleService.getById(id));
        return "updateTrainSchedule";
    }

    @PostMapping("/{userName}/updateTrainSchedule/{id}")
    public String updateTrainSchedule(@PathVariable String userName, @PathVariable Long id, Model model, HttpServletRequest req){
        TrainSchedule trainSchedule=trainScheduleService.getById(id);
        trainSchedule.getTrains();
        Date date=Date.valueOf(req.getParameter("date"));
        Time departingTime=Time.valueOf(req.getParameter("departingTime")+":00");
        Time arrivalTime=Time.valueOf(req.getParameter("arrivalTime")+":00");
        trainSchedule.setDate(date);
        trainSchedule.setDepartingTime(departingTime);
        trainSchedule.setArrivalTime(arrivalTime);
        trainScheduleService.saveTrainSchedule(trainSchedule);
        return "redirect:/"+userName+"/adminViewTrainSchedule/"+trainSchedule.getTrains();

    }


}
