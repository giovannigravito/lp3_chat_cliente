
package com.br.lp3.batepapo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author 31427782
 */
public interface Mensagem extends Remote{
    public void conecta(String nome) throws RemoteException;
    public void diz(String remetente, String msg) throws RemoteException;
    public void diz(String remetente, String destinatario, String msg) throws RemoteException;
    public void desconecta(String nome) throws RemoteException;
    public List<String> getUsuarios() throws RemoteException;
}
