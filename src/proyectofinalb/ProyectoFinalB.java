
package proyectofinalb;

import javax.swing.JOptionPane;
public class ProyectoFinalB {
    static VentanaPrincipal vp = new VentanaPrincipal();
    static CambiarUsuario cu = new CambiarUsuario(true);
    public static void main(String[] args) {
        ConexionBD cbd = ConexionBD.getConexionBD();
        vp.setVisible(true);
        
        if(cbd.checarPrimerUsuario()){
            vp.lblUser.setText("                          ");
            JOptionPane.showMessageDialog(vp.jPanel1.getRootPane(),"Bienvenido!\nPara empezar a utilizar el sistema, por favor, registre un usuario.");
            vp.jMenuBar1.setVisible(false);
            vp.jPanel1.add(new AgregarUsuario(true));
        }else{
            vp.jPanel1.add(cu);
            vp.jMenuBar1.setVisible(false);
            vp.lblUser.setText("                          ");
        }
    }
    public void iniciar(String x){
            cu.ingresado(vp.jMenuBar1, vp.lblUser);
            ConexionBD cbd = ConexionBD.getConexionBD();
            if(cbd.obtenerRegistros("usuario", "nombre='"+x+"'")[0][3].equals("0")){
                vp.jMenuItem2.setEnabled(false);
                //vp.jMenuItem3.setEnabled(false);
                vp.jMenuItem1.setEnabled(false);
            }else{
                vp.jMenuItem2.setEnabled(true);
                //vp.jMenuItem3.setEnabled(true);
                vp.jMenuItem1.setEnabled(true);
            }
        }
    public void cambiarUsuario(String x){
            vp.lblUser.setText("Usuario activo:  "+x);
            ConexionBD cbd = ConexionBD.getConexionBD();
            if(cbd.obtenerRegistros("usuario", "nombre='"+x+"'")[0][3].equals("0")){
                vp.jMenuItem2.setEnabled(false);
                //vp.jMenuItem3.setEnabled(false);
                vp.jMenuItem1.setEnabled(false);
            }else{
                vp.jMenuItem2.setEnabled(true);
                //vp.jMenuItem3.setEnabled(true);
                vp.jMenuItem1.setEnabled(true);
            }
    }
    
    
}
