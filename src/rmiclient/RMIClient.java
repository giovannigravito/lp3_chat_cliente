/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiclient;

import com.br.lp3.batepapo.Mensagem;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 31427782
 */
public class RMIClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry("172.16.16.181", 1099);
            //Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            Mensagem servico = (Mensagem) registro.lookup("Mensagem");
            
            System.out.println("Qual seu nome?");
            Scanner Sc = new Scanner(System.in);
            String nome = Sc.nextLine();            
           
                        
            servico.conecta(nome);
            
           
            String msg = "";
            do{
                msg = Sc.nextLine();
                if(msg.equalsIgnoreCase("quem?")){
                    List<String> lista = servico.getUsuarios();
                    for (String nomeU : lista) {
                        System.out.println(nomeU);                        
                    }
                    System.out.println("------------");
                } else if(msg.equalsIgnoreCase("para")){
                    System.out.println("Destinatário: ");
                    String dest = Sc.nextLine();
                    System.out.println("Mensagem: ");
                    msg = Sc.nextLine();
                    servico.diz(nome, dest, msg);
                  }else{
                    servico.diz(nome, msg);
                }
            }while(!msg.equalsIgnoreCase("sair"));
            servico.desconecta(nome);
            
        } catch (RemoteException | NotBoundException ex ) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
