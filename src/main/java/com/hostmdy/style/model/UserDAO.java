package com.hostmdy.style.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.hostmdy.style.utility.PasswordEncoder;
import com.hostmdy.style.utility.PasswordValidator;

public class UserDAO {
	
	private DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public UserDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// for user-profile 
	public void updateUser(User user) {
        String sql = "UPDATE user SET email = ?, firstname = ?, lastname = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getFirstname());
            stmt.setString(3, user.getLastname());
            stmt.setLong(4, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public boolean enableUser(Long userId) {
		boolean ok = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update user set "
					+ "enable=? where id=?;");
			pStmt.setBoolean(1, true);
			pStmt.setLong(2, userId);
			int rowEffected = pStmt.executeUpdate();
			
			if(rowEffected > 0) {
				ok = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return ok;
	}
	
	public boolean disableUser(Long userId) {
		boolean ok = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update user set "
					+ "enable=? where id=?;");
			pStmt.setBoolean(1, false);
			pStmt.setLong(2, userId);
			int rowEffected = pStmt.executeUpdate();
			
			if(rowEffected > 0) {
				ok = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return ok;
	}
	
	public List<User> getAllUsers(){
		List<User> userList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where role='user';");
			
			while(rs.next()) {
				userList.add(new User(
						rs.getLong("id"), 
						rs.getString("firstname"), 
						rs.getString("lastname"), 
						rs.getString("username"), 
						rs.getString("password"), 
						rs.getString("email"), 
						rs.getString("role"), 
						rs.getBoolean("enable"), 
						rs.getTimestamp("createdAt").toLocalDateTime(),
						rs.getString("profilePicture")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return userList;
	}

	public Optional<User> getUserByUsernameOrEmail(String username){
	    Optional<User> userOptional = Optional.empty();
	    try {
	        connection = dataSource.getConnection();
	        String sql = "SELECT * FROM user WHERE username = ? OR email = ?";
	        pStmt = connection.prepareStatement(sql);
	        pStmt.setString(1, username);
	        pStmt.setString(2, username);
	        rs = pStmt.executeQuery();
	        
	        while(rs.next()) {
	            userOptional = Optional.of(new User(
	                    rs.getLong("id"), 
	                    rs.getString("firstname"), 
	                    rs.getString("lastname"), 
	                    rs.getString("username"), 
	                    rs.getString("password"), 
	                    rs.getString("email"), 
	                    rs.getString("role"), 
	                    rs.getBoolean("enable"), 
	                    rs.getTimestamp("createdAt").toLocalDateTime(),
	                    rs.getString("profilePicture")
	                    ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }
	    
	    return userOptional;
	}
	
	public boolean createUser(User user) {
	    boolean insertOk = false;
	    try (Connection connection = dataSource.getConnection()) {
	        String sql = "INSERT INTO user (firstname, lastname, username, password, email, role, enable, createdAt, profilePicture) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement pStmt = connection.prepareStatement(sql)) {
	            pStmt.setString(1, user.getFirstname());
	            pStmt.setString(2, user.getLastname());
	            pStmt.setString(3, user.getUsername());
	            pStmt.setString(4, PasswordEncoder.encode(user.getPassword()));
	            pStmt.setString(5, user.getEmail());
	            pStmt.setString(6, user.getRole());
	            pStmt.setBoolean(7, user.getEnable());
	            pStmt.setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
	            pStmt.setString(9, user.getProfilePicture());

	            System.out.println("Executing SQL: " + pStmt.toString());

	            int rowEffected = pStmt.executeUpdate();
	            insertOk = rowEffected > 0;
	            System.out.println("Rows affected: " + rowEffected);
	        }
	    } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
	        e.printStackTrace();
	    }
	    return insertOk;
	}

	
	public boolean authenticate(String username, String password) {
	    Optional<User> userOptional = getUserByUsernameOrEmail(username);
	    if (userOptional.isEmpty()) {
	        System.out.println("User not found");
	        return false;
	    }
	    User user = userOptional.get();
	    try {
	        if (PasswordValidator.validatePassword(password, user.getPassword()) && user.getEnable()) {
	            return true;
	        } else {
	            System.out.println("Password validation failed or user is not enabled");
	        }
	    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
