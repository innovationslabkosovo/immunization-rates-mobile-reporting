/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vaccination;

import com.sun.j2me.global.Constants;
import com.sun.midp.main.Main;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.pim.*;

/**
 * @author jeton
 */

public class Konfigurimi extends MIDlet implements CommandListener, ItemCommandListener{
    
    private Display display;
    private Form form,edit;
    private TextField User,Pass,imei,fmc,emri,kalimi,user_prev,pass_prew;
    private Command Ruaj,Edito,ruaj,back,exit,ch_update;
    private StringItem Update;
    private RecordStore Users,Passs;
    private Alert alert;
    private RecordStore Imei;
    private RecordStore Fmc_emri,Lang;
    private ChoiceGroup lang;
    static String Ch_language;
    static String[] languages = {"Sr","Al"};
    private DateField startDateField;
//    public 
     
    //Constructor
    public Konfigurimi(){
        
        display = Display.getDisplay(this);
        
        form = new Form("Konfigurimi");
        edit = new Form("Ndrysho");
        User = new TextField("Perdoruesi: ","",8,TextField.ANY);
        Pass = new TextField("Fjalekalimi: ","",8,TextField.ANY);
        imei = new TextField("Imei: ","",15,TextField.NUMERIC);
        fmc = new TextField("Numri identifikues i QMF: ","",40,TextField.NUMERIC);
        
        Ruaj = new Command("Ruaj",Command.OK,1);
        Edito = new Command("Edito",Command.OK,2);
        exit = new Command("Dil",Command.OK,3);
        back = new Command("Kthehu",Command.BACK,1);
        
        form.append(User);
        form.append(Pass);
        form.append(imei);
        form.append(fmc);

        form.addCommand(Ruaj);
        form.addCommand(Edito);
        form.addCommand(exit);
        form.setCommandListener(this);

    }
    
    public void startApp() {
        if(!getUser()){
            display.setCurrent(form);
            ttask();
        }else{
            creatEditGui();

        }
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
  
    public void commandAction(Command c, Displayable d) {
        if(c == Ruaj){
            if(getUser()){
                notifyDestroyed();
            }else {
                creatUser();
                
            }
        }
        
        if(c == Edito){
            edit.deleteAll();
            edit.removeCommand(ruaj);
            if(getUser()){
                 creatEditGui();
                 
            }
           
        }
        
        if(c == ruaj){
            ChangeInfo();
            Language();
        }
        
        if(c == exit){
            notifyDestroyed();
        }
        
        else if(c == back){
            notifyDestroyed();
        }
    }
    
     public void commandAction(Command c, Item item) {
        if(c == ch_update){
            up();
        }
    }
    
    //Create the Edit user form
    public void creatEditGui(){
        
        emri = new TextField("Perdoruesi i ri: ","",8,TextField.ANY);
        kalimi = new TextField("Fjalekalimi i ri: ","",8,TextField.ANY);
        user_prev = new TextField("Perdoruesi i vjeter: ","",8,TextField.ANY);
        pass_prew = new TextField("Fjalekalimi i vjeter: ","",8,TextField.ANY);
        
        
        String gjuha[] = {"English","Shqip"}; 
        lang = new ChoiceGroup("Gjuha",ChoiceGroup.POPUP,gjuha,null);
        
        Update = new StringItem("Update","",Item.BUTTON);

        ruaj = new Command("Ruaj",Command.OK,1);
        ch_update = new Command("Update",Command.OK,1);
        Update.setItemCommandListener(this);
        Update.addCommand(ch_update);
        
        
        
        
        edit.append("Verifiko te dhenat");
        edit.append(user_prev);
        edit.append(pass_prew);
        edit.append("Te dhenat e reja");
        edit.append(emri);
        edit.append(kalimi);
        edit.append(lang);
        edit.append(Update);
//        edit.append(lang);
        
        edit.addCommand(ruaj);
        edit.addCommand(back);
        edit.setCommandListener(this);
        
        

        
        display.setCurrent(edit);
        
        
    }
    
    
    //Change the username and password of the user
    public void ChangeInfo(){
 
        try{
            
            Users = RecordStore.openRecordStore("User", false, RecordStore.AUTHMODE_PRIVATE ,true );
            Passs = RecordStore.openRecordStore("Pass", false, RecordStore.AUTHMODE_PRIVATE ,true );

    //        String outputData = User.getString();
    //        byte[] byteOutputData = outputData.getBytes();
    //        Users.addRecord(byteOutputData, 0,
    //        byteOutputData.length);

            byte[] byteInputData = new byte[1];
            int length = 0;
            for (int x = 1; x <= Users.getNumRecords(); x++){
                if (Users.getRecordSize(x) > byteInputData.length){
                    byteInputData = new byte[Users.getRecordSize(x)];
                }
                length = Users.getRecord(x, byteInputData, 0);
            }
            
            byte[] byteInputData1 = new byte[1];
            int length1 = 0;
            for (int x = 1; x <= Passs.getNumRecords(); x++){
                if (Passs.getRecordSize(x) > byteInputData1.length){
                    byteInputData1 = new byte[Passs.getRecordSize(x)];
                }
                length1 = Passs.getRecord(x, byteInputData1, 0);
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

          if(value1.toLowerCase().equals(user_prev.getString().toLowerCase()) && value2.toLowerCase().equals(pass_prew.getString().toLowerCase())){

                Users = RecordStore.openRecordStore("User", true, RecordStore.AUTHMODE_PRIVATE ,true );
                Passs = RecordStore.openRecordStore("Pass", true, RecordStore.AUTHMODE_PRIVATE ,true );

                Users.setRecord(1, emri.getString().getBytes(), 0, emri.getString().length());
                
                Passs.setRecord(1, kalimi.getString().getBytes(), 0, kalimi.getString().length());
                String msg = "Perdoruesi dhe fjalekalimi u nderruan me sukses.";
                
                alert = new Alert("Records", new String(msg.getBytes(), 0,
                msg.length()), null, AlertType.INFO);
                alert.setTimeout(Alert.FOREVER);
                display.setCurrent(alert);
                Users.closeRecordStore();
                Passs.closeRecordStore();
                
 
          }else{
                String msg = "Perdoruesi ose fjalekalimi i vjeter nuk perputhen, provoni perseri !";
                
                alert = new Alert("Records", new String(msg.getBytes(), 0,
                msg.length()), null, AlertType.INFO);
                alert.setTimeout(Alert.FOREVER);
                display.setCurrent(alert);
                Users.closeRecordStore();
                Passs.closeRecordStore();
          }
       
     }catch(RecordStoreException e){}
    }
    
    //Get the user name from the recordstore
    public boolean getUser(){
          
        try{

            Users = RecordStore.openRecordStore("User", true, RecordStore.AUTHMODE_PRIVATE ,true);
            Passs = RecordStore.openRecordStore("Pass", true, RecordStore.AUTHMODE_PRIVATE ,true);

    //        String outputData = User.getString();
    //        byte[] byteOutputData = outputData.getBytes();
    //        Users.addRecord(byteOutputData, 0,
    //        byteOutputData.length);

            byte[] byteInputData = new byte[1];
            int length = 0;
            for (int x = 1; x <= Users.getNumRecords(); x++){
                if (Users.getRecordSize(x) > byteInputData.length){
                    byteInputData = new byte[Users.getRecordSize(x)];
                }
                length = Users.getRecord(x, byteInputData, 0);
            }
            
            byte[] byteInputData1 = new byte[1];
            int length1 = 0;
            for (int x = 1; x <= Passs.getNumRecords(); x++){
                if (Passs.getRecordSize(x) > byteInputData1.length){
                    byteInputData1 = new byte[Passs.getRecordSize(x)];
                }
                length1 = Passs.getRecord(x, byteInputData1, 0);
            }

//            alert = new Alert("Records", new String(byteInputData, 0,
//            length), null, AlertType.INFO);
//            alert.setTimeout(Alert.FOREVER);
//            display.setCurrent(alert);
//            Users.closeRecordStore();
//              
            if(length != 0 || length1 != 0){
                return true;
            }else {
                return false;
            }
            
        }catch(Exception e){
            return false;
        }

    }
    
    //Creat a new user with the username password imei and fhc id
    public void creatUser(){   
            
        try{
            
            Users = RecordStore.openRecordStore("User", true, RecordStore.AUTHMODE_PRIVATE ,true );
            Passs = RecordStore.openRecordStore("Pass", true, RecordStore.AUTHMODE_PRIVATE ,true );
            Imei = RecordStore.openRecordStore("Imei", true, RecordStore.AUTHMODE_PRIVATE ,true );
            Fmc_emri = RecordStore.openRecordStore("Fmc_emri", true, RecordStore.AUTHMODE_PRIVATE ,true );
            
            String outputData = User.getString();
            byte[] byteOutputData = outputData.getBytes();
            Users.addRecord(byteOutputData, 0,
            byteOutputData.length);
            
            String outputData1 = Pass.getString();
            byte[] byteOutputData1 = outputData1.getBytes();
            Passs.addRecord(byteOutputData1, 0,
            byteOutputData1.length);
            
            String outputData2 = imei.getString();
            byte[] byteOutputData2 = outputData2.getBytes();
            Imei.addRecord(byteOutputData2, 0,
            byteOutputData2.length);
            
            String outputData3 = fmc.getString();
            byte[] byteOutputData3 = outputData3.getBytes();
            Fmc_emri.addRecord(byteOutputData3, 0,
            byteOutputData3.length);
            
            byte[] byteInputData = new byte[1];
            int length = 0;
            for (int x = 1; x <= Users.getNumRecords(); x++){
                if (Users.getRecordSize(x) > byteInputData.length){
                    byteInputData = new byte[Users.getRecordSize(x)];
                }
                length = Users.getRecord(x, byteInputData, 0);
            }
            
            byte[] byteInputData1 = new byte[1];
            int length1 = 0;
            for (int x = 1; x <= Passs.getNumRecords(); x++){
                if (Passs.getRecordSize(x) > byteInputData1.length){
                   byteInputData1 = new byte[Passs.getRecordSize(x)];
                }
                length1 = Passs.getRecord(x, byteInputData1, 0);
            }
            
            byte[] byteInputData2 = new byte[1];
            int length2 = 0;
            
            for (int x = 1; x <= Imei.getNumRecords(); x++){
                if (Imei.getRecordSize(x) > byteInputData2.length){
                    byteInputData2 = new byte[Imei.getRecordSize(x)];
                }
                length2 = Imei.getRecord(x, byteInputData2, 0);
            }
            
            byte[] byteInputData3 = new byte[1];
            int length3 = 0;
            for (int x = 1; x <= Fmc_emri.getNumRecords(); x++){
                if (Fmc_emri.getRecordSize(x) > byteInputData3.length){
                    byteInputData3 = new byte[Fmc_emri.getRecordSize(x)];
                }
                length3 = Fmc_emri.getRecord(x, byteInputData1, 0);
            }
//          
//            alert = new Alert("Records", new String(byteInputData3, 0,
//            length3), null, AlertType.INFO);
//            alert.setTimeout(Alert.FOREVER);
//            display.setCurrent(alert);
//            Users.closeRecordStore();
//            
        }catch (Exception e){
        }
            
    }
            
     public void up(){
            
       connect.start();
//         ttask();
            
    }
     
     public void Language(){

        try {
            
            Lang = RecordStore.openRecordStore("Language", true, RecordStore.AUTHMODE_PRIVATE ,true );

            getTheselectedLang();
            
            byte[] byteOutputData = Ch_language.getBytes();
            Lang.addRecord(byteOutputData, 0,
            byteOutputData.length);
         
            alert = new Alert("Records", new String(byteOutputData, 0,
            byteOutputData.length), null, AlertType.INFO);
            alert.setTimeout(Alert.FOREVER);
            display.setCurrent(alert);

        } catch (RecordStoreException ex) {
            edit.append(ex.toString());
        }
        
     }
     
     public void getTheselectedLang(){
            for(int i = 0;i<=2;i++){
                if(lang.isSelected(i)){
                   Ch_language = languages[i];
                   return;
                }
            }
     }
     
     
     public void ttask(){
        
            Calendar cal = Calendar.getInstance();
    
//            cal.set(Calendar.DAY_OF_MONTH, 19);
             startDateField = new DateField("Start date", 
                                               DateField.DATE_TIME);
             startDateField.setDate(new Date());
             long startDate = startDateField.getDate().getTime();
         
             EventList eventList = null;
             
                try {

                   eventList = (EventList) PIM.getInstance().openPIMList(PIM.EVENT_LIST, PIM.READ_WRITE);
              
                } catch (PIMException e) {
                   return;
                }
                
                int[] supported_fields = eventList.getSupportedRepeatRuleFields( RepeatRule.MONTHLY );
                
                if( supported_fields.length > 0 ) {
//                    alert = new Alert("Records", new String(("is supported").getBytes(), 0,
//                    10), null, AlertType.INFO);
//                    alert.setTimeout(Alert.FOREVER);
//                    display.setCurrent(alert);
                    
//                     sinl.setInt( RepeatRule.FREQUENCY, RepeatRule.MONTHLY );
//                     RepeatRule.setInt(RepeatRule.FREQUENCY, RepeatRule.DAILY);  
                }

cal.set( Calendar.MONTH, Calendar.DECEMBER );
                Event singleEvent = eventList.createEvent();
                if (eventList.isSupportedField(Event.SUMMARY))
                     singleEvent.addString(Event.SUMMARY, PIMItem.ATTR_NONE, "Dergimi i Raportit");
                if (eventList.isSupportedField(Event.START))
                     singleEvent.addDate(Event.START, PIMItem.ATTR_NONE, startDate );
                if (eventList.isSupportedField(Event.ALARM))
                     singleEvent.addInt(Event.ALARM, PIMItem.ATTR_NONE, 180);
                if(eventList.isSupportedField(RepeatRule.MONTHLY)){
                     singleEvent.addInt(RepeatRule.FREQUENCY,RepeatRule.MONTHLY,5); 
                }
               
                try { 
                     singleEvent.commit();
                } catch (PIMException e) {
                     // An error occured
                }
                try {
                     eventList.close();
                } catch (PIMException e) {

                }
                
            
     }
            
     Thread connect = new Thread(new Runnable(){
            
        public void run() {
           HttpConnection con;
           InputStream is;
           StringBuffer buff = new StringBuffer();
           String version = getAppProperty("MIDlet-Version"); 
           final String url = "http://77.81.240.20/immunization_up/vac_up.php?ver="+version;
           
            try {
                
                con = (HttpConnection)Connector.open(url);
                con.setRequestMethod(HttpConnection.GET);
                
                try {
                    
                    is = con.openInputStream();
                    
                    int ch;
                    while((ch = is.read()) != -1){
                        buff.append((char) ch);
                    }
                    
                    if(!buff.toString().equals("")){
                        
                        try {
                        
                        platformRequest(buff.toString());
                        destroyApp(true);
//                          edit.append(buff.toString());
                        
                        } catch (Exception e) {

                            edit.append(buff.toString());
                            
                        }finally {
                            notifyDestroyed();
			}
                        
                     }else{
                        String Update_response = "Nuk Ka Update";
                        alert = new Alert("Update", new String((Update_response).getBytes(), 0,
                        Update_response.length()), null, AlertType.INFO);
                        alert.setTimeout(2500);
                        display.setCurrent(alert);
                    }
                    
                } catch (Exception e) {
                    edit.append(e.toString());
                }
                
            } catch (Exception e) {
                edit.append(e.toString());
            }
           
        }
         
     });
 
}