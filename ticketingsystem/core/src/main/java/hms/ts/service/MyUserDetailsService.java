package hms.ts.service;

import hms.ts.dao.EmployeeDao;
import hms.ts.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeDao userInfoDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee userInfo = userInfoDAO.findEmployeeByUsername(username);
        System.out.println("UserInfo= " + userInfo);

        if (userInfo == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        // [USER,ADMIN,..]
        String role= userInfoDAO.findEmployeeByUsername(username).getRole().getTitle();
        if(role.equalsIgnoreCase("project manager")){
            role = "ADMIN";
        }else{
            role = "USER";
        }

        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        if(role!= null)  {
            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
            grantList.add(authority);

        }

        UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(), //
                userInfo.getPassword(),grantList);

        return userDetails;
    }

}
