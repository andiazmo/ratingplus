/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package utilidades.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SFTPHelper {
    private String user;
    private String host;
    private int port;
    private String pass;
    private String localPath;
    private String sftpPath;
    //    private Session session;
//    private ChannelSftp sftp;
    
    public SFTPHelper(String user, String host, int port, String pass, String localPath, String sftpPath) {
        this.user = user;
        this.host = host;
        this.port = port;
        this.pass = pass;
        this.localPath = localPath;
        this.sftpPath = sftpPath;
    }
    
    
    
    public void sendLite(String fileName){
        try{
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            UserInfo ui = new SUserInfo(pass, null);
            
            session.setUserInfo(ui);
            session.setPassword(pass);
            session.connect();
            
            ChannelSftp sftp = (ChannelSftp)session.openChannel("sftp");
            sftp.connect();
            
//            sftp.cd("/home/Factiva/cupos/sftp/dialogo");
            sftp.cd(sftpPath);
            System.out.println("Subiendo c:/ejemplo.txt ...");
//            sftp.put("C:/cupos/sftp/dialogo/ejemplo.txt", "ejemplo.txt");
            sftp.put(localPath+fileName, fileName);
            
            System.out.println("Archivos subidos.");
            
            sftp.exit();
            sftp.disconnect();
            session.disconnect();
            
            System.out.println("----------------- FIN");
        }
        catch (SftpException ex) {
            Logger.getLogger(SFTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSchException ex) {
            Logger.getLogger(SFTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean receive(String fileName){
        try{
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            UserInfo ui = new SUserInfo(pass, null);
            
            session.setUserInfo(ui);
            session.setPassword(pass);
            session.connect();
            
            ChannelSftp sftp = (ChannelSftp)session.openChannel("sftp");
            sftp.connect();
            
//            sftp.cd("/home/Factiva/cupos/sftp/dialogo");
            sftp.cd(sftpPath);
            System.out.println("buscando archivo: "+localPath+fileName);
//            sftp.put("C:/cupos/sftp/dialogo/ejemplo.txt", "ejemplo.txt");
            sftp.get(fileName, localPath+fileName);
            
            System.out.println("Archivos subidos.");
            
            sftp.exit();
            sftp.disconnect();
            session.disconnect();
            
            System.out.println("----------------- FIN");
            return true;
        }
        catch (SftpException ex) {
            Logger.getLogger(SFTPHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (JSchException ex) {
            Logger.getLogger(SFTPHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
}
