package proyectofinalb;
import java.sql.*;
public class ConexionBD {
   private static Connection conexion=null;
    private static PreparedStatement pstm; 
    private static ResultSet rs;

private static ConexionBD cbd;
public static ConexionBD getConexionBD(){
    if(cbd==null){
        cbd = new ConexionBD();
    }
    return cbd;
}
//private ConexionBD(){}
    
    /*
  String cadena = "jdbc:postgresql://localhost:5433/pruebaReportes";
             String user ="postgres";
             String pass = "097073290";
              try {
                  Class.forName("org.postgresql.Driver");
    */
    private ConexionBD() {
	try {
            Class.forName("org.postgresql.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //String URL = "jdbc:mysql://localhost:3306/sakila";
            String URL = "jdbc:postgresql://localhost:5432/empresaPABD";
            conexion = DriverManager.getConnection(URL, "postgres", "admin");
            } catch (ClassNotFoundException e) {
		System.out.println("Error de DRIVER");
            } catch (SQLException e) {
		System.out.println("Error de conexion en MySQL");
            }
	}
	public static Connection getConexion(){
            if (conexion == null){
                new ConexionBD();
            }
            return conexion;
   }
	static void cerrarConexion() {
            try {
		pstm.close();
		conexion.close();
            } catch (SQLException e) {
		System.out.println("Error al cerrar la conexion");
		e.printStackTrace();
            }
	}
	
	
    public boolean checarPrimerUsuario() {
	String usuario = "";
            try {
                Statement sql = ConexionBD.getConexion().createStatement();
		ResultSet res = sql.executeQuery("select * from usuario;");
		while(res.next()) {
                    usuario = res.getString(2);
		}
            } catch (Exception e) {
		System.out.println(e.toString());
            }
            if(usuario.equals(""))
		return true;
            else
		return false;
	}
    
    public boolean verificarSiExiste(String tabla, String complemento) {
	boolean retorno = true;
            try {
		Statement sql = ConexionBD.getConexion().createStatement();
		ResultSet res = sql.executeQuery("select * from "+tabla+" WHERE "+complemento+";");
		if(res.next())
                    retorno = true;
		else
                    retorno = false;
		} catch (Exception e) {
                    System.out.println(e.toString());
		}
		return retorno;
	}
    
    public void agregarRegistro(String complemento) {
	try {
            String instruccion = "INSERT INTO "+complemento+";";
            pstm = conexion.prepareStatement(instruccion);
            pstm.execute();
	} catch (Exception e) {
            System.out.println(e.toString());
            }
	}
    
    public String[] obtenerRegistro() {
	String[] retorno = new String[6];
	boolean bandera = true;
	try {
            Statement sql = ConexionBD.getConexion().createStatement();
            ResultSet res = sql.executeQuery("select * from usuario;");
            while(res.next()) {
		for(int i=0; i<6; i++) {
                    try {
			retorno[i] = res.getString(i+1);
                    }catch(Exception e) {
			break;
		}
		}
		}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return retorno;
	}
    
    public String contarOcurrencias(String tabla, String complemento) {
	String retorno = "";
	try {
            Statement sql = ConexionBD.getConexion().createStatement();
            ResultSet res = sql.executeQuery("select count(*) AS cuenta from "+tabla+" WHERE "+complemento+";");
            while(res.next()) {
		retorno = res.getString(1);
		}
	} catch (Exception e) {
            System.out.println(e.toString());
	}
            return retorno;
	}
    
    public String[][] obtenerRegistros(String tabla, String complemento) {
        String[][] retorno = new String[Integer.parseInt(contarOcurrencias(tabla, complemento))][6];
	boolean bandera = true;
	int cont = 0;
	try {
            Statement sql = ConexionBD.getConexion().createStatement();
            ResultSet res = sql.executeQuery("select * from "+tabla+" WHERE "+complemento+";");
            while(res.next()) {
                for(int i=0; i<6; i++) {
		try {
                    retorno[cont][i] = res.getString(i+1);
                    }catch(Exception e) {
			break;
                    }
		}
                cont++;
            }
	} catch (Exception e) {
            System.out.println(e.toString());
	}
            return retorno;
	}
    
        public String[][] obtenerRegistrosEmpleados(String tabla, String complemento) {
        String[][] retorno = new String[Integer.parseInt(contarOcurrencias(tabla, complemento))][10];
	boolean bandera = true;
	int cont = 0;
	try {
            Statement sql = ConexionBD.getConexion().createStatement();
            ResultSet res = sql.executeQuery("select * from "+tabla+" WHERE "+complemento+";");
            while(res.next()) {
                for(int i=0; i<10; i++) {
		try {
                    retorno[cont][i] = res.getString(i+1);
                    }catch(Exception e) {
			break;
                    }
		}
                cont++;
            }
	} catch (Exception e) {
            System.out.println(e.toString());
	}
            return retorno;
	}
        
        public String[][] obtenerRegistrosDepartamentos(String tabla, String complemento) {
        String[][] retorno = new String[Integer.parseInt(contarOcurrencias(tabla, complemento))][10];
	boolean bandera = true;
	int cont = 0;
	try {
            Statement sql = ConexionBD.getConexion().createStatement();
            ResultSet res = sql.executeQuery("select * from "+tabla+" WHERE "+complemento+";");
            while(res.next()) {
                for(int i=0; i<4; i++) {
		try {
                    retorno[cont][i] = res.getString(i+1);
                    }catch(Exception e) {
			break;
                    }
		}
                cont++;
            }
	} catch (Exception e) {
            System.out.println(e.toString());
	}
            return retorno;
	}
    
    public void editarRegistro(String tabla, String datosACambiar, String donde) {
		try {
			String instruccion = "UPDATE "+tabla+" set "+datosACambiar+" WHERE "+donde+";";
			pstm = conexion.prepareStatement(instruccion);
			pstm.execute();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
    
    public void eliminarRegistro(String tabla, String complemento) {
		try {
			String instruccion = "delete from "+tabla+" WHERE "+complemento+";";
			pstm = conexion.prepareStatement(instruccion);
			pstm.execute();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
    
    public void mensajeDirecto(String mensaje) {
		try {
			String instruccion = mensaje;
			pstm = conexion.prepareStatement(instruccion);
			pstm.execute();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
