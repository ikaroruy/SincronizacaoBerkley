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

public class Berkley
{
    private long tempoServidor;
    private final long[] diferencaTempos;
    private long somaDiferenca; 
    private final int numeroClientes = 3;
    private int countClientesOperando;
    
    public Berkley(){
        this.tempoServidor = 0;
        this.countClientesOperando = this.numeroClientes;
        this.diferencaTempos = new long[this.numeroClientes];
        this.somaDiferenca = 0;
    }
    
    public synchronized void setTempoServidor(long tempoServidor){ 
        this.tempoServidor = tempoServidor; 
        try{
            notifyAll();              
            wait();                   
        }catch(InterruptedException e){}
    }
    
    public synchronized void setDiferencaTempos(long time,int n){
        try{
            if(tempoServidor==0) wait();              
            this.diferencaTempos[n] = (time-tempoServidor);  //Vetor de diferenças
            this.somaDiferenca    += time;              
            countClientesOperando--;  //decrementa o contador de clientes                
           
            if(countClientesOperando==0) notify();  //se todos os clientes tem operado, servidor desperta  
            wait();                                
        }catch(InterruptedException e){}
    }
    
    public synchronized void calcAndSetMedia(){
        long avg = (this.somaDiferenca / (this.numeroClientes+1));
        for(int i=0;i<this.numeroClientes;i++) this.diferencaTempos[i] = ((-this.diferencaTempos[i]) + avg);
        notifyAll();
    }
    
    public synchronized long getTempoSetado(int n){ return this.diferencaTempos[n]; }
    public synchronized long getMedia()         { return this.somaDiferenca / (this.numeroClientes+1); }
    public synchronized void reiniciaProcesso(){
        this.tempoServidor = 0;
        this.countClientesOperando = this.numeroClientes;
        this.somaDiferenca = 0;
    }
    
    
}

