/**********************************************
Workshop 6 & 7
Course: APD545-Fall2024
Last Name: Mohammed
First Name: Adnan
ID: 174731216
Section: ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:26th November 2024
**********************************************/

package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.abstractClasses.Part;
import application.models.InHouse;
import application.models.Outsourced;
import application.models.Products;
import javafx.collections.ObservableList;

public class Database {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/inventorymanagement?useSSL=false";
	private static final String DB_USERNAME = "Adnan";
	private static final String DB_PASSWORD = "Seneca@123";
	private static final String DB_CREATE_PART_TABLE_QRY = "CREATE TABLE IF NOT EXISTS Parts("
			+ "id INTEGER PRIMARY KEY, name VARCHAR(50) NOT NULL, price DOUBLE NOT NULL,"
			+ "stock INTEGER NOT NULL, min INTEGER NOT NULL, max INTEGER NOT NULL,"
			+ "type ENUM('InHouse', 'Outsourced') NOT NULL, machine_id INTEGER DEFAULT NULL,"
			+ "company_name VARCHAR(50) DEFAULT NULL)";
	private static final String PART_INSERT_QRY = "INSERT INTO Parts (id, name, price, stock, min, max, type, machine_id, company_name) VALUES"
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String PART_SELECT_QRY = "SELECT * FROM  Parts";
	private static final String Part_DELETE_QRY = "DELETE FROM Parts";
	
	private static final String DB_CREATE_PRODUCT_TABLE_QRY = "CREATE TABLE IF NOT EXISTS Products ("
	        + "id INTEGER PRIMARY KEY, name VARCHAR(50) NOT NULL, price DOUBLE NOT NULL, "
	        + "stock INTEGER NOT NULL, min INTEGER NOT NULL, max INTEGER NOT NULL)";

	private static final String DB_CREATE_PRODUCT_PARTS_TABLE_QRY = "CREATE TABLE IF NOT EXISTS ProductParts ("
	        + "product_id INTEGER NOT NULL, part_id INTEGER NOT NULL, "
	        + "PRIMARY KEY (product_id, part_id), "
	        + "FOREIGN KEY (product_id) REFERENCES Products(id), "
	        + "FOREIGN KEY (part_id) REFERENCES Parts(id))";

	private static final String PRODUCT_INSERT_QRY = "INSERT INTO Products (id, name, price, stock, min, max) VALUES "
	        + "(?, ?, ?, ?, ?, ?)";

	private static final String PRODUCT_PARTS_INSERT_QRY = "INSERT INTO ProductParts (product_id, part_id) VALUES (?, ?)";

	private static final String PRODUCT_SELECT_QRY = "SELECT * FROM Products";

	private static final String PRODUCT_PARTS_SELECT_QRY = "SELECT part_id FROM ProductParts WHERE product_id = ?";

	private static final String PRODUCT_DELETE_QRY = "DELETE FROM Products";
	
	private static final String PRODUCT_PARTS_DELETE_QRY = "DELETE FROM ProductParts";
	
	public void createPartsTable() {
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){
			PreparedStatement ps = conn.prepareStatement(DB_CREATE_PART_TABLE_QRY);
			ps.execute();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertParts(ObservableList<Part> allParts) {
		deleteParts();
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){
			PreparedStatement ps = conn.prepareStatement(PART_INSERT_QRY);
			for(Part e : allParts) {
				ps.setInt(1, e.getId());
				ps.setString(2, e.getName());
				ps.setDouble(3, e.getPrice());
				ps.setInt(4, e.getStock());
				ps.setInt(5, e.getMin());
				ps.setInt(6, e.getMax());
				if(e instanceof InHouse) {
					ps.setString(7, "InHouse");
					ps.setInt(8, e.getMachineID());
					ps.setNull(9, java.sql.Types.VARCHAR);
				}else if(e instanceof Outsourced) {
					ps.setString(7, "Outsourced");
					ps.setNull(8, java.sql.Types.INTEGER);
					ps.setString(9, e.getCompanyName());
				}
				
				ps.executeUpdate();
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Part> loadParts() {
		List<Part> loadedList = new ArrayList<Part>();
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){
			PreparedStatement ps = conn.prepareStatement(PART_SELECT_QRY);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				if(res.getString("type").equals("InHouse")) {
					loadedList.add(new InHouse(res.getInt("id"), res.getString("name"),
							res.getDouble("price"), res.getInt("stock"), res.getInt("min"),
							res.getInt("max"), res.getInt("machine_id")));
				}else if(res.getString("type").equals("Outsourced")) {
					loadedList.add(new Outsourced(res.getInt("id"), res.getString("name"),
							res.getDouble("price"), res.getInt("stock"), res.getInt("min"),
							res.getInt("max"), res.getString("company_name")));
				}
				
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return loadedList;
	}
	
	private void deleteParts() {
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){
			PreparedStatement ps = conn.prepareStatement(Part_DELETE_QRY);
			ps.execute();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void createProductTables() {
	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	        PreparedStatement ps = conn.prepareStatement(DB_CREATE_PRODUCT_TABLE_QRY);
	        ps.execute();

	        ps = conn.prepareStatement(DB_CREATE_PRODUCT_PARTS_TABLE_QRY);
	        ps.execute();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void insertProducts(ObservableList<Products> allProducts) {
		deleteProducts();
	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	        PreparedStatement psProduct = conn.prepareStatement(PRODUCT_INSERT_QRY);
	        PreparedStatement psProductParts = conn.prepareStatement(PRODUCT_PARTS_INSERT_QRY);

	        for (Products product : allProducts) {
	            psProduct.setInt(1, product.getId());
	            psProduct.setString(2, product.getName());
	            psProduct.setDouble(3, product.getPrice());
	            psProduct.setInt(4, product.getStock());
	            psProduct.setInt(5, product.getMin());
	            psProduct.setInt(6, product.getMax());
	            psProduct.executeUpdate();

	            for (Part part : product.getAllAssociatedParts()) {
	                psProductParts.setInt(1, product.getId());
	                psProductParts.setInt(2, part.getId());
	                psProductParts.executeUpdate();
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	public List<Products> loadProducts() {
	    List<Products> loadedProducts = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	        PreparedStatement psProduct = conn.prepareStatement(PRODUCT_SELECT_QRY);
	        ResultSet rsProduct = psProduct.executeQuery();

	        while (rsProduct.next()) {
	            Products product = new Products(
	                    rsProduct.getInt("id"),
	                    rsProduct.getString("name"),
	                    rsProduct.getDouble("price"),
	                    rsProduct.getInt("stock"),
	                    rsProduct.getInt("min"),
	                    rsProduct.getInt("max")
	            );

	            PreparedStatement psProductParts = conn.prepareStatement(PRODUCT_PARTS_SELECT_QRY);
	            psProductParts.setInt(1, product.getId());
	            ResultSet rsParts = psProductParts.executeQuery();

	            while (rsParts.next()) {
	                int partId = rsParts.getInt("part_id");
	                Part part = findPartById(partId);
	                product.addAssociatedPart(part);
	            }

	            loadedProducts.add(product);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return loadedProducts;
	}
	
	private Part findPartById(int num) {
		for (Part e : loadParts()) {
			if(e.getId() == num) {
				return e;
			}
		}
		return null;
	}
	
	private void deleteProducts() {
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){
			PreparedStatement ps = conn.prepareStatement(PRODUCT_DELETE_QRY);
			ps.execute();
			
			ps = conn.prepareStatement(PRODUCT_PARTS_DELETE_QRY);
			ps.execute();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

}
