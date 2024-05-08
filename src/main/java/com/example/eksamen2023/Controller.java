package com.example.eksamen2023;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class Controller {
    @GetMapping ("/hello")
    public String check(){
        return "Your controller is working";
    }

    @Autowired
    private JdbcTemplate db;

    @Autowired
    HttpSession session;

    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping("/saveCitizen")
    public void saveCitizen(Citizen c, HttpServletResponse response) throws IOException{
        String sql = "INSERT INTO Citizen (FIRSTNAME, SURNAME, DOB, SSN, PHONE, EMAIL, CITY, STREET) VALUES(?,?,?,?,?,?,?,?)";
        try{
            db.update(sql, c.getFirstname(), c.getSurname(), c.getDoB(), c.getSSN(), c.getPhoneNumber(),
                    c.getEmail(), c.getCity(), c.getStreet());
        } catch (Exception e){
            logger.error("An error has occured when saving citizen to database: "+e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occured when saving to database");
        }
    }

    class CitizenRowMapper implements RowMapper<Citizen> {
        @Override
        public Citizen mapRow(ResultSet rs, int rowNum) throws SQLException {
            Citizen citizen = new Citizen();
            citizen.setId(rs.getLong("ID"));
            citizen.setFirstname(rs.getString("FIRSTNAME"));
            citizen.setSurname(rs.getString("SURNAME"));
            citizen.setDoB(rs.getDate("DOB").toLocalDate());
            citizen.setSSN(rs.getString("SSN"));
            citizen.setPhoneNumber(rs.getString("PHONE"));
            citizen.setEmail(rs.getString("EMAIL"));
            citizen.setCity(rs.getString("CITY"));
            citizen.setStreet(rs.getString("STREET"));
            return citizen;
        }
    }

    @GetMapping("/login")
    public boolean loginCitizen(HttpServletResponse response) throws IOException{
        try {
            session.setAttribute("loggedIn", true);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @GetMapping("/removeUnderage")
    public boolean removeUnderage(HttpServletResponse response) throws IOException{
        String getCitizens = "SELECT * FROM Citizen";
        String removeCitizens = "DELETE FROM Citizen WHERE ID=?";
        LocalDate dateToday = LocalDate.now();
        System.out.println(dateToday);
        if (session.getAttribute("loggedIn") != null){
            try{
                List<Citizen> citizens = db.query(getCitizens, new CitizenRowMapper());
                System.out.println(citizens.get(0).getDoB());
                for (Citizen citizen : citizens){
                    if (calculateAge(citizen.getDoB(), dateToday) < 18){
                        db.update(removeCitizens, citizen.getId());
                    }
                }
                return true;
            } catch (Exception e){
                logger.error("Error in removing underage citizens from database");
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return false;
            }
        } else {
            return false;
        }
    }
    @GetMapping("/logout")
    public boolean logoutCitizen(){
        try {
            session.removeAttribute("loggedIn");
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate){
        if ((birthDate != null) && (currentDate != null)){
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
    @GetMapping("/showCitizen")
    public List<Citizen> showCitizen(){
        List<Citizen> citizenList = db.query("SELECT * FROM Citizen", new CitizenRowMapper());
        Collections.sort(citizenList, Comparator.comparing(Citizen::getSurname));
        logger.info(citizenList.toString());
        return citizenList;
    }
}
