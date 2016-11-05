/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sincronizacaoberkley;

/**
 *
 * @author Ykaro
 */
public class Principal
{
    public static void main(String args[]){
        Berkley ms = new Berkley();
      
        Servidor srv = new Servidor(ms);
        srv.start();
        Cliente clv[] = new Cliente[3];
      
        for(int i=0;i<3;i++){
            clv[i] = new Cliente(i,ms);
            clv[i].start();
        }
            
    }
}
