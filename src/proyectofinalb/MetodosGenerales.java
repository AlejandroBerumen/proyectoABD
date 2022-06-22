/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinalb;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MetodosGenerales {
    KeyAdapter noEspaciosNiCaracteresRaros = new KeyAdapter() {
		public void keyTyped(KeyEvent key) {
			char presionada = key.getKeyChar();
			if((presionada<48 || presionada>57) && (presionada<65 || presionada>90) && (presionada<97 || presionada>122) && presionada!='_' && presionada!='ñ' && presionada!='Ñ') {
				key.consume();
			}
		}
	};
	
	KeyAdapter soloNumeros = new KeyAdapter() {
		public void keyTyped(KeyEvent key) {
			char presionada = key.getKeyChar();
			if((presionada<48 || presionada>57)) {
				key.consume();
			}
		}
	};
        
        KeyAdapter soloNumerosDecimales = new KeyAdapter() {
		public void keyTyped(KeyEvent key) {
			char presionada = key.getKeyChar();
			if((presionada<48 || presionada>57) && presionada!='.') {
				key.consume();
			}
		}
	};
	
	KeyAdapter soloLetrasYEspacios = new KeyAdapter() {
		public void keyTyped(KeyEvent key) {
			char presionada = key.getKeyChar();
			if((presionada<65 || presionada>90) && (presionada<97 || presionada>122) && presionada!=' ' && presionada!='ñ' && presionada!='Ñ') {
				key.consume();
			}
		}
	};
}
