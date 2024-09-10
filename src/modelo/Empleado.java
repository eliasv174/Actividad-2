package modelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
        


public class Empleado extends Persona {
    private int id;
    private String codigo;
    private String puesto;
    Conexion cn;

    public Empleado() {}

    public Empleado(int id,String codigo,String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento,String puesto) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.codigo = codigo;
        this.puesto = puesto;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    @Override
    public void crear(){
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion(); 
            String query = "INSERT INTO empleados (codigo,nombres,apellidos,direccion,telefono,fecha_nacimiento,puesto) VALUES (?,?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1,getPuesto());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getFecha_nacimiento()); 
            parametro.setString(7,getPuesto());
            
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
        String query = "Select * from empleados";
        ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
        String encabezado[] = {"id","Codigo","nombres","apellidos","direccion","telefono","nacimiento","Puesto"};
        tabla.setColumnIdentifiers(encabezado);
        String datos[] = new String[8];
        while(consulta.next()){
            datos[0]= consulta.getString("id_empleados");
            datos[1]= consulta.getString("Codigo");
            datos[2]= consulta.getString("nombres");
            datos[3]= consulta.getString("apellidos");
            datos[4]= consulta.getString("direccion");
            datos[5]= consulta.getString("telefono");
            datos[6]= consulta.getString("fecha_nacimiento");
            datos[7]= consulta.getString("id_puesto");
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
            String query = "UPDATE empleados SET codigo = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, id_puesto = ? WHERE `id_empleados` = ?;";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1,getCodigo());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getFecha_nacimiento()); 
            parametro.setInt(7,getId());
            parametro.setString(8,getPuesto());
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
            String query = "DELETE from empleados WHERE `id_empleados = ?;";
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
