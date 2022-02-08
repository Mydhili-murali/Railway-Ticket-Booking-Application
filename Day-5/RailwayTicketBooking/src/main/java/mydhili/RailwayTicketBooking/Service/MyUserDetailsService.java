package mydhili.RailwayTicketBooking.Service;

import mydhili.RailwayTicketBooking.Entity.MyUserDetails;
import mydhili.RailwayTicketBooking.Entity.Passengers;
import mydhili.RailwayTicketBooking.Repository.PassengersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PassengersRepository passengersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Passengers user=passengersRepository.getById(username);
        return new MyUserDetails(user);
    }
}
