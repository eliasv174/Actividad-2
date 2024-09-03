package modelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
        


public class Cliente extends Persona {
    private int id;
    private String nit;
    Conexion cn;

    public Cliente() {}

    public Cliente(int id,String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.nit = nit;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    
    @Override
    public void crear(){
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion(); 
            String query = "INSERT INTO clientes (nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES (?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1,getNit());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getFecha_nacimiento()); 
            
            int executar = parametro.executeUpdate();
            System.out.println("Ingreso Exitoso: "+ Integer.toString(executar));
            
            
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            // Mensaje de error de la excepción
            System.out.println("Algo salió mal: " + ex.getMessage());
        }
      
    }    
    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
        cn = new Conexion();
        cn.abrir_conexion();
        String query = "Select * from clientes";
        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
        String encabezado[] = {"id","nit","nombres","apellidos","direccion","telefono","nacimiento"};
        tabla.setColumnIdentifiers(encabezado);
        String datos[] = new String[7];
        while(consulta.next()){
            datos[0]= consulta.getString("id_clientes");
            datos[1]= consulta.getString("nit");
            datos[2]= consulta.getString("nombres");
            datos[3]= consulta.getString("apellidos");
            datos[4]= consulta.getString("direccion");
            datos[5]= consulta.getString("telefono");
            datos[6]= consulta.getString("fecha_nacimiento");
            tabla.addRow(datos);
        }
        
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println("Error en leer: "+ ex.getMessage());
    }
    return tabla;
    }
    
    
    @Override
    public void actualizar(){
    try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion(); 
            String query = "UPDATE clientes SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE `id_clientes` = ?;";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1,getNit());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getFecha_nacimiento()); 
            parametro.setInt(7,getId());
            
            int executar = parametro.executeUpdate();
            System.out.println("Modificacion Exitosa: "+ Integer.toString(executar));
            
            
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            // Mensaje de error de la excepción
            System.out.println("Algo salió mal al actualizar: " + ex.getMessage());
        }
    }
    
    @Override
    public void borrar(){
    try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion(); 
            String query = "DELETE from clientes WHERE `id_clientes` = ?;";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setInt(1,getId());
            
            int executar = parametro.executeUpdate();
            System.out.println("Eliminacion Exitosa: "+ Integer.toString(executar));
            
            
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            // Mensaje de error de la excepción
            System.out.println("Algo salió mal al borrar: " + ex.getMessage());
        }
    }
    
    
}

