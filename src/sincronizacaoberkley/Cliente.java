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
public class Cliente extends Thread
{
    private final int idCliente;
    private long tempoCliente;
    private final Berkley ms;
    private final boolean addAtraso = true;
    public Cliente(int clientID,Berkley sm){
        this.ms         = sm;
        this.idCliente   = clientID;
        this.tempoCliente = 0;
    }
    
    @Override
    public void run(){
        while(true){ //clientes conectados 
                
            //Atualizar tempo e adicionar um retardo para cada thread caso addAtraso = true;
                this.tempoCliente += (this.addAtraso) ?(this.idCliente+1)*2 : 0;
                System.out.println("Temporização (cliente " + idCliente + ") : " + this.tempoCliente);
                this.ms.setDiferencaTempos(this.tempoCliente,this.idCliente); //setar as diferenças, se o servidor não tem configurado seu tempo, os clientes dormem
                this.tempoCliente += this.ms.getTempoSetado(this.idCliente); //atualizando relogio dos clientes
                System.out.println("Temporização acordada (cliente " + idCliente + ") : " + this.tempoCliente);
        }
    }
                 
}
