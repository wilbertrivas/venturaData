/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package Consumo.View;
 
import Consumo.Controller.ControlDB_Insumo;
import Consumo.Model.Insumo;
import java.sql.SQLException;

/**fg
 *
 * @author wrivas
 */
public class Testing {
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       String[] lista={"1","2","3","4","5"};
        for(String dato:lista){
            System.out.println(dato);
        }
       
    }
}
