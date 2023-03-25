package plantillasCodigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import tema8Ejercicio4_BD_JTable.ConnectionSingleton;

/**
 * @author FERNANDO ROMERO DE ÁVILA - 1º DAW 2022-23
 */
public class PlantillasCodigo {

    
    
    /* PERMITA ADAPTAR ASPECTO DE VENTANAS SWING EN MAC */
    try
    {
	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }catch(
    Exception e)
    {
	    e.printStackTrace();
	}
    
    
    

    /* PERMITE LIBERAR MEMORIA USANDO SOLO UNA CONEXION A BD CON LA CLASE CONNECTION SINGLETON */
	public class ConnectionSingleton {
	    private static Connection con;
	    public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1/prueba";
		String user = "root";
		String password = "Solana12023";
		if (con == null || con.isClosed()) {
		    con = DriverManager.getConnection(url, user, password);
		}
		return con;
	    }
	}


	// Connection con=ConnectionSingleton.getConnection();
    
    
    
    /* OBJETO JTABLE PARA CREAR TABLAS CON FORMATO EN SWING */
	DefaultTableModel model = new DefaultTableModel();
	model.addColumn("ID");
	model.addColumn("Nombre");
	model.addColumn("Edad");

	Connection con;
	try {
	    con = ConnectionSingleton.getConnection();
	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT * FROM persona");

	    while (rs.next()) {
		Object[] row = new Object[3];
		row[0] = rs.getInt("id");
		row[1] = rs.getString("nombre");
		row[2] = rs.getInt("edad");
		model.addRow(row);
	    }

	    tableBD = new JTable(model);
	    tableBD.setBounds(33, 242, 373, -205);
	    frame.getContentPane().add(tableBD);
	    tableBD.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    
	    JScrollPane scrollPane = new JScrollPane(tableBD);
	    scrollPane.setBounds(53, 38, 245, 133);
	    frame.getContentPane().add(scrollPane);
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    
    
    
    
    
    
    
    
    
}

