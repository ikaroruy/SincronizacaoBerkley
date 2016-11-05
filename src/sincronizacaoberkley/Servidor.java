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
public class Servidor extends Thread
{
    private final Berkley ms;
    private final int sleepMSegundos;
    private long tempoServidor;
    
    public Servidor(Berkley sm){
        this.ms = sm;
        this.sleepMSegundos = 10000;
        this.tempoServidor = 0;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(this.sleepMSegundos); //Dorme 10 segundos
                //Acorda, configurando sua hora, avisando aos clientes
                System.out.println("Temporização (servidor) : " + this.tempoServidor);
                this.ms.setTempoServidor(this.tempoServidor);
               
                //Uma vez acordado, os clientes terão configurado as diferenças em um vetor e então 
                //deverá ser calculado a média
                this.ms.calcAndSetMedia();
                
                //Ajustando a hora do servidor (tempo do servidor + média)
                this.tempoServidor += this.ms.getMedia();
                
                //Nova temporização acordada
                System.out.println("Temporização acordada (servidor) : " + this.tempoServidor);           
                
                //Restaurando o estado inicial do servidor, quando ele finaliza
                this.ms.reiniciaProcesso();
            }catch(InterruptedException e){} 
        }
    }
}
