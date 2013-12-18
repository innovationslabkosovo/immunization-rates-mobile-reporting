/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vaccination;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 * @author root
 */

public class Kerkesat extends MIDlet implements CommandListener,ItemCommandListener{
    
    private Display display;
    private Form form1,form2,form3,result,login;
    private Command next1,next2,submit;
    private Command back1,back2;
    private Command log;
    private TextField bcg_m,bcg,hep_1,opv_1,opv_2,opv_3,dpt_hib1,dpt_hib2,dpt_hib3;
    private TextField mmr, opv_r1,dpt_r1,opv_r2,dt,mmr_r,opv_r3,td,tt;
    private TextField user, pass;
    private RecordStore Fmc_emri;
    private String fmc;
    private RecordStore Imei;
    private String imei;
    private StringItem si_log;
    private RecordStore Users_rs;
    private RecordStore Passs_rs;
    private Object text;
    private Alert alert;

    public Kerkesat(){
        
        display = Display.getDisplay(this);
        
//        Declare The Forms

        form1 = new Form("Kerkesat");
        form2 = new Form("Kerkesat");
        form3 = new Form("Kerkesat");
        result = new Form("Final");
        login = new Form("Hyrja");
 
     
//        Declare The Text Fields
        bcg_m = new TextField("Sasia:","",6,TextField.NUMERIC);
        bcg = new TextField("Sasia:","",6,TextField.NUMERIC);
        hep_1 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        opv_1 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        opv_2 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        opv_3 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        dpt_hib1 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        dpt_hib2 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        dpt_hib3 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        mmr = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        opv_r1 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        dpt_r1 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        opv_r2 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        dt = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        mmr_r = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        opv_r3 = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        td = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        tt = new TextField("Sasia:", "", 6, TextField.NUMERIC);
        user = new TextField("Perdoruesi", "", 8,TextField.ANY);
        user.setLayout(TextField.LAYOUT_CENTER);
        pass = new TextField("Fjalekalimi", "", 8,TextField.PASSWORD);
        
        
        si_log = new StringItem("Kyqu ","Kyqu",Item.BUTTON);
        si_log.setLayout(Item.LAYOUT_CENTER);
        
//        Declare The Commands
        next1 = new Command("Vazhdo",Command.SCREEN,1);
        next2 = new Command("Vazhdo",Command.SCREEN,2);
        submit = new Command("Dergo",Command.OK,1);
        log = new Command("Kyqu" , Command.SCREEN, 1);
        
        back1 = new Command("Kthehu",Command.BACK,1);
        back2 = new Command("Kthehu",Command.BACK,1);
        
        login.append(user);
        login.append(pass);
        login.append(si_log);
        
//        Append the Text Fields to Forms
        
        form1.append("BCG ne maternitet");
        form1.append(bcg_m);
        form1.append("BCG");
        form1.append(bcg);
        form1.append("HEP 1 ne maternitet");
        form1.append(hep_1);
        form1.append("OPV 1");
        form1.append(opv_1);
        form1.append("OPV 2");
        form1.append(opv_2);
        form1.append("OPV 3");
        form1.append(opv_3);
        
        form2.append("DPT1+Hib 1");
        form2.append(dpt_hib1);
        form2.append("DPT2+Hib 2");
        form2.append(dpt_hib2);
        form2.append("DPT3+Hib 3");
        form2.append(dpt_hib3);
        form2.append("MMR");
        form2.append(mmr);
        form2.append("OPV R1");
        form2.append(opv_r1);
        form2.append("DPT R1");
        form2.append(dpt_r1);
        
        form3.append("OPV R2");
        form3.append(opv_r2);
        form3.append("DT");
        form3.append(dt);
        form3.append("MMR r");
        form3.append(mmr_r);
        form3.append("OPV R3");
        form3.append(opv_r3);
        form3.append("Td");
        form3.append(td);
        form3.append("Tt");
        form3.append(tt);

        
//        Add The Commands to Forms
        form1.addCommand(next1);
        form1.setCommandListener(this);
        
        form2.addCommand(next2);
        form2.addCommand(back1);
        form2.setCommandListener(this);
        
        form3.addCommand(submit);
        form3.addCommand(back2);
        form3.setCommandListener(this);
        
        si_log.addCommand(log);
        si_log.setItemCommandListener(this);
        
    }

    public void startApp() {
        display.setCurrent(login);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if(c == next1){
            display.setCurrent(form2);
        }
        if(c == next2){
            display.setCurrent(form3);
        }
        
        if(c == back1){
            display.setCurrent(form1);
        } 
        
        if(c == back2){
            display.setCurrent(form2);
        }
        
        if(c == submit){
            
            String data1 = "BCG_ne_maternitet:"+bcg_m.getString();
            String data2 = "BCG:"+bcg.getString();
            String data3 = "Hep+1+ne+maternitet:"+hep_1.getString();
            String data4 = "OPV+1:"+opv_1.getString();
            String data5 = "OPV+2:"+opv_2.getString();
            String data6 = "OPV+3:"+opv_3.getString();
            String data7 = "DPT1+Hib+1:"+dpt_hib1.getString();
            String data8 = "DPT2+Hib+2:"+dpt_hib2.getString();
            String data9 = "DPT3+Hib+3:"+dpt_hib3.getString();
            String data10 = "MMR:"+mmr.getString();
            String data11 = "OPV+R1:"+opv_r1.getString();
            String data12 = "DPT+R1:"+dpt_r1.getString();
            String data13 = "OPV+R2:"+opv_r2.getString();
            String data14 = "DT:"+dt.getString();
            String data15 = "MMR+r:"+mmr_r.getString();
            String data16 = "OPV+R3:"+opv_r3.getString();
            String data17 = "Td:"+td.getString();
            String data18 = "TT:"+tt.getString();
            
              try{
                fmc = getFmc();
                imei = getImei();
               }catch(RecordStoreException res){}

            final String url = "http://77.81.240.20/kerkesat.php?fmc_id="+fmc+"&imei="+imei+"&rr1="+data1+"&rr2="+data2+
            "&rr3="+data3+"&rr4="+data4+"&rr5="+data5+"&rr6="+data6+"&rr7="+data7+"&rr8="+data8+"&rr9="+data9+"&rr10="+data10
            +"&rr11="+data11+"&rr12="+data12+"&rr13="+data13+"&rr14="+data14+"&rr14="+data14+"&rr15="+data15+"&rr16="+data16+"&rr17="+data17
            +"&rr18="+data18;
            
            Thread sub = new Thread(new Runnable() {
                
                public void run() {
                    HttpConnection con;
                    InputStream is;
                    StringBuffer buff = new StringBuffer();
                    try {
                        con = (HttpConnection)Connector.open(url);
                        con.setRequestMethod(HttpConnection.GET);
                        
                        try{
                            is = con.openInputStream();
                            
                            int ch;
                            
                            while((ch = is.read()) != -1){
                                buff.append((char) ch);
                            }
                            result.append(buff.toString());
                            
                            display.setCurrent(result);
                            
                        }catch(Exception e){}
                        
                    }catch(Exception e){}
                }
            });
            sub.start();
        }
            
    }
    
    private String getFmc() throws RecordStoreException{
            
        try {
            
            Fmc_emri = RecordStore.openRecordStore("Fmc_emri", true );
            
            byte[] byteInputData = new byte[1];
            int length = 0;
            
            for (int x = 1; x <= Fmc_emri.getNumRecords(); x++){
                if (Fmc_emri.getRecordSize(x) > byteInputData.length){
                    byteInputData = new byte[Fmc_emri.getRecordSize(x)];
                }
                length = Fmc_emri.getRecord(x, byteInputData, 0);
            }
            
            String val = null;
            
            try {
                val = new String(byteInputData,"UTF-8");
            } catch (UnsupportedEncodingException ex) {
            }
            
            return val;
            
        } catch (RecordStoreException ex) {
            return ex.toString();
        }
 }
    
 private String getImei() throws RecordStoreException{
     
        try {
            
            Imei = RecordStore.openRecordStore("Imei", true );
            
            byte[] byteInputData = new byte[1];
            int length = 0;
            
            for (int x = 1; x <= Imei.getNumRecords(); x++){
                if (Imei.getRecordSize(x) > byteInputData.length){
                    byteInputData = new byte[Imei.getRecordSize(x)];
                }
                length = Imei.getRecord(x, byteInputData, 0);
            }
            
            String val = null;
            
            try {
                val = new String(byteInputData,"UTF-8");
            } catch (UnsupportedEncodingException ex) {
            }
            
            return val;

        } catch (RecordStoreException ex) {
            return ex.toString();
        }
        
 }
 
  public void login() throws RecordStoreException{
     
      Users_rs = RecordStore.openRecordStore("User", true );
      Passs_rs = RecordStore.openRecordStore("Pass", true );
      
            byte[] byteInputData = new byte[1];
            int length = 0;
            for (int x = 1; x <= Users_rs.getNumRecords(); x++){
                if (Users_rs.getRecordSize(x) > byteInputData.length){
                    byteInputData = new byte[Users_rs.getRecordSize(x)];
                }
                length = Users_rs.getRecord(x, byteInputData, 0);
            }
            
            byte[] byteInputData1 = new byte[1];
            int length1 = 0;
            for (int x = 1; x <= Passs_rs.getNumRecords(); x++){
                if (Passs_rs.getRecordSize(x) > byteInputData1.length){
                   byteInputData1 = new byte[Passs_rs.getRecordSize(x)];
                }
                length1 = Passs_rs.getRecord(x, byteInputData1, 0);
            }
            
            String value1 = null;
            String value2 = null;
            
            try {
                value1 = new String(byteInputData, "UTF-8");
                value2 = new String(byteInputData1, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
            }
            
//            alert = new Alert("Records", new String(byteInputData, 0,
//            length), null, AlertType.INFO);
//            alert.setTimeout(Alert.FOREVER);
//            display.setCurrent(alert);
//            Users_rs.closeRecordStore();
            
      if(value1.toLowerCase().equals(user.getString().toLowerCase()) && value2.toLowerCase().equals(pass.getString().toLowerCase())){
                display.setCurrent(form1);
      }else{
          
        String text = "Perdoruesi ose Fjalkalimi nuk eshte i sakte";
        alert = new Alert("Records", new String(text.getBytes(), 0,
        text.length()), null, AlertType.INFO);
        alert.setTimeout(Alert.FOREVER);
        display.setCurrent(alert);

     }
 }

    public void commandAction(Command c, Item item) {
      if(c == log){
        try {
            login();
         } catch (RecordStoreException ex) {
         }
     }
    }

}