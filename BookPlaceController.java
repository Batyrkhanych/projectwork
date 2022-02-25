package kz.lori.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.company.data.interfaces.IDB;

import kz.lori.entity.BookPlace;
import kz.lori.entity.Person;
import kz.lori.entity.interfaces.IBookPlace;

 

public class BookPlaceController {
	private IDB db;
	public BookPlaceController(IDB db) { 
		this.db = db;
	}
	
	public List<IBookPlace> getAllPlaces() {
		
		 Connection con = null;
	        try {
	            con = db.getConnection();
	            String sql = "SELECT * FROM places";
	            Statement st = con.createStatement();

	            ResultSet rs = st.executeQuery(sql);
	            List<IBookPlace> places = new LinkedList<>();
	            while (rs.next()) {
	            	 IBookPlace place = new BookPlace(rs.getInt("place_id"), 
	            			 rs.getInt("place_stage"), 
	            			 rs.getBoolean("place_free"), rs.getInt("person_id"));

	            	places.add(place);
	            }

	            return places;
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                con.close();
	            } catch (SQLException throwables) {
	                throwables.printStackTrace();
	            }
	        }
	        return null;
	    }
	public void addPerson(Person person) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO persons(person_id, person_name, person_telephone, person_address, car_id) VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, person.getId());
            st.setString(2, person.getName());
            st.setString(3, person.getAddress());
            st.setInt(4, person.getCarid());
            	
            	
            	st.execute();
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
	public void addBookPlace(BookPlace place) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO bookplaces(place_id, place_stage, person_telephone, place_free, person_id) VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, place.getId());
            st.setInt(2, place.getStage());
            st.setBoolean(3, place.isFree());
            st.setInt(4, place.getPersonid());
            	
            	
            	st.execute();
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
