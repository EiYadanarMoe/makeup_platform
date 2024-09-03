
package com.hostmdy.style.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StyleDAO {

	private DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public StyleDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	private void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void toggleLike(Long styleId, Long userId) {
	    if (isStyleLikedByUser(styleId, userId)) {
	        String sql = "DELETE FROM user_like WHERE style_id = ? AND user_id = ?";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setLong(1, styleId);
	            stmt.setLong(2, userId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } else {
	        String sql = "INSERT INTO user_like (style_id, user_id) VALUES (?, ?)";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setLong(1, styleId);
	            stmt.setLong(2, userId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	 public boolean addLike(long userId, long styleId) {
	        Connection connection = null;
	        PreparedStatement pStmt = null;

	        try {
	            connection = dataSource.getConnection();
	            pStmt = connection.prepareStatement("INSERT INTO user_like (user_id, style_id) VALUES (?, ?)");
	            pStmt.setLong(1, userId);
	            pStmt.setLong(2, styleId);

	            int rowAffected = pStmt.executeUpdate();
	            return rowAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            closeConnection();
	        }
	    }
	 
	 public List<Style> getLikedStylesByUserId(long userId) {
		    List<Style> likedStyles = new ArrayList<>();
		    String sql = "SELECT s.id, s.name, s.image_url, s.category " +
		                 "FROM makeupstyle s " +
		                 "INNER JOIN user_like ul ON s.id = ul.style_id " +
		                 "WHERE ul.user_id = ?";

		    try (Connection conn = dataSource.getConnection();
		         PreparedStatement statement = conn.prepareStatement(sql)) {
		        statement.setLong(1, userId);
		        try (ResultSet resultSet = statement.executeQuery()) {
		            while (resultSet.next()) {
		                long id = resultSet.getLong("id");
		                String name = resultSet.getString("name");
		                String image_url = resultSet.getString("image_url");
		                String category = resultSet.getString("category");

		                Style style = new Style(id, name, image_url, category);
		                likedStyles.add(style);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return likedStyles;
		}





	 
	 public boolean isStyleLikedByUser(Long styleId, Long userId) {
		    String sql = "SELECT COUNT(*) FROM user_like WHERE style_id = ? AND user_id = ?";
		    try (Connection conn = dataSource.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setLong(1, styleId);
		        stmt.setLong(2, userId);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            return rs.getInt(1) > 0;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;
		}


	
	public List<Style> getAllStyles() {
	    List<Style> styleList = new ArrayList<>();
	    try {
	        connection = dataSource.getConnection();
	        stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT * FROM makeupstyle");

	        while (rs.next()) {
	            styleList.add(new Style(
	                rs.getLong("id"),
	                rs.getString("name"),
	                rs.getString("description"),
	                rs.getString("image_url"),
	                rs.getString("category"),
	                rs.getLong("category_id")
	            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeConnection();
	    }
	    return styleList;
	}
	
	
	public List<Style> filterStyles(String query) {
	    List<Style> filterList = new ArrayList<>();
	    String sql = "SELECT * FROM makeupstyle WHERE name LIKE ? OR description LIKE ? OR category LIKE ? OR CAST(id AS CHAR) LIKE ? OR CAST(category_id AS CHAR) LIKE ?";

	    try {
	        connection = dataSource.getConnection();
	        pStmt = connection.prepareStatement(sql);
	        String likeQuery = "%" + query + "%";
	        pStmt.setString(1, likeQuery);
	        pStmt.setString(2, likeQuery);
	        pStmt.setString(3, likeQuery);
	        pStmt.setString(4, likeQuery);
	        pStmt.setString(5, likeQuery);
	        rs = pStmt.executeQuery();

	        while (rs.next()) {
	            filterList.add(new Style(
	                rs.getLong("id"),
	                rs.getString("name"),
	                rs.getString("description"),
	                rs.getString("image_url"),
	                rs.getString("category"),
	                rs.getLong("category_id")
	            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeConnection();
	    }
	    return filterList;
	}

	public boolean updateStyle(Style style) {
	    boolean updateOk = false;
	    try {
	        connection = dataSource.getConnection();

	        pStmt = connection.prepareStatement("UPDATE makeupstyle SET name=?, description=?, image_url=?, category=?, category_id=?, youtubeLink=? WHERE id=?");
	        pStmt.setString(1, style.getName());
	        pStmt.setString(2, style.getDescription());
	        pStmt.setString(3, style.getImage_url());
	        pStmt.setString(4, style.getCategory());

	        if (style.getCategory_id() != null) {
	            pStmt.setLong(5, style.getCategory_id());
	        } else {
	            pStmt.setNull(5, Types.BIGINT); 
	        }

	        pStmt.setString(6, style.getYoutubeLink());
	        pStmt.setLong(7, style.getId());

	        int rowEffected = pStmt.executeUpdate();
	        if (rowEffected > 0) {
	            updateOk = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeConnection();
	    }
	    return updateOk;
	}

	public Style getStyleById(Long styleId) {
		Style style = null;
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from makeupstyle where id = '" + styleId + "';");

			while (rs.next()) {
				style = new Style(rs.getLong("id"), rs.getString("name"), rs.getString("description"),
						rs.getString("image_url"), rs.getString("category"), rs.getLong("category_id"),rs.getString("youtubeLink"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return style;
	}

	public boolean createStyle(Style style) {
	    boolean insertOk = false;
	    try {
	        connection = dataSource.getConnection();

	        Long categoryId = getCategoryId(style.getCategory());

	        pStmt = connection.prepareStatement("INSERT INTO makeupstyle (name, description, image_url, category, category_id, youtubeLink) VALUES (?, ?, ?, ?, ?, ?)");
	        pStmt.setString(1, style.getName());
	        pStmt.setString(2, style.getDescription());
	        pStmt.setString(3, style.getImage_url());
	        pStmt.setString(4, style.getCategory());

	        if (categoryId != null) {
	            pStmt.setLong(5, categoryId);
	        } else {
	            pStmt.setNull(5, java.sql.Types.BIGINT);
	        }

	        pStmt.setString(6, style.getYoutubeLink()); // Set YouTube link

	        int rowEffected = pStmt.executeUpdate();
	        if (rowEffected > 0) {
	            insertOk = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeConnection();
	    }
	    return insertOk;
	}

	private Long getCategoryId(String categoryName) throws SQLException {
	    Long categoryId = null;
	    String selectQuery = "SELECT id FROM category WHERE name = ?";
	    String insertQuery = "INSERT INTO category (name) VALUES (?)";
	    
	    try {
	        // Check if category exists
	        pStmt = connection.prepareStatement(selectQuery);
	        pStmt.setString(1, categoryName);
	        rs = pStmt.executeQuery();
	        if (rs.next()) {
	            categoryId = rs.getLong("id");
	        } else {
	            // Insert new category
	            pStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
	            pStmt.setString(1, categoryName);
	            pStmt.executeUpdate();
	            rs = pStmt.getGeneratedKeys();
	            if (rs.next()) {
	                categoryId = rs.getLong(1);
	            }
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pStmt != null) pStmt.close();
	    }
	    return categoryId;
	}
	
	public boolean deleteStyle(Long styleId) {
	    boolean deleteOk = false;
	    try {
	        connection = dataSource.getConnection();

	        pStmt = connection.prepareStatement("DELETE FROM user_like WHERE style_id = ?;");
	        pStmt.setLong(1, styleId);
	        pStmt.executeUpdate(); 

	        pStmt = connection.prepareStatement("DELETE FROM makeupstyle WHERE id = ?;");
	        pStmt.setLong(1, styleId);

	        int rowEffected = pStmt.executeUpdate();
	        if (rowEffected > 0) {
	            deleteOk = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeConnection();
	    }

	    return deleteOk;
	}


}