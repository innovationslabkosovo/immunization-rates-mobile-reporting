package Vaccination;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public class Raportimi extends MIDlet implements CommandListener,ItemCommandListener{

  static Calendar cal = Calendar.getInstance();
    
  private String number = "50111";
  private Display display;
  private Alert alert;
  private Form form, form2, form3, form4, form5, form6, form7, form8, form9, text, login;
  private String Vaksines, Vaksines1, Vaksines2, Vaksines3, Vaksines4, Vaksines5, Vaksines6, Vaksines7, Vaksines8, Vaksines9, Vaksines10;
  private String Vaksines11, Vaksines12, Vaksines13, Vaksines14, Vaksines15, Vaksines16, Vaksines17, Vaksines18;
  private String input, input1, success;
  static String fmc_id = null;
  private String f;
  static String Language[] = new String[100];
  private String fmc_id_a[] = {"14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36"};
//  private int muajt[] = {14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36};
  private int[] as = {cal.get(Calendar.MONTH)+1,cal.get(Calendar.MONTH)};
  private int muaji;
  static String zgj_m;
  static String prev_m;
   
  //private String input2, input3, input4, input5, input6, input7, input8, input9;
  //private String input10, input11, input12, input13, input14, input15, input16, input17;
  //private String input18, input19, input20, input21, input22, input23, input24, input25, input26, input27, input28, input29;
  //private String input30, input31, input32, input33, input34, input35, input36, input37, input38, input39, input40, input41;
  //private String input42, input43, input44, input45, input46, input47, input48, input49, input50, input51, input52, input53;
  //private String input54, input55, input56, input57, input58, input59, input60, input61;
  //private String input62, input63, input64, input65, input66, input67, input68, input69, input70, input71, input72, input73;
  //private String input74, input75, input76, input77, input78, input79, input80, input81, input82, input83, input84, input85;
  //private String c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19;
  
  private ChoiceGroup values;
  private String periudha;
  private TextField user, pass;
 // private TextField t1, t2, t3, t4, t5, t6, t7, t8;
 // private TextField t9, t10, t11, t12, t13, t14, t15, t16;
  private TextField t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28,t31,t35,t39;
  private TextField t29, t30, t32, t33, t34, t36, t37, t38, t40;
  private TextField t41, t42, t43, t44, t45, t46, t47, t48, t49, t50, t51, t52;
  private TextField t53, t54, t55, t56, t57, t58, t59, t60;
  private TextField t61, t62, t64, t65, t67, t68, t70, t71;
  private TextField t73, t74, t76, t77, t79, t80, t82, t83,t84;
  private ChoiceGroup values1;
  private Command Send, exit, start, next, next1, next2, next3, next4, next5, next6, next7, next8;
  private Command back7,back2,back3,back4,back5,back6,getSettings;
  private Command log;
  private final String lineBr = "\r\n";                     // line break
  private RecordStore recordStore,Lang;
  private StringItem si_log,te;
  private RecordStore Users_rs;
  private RecordStore Passs_rs;
  private RecordStore Fmc_emri;
  private RecordStore Imei;
  private String imei;
  
  public Raportimi(){
  
  display = Display.getDisplay(this);
     
  /*********************************************************************************************/
  /********************************* Set Item Commands *********************************************/
  /*******************************************************************************************/
  
  si_log = new StringItem("Kyqu ","Kyqu",Item.BUTTON);
  si_log.setLayout(Item.LAYOUT_CENTER);
  
  /*********************************************************************************************/
  /********************************* Set Commands *********************************************/
  /*******************************************************************************************/
  
  start = new Command("Shiko te dhenat", Command.SCREEN, 1);
  exit = new Command("Mbylle", Command.EXIT, 1);
  next = new Command("Vazhdo", Command.SCREEN, 1);
  next1 = new Command("Vazhdo", Command.SCREEN, 1);
  next2 = new Command("Vazhdo", Command.SCREEN, 1);
  next3 = new Command("Vazhdo", Command.SCREEN, 1);
  next4 = new Command("Vazhdo", Command.SCREEN, 1);
  next5 = new Command("Vazhdo", Command.SCREEN, 1);
  next6 = new Command("Vazhdo", Command.SCREEN, 1);
  next7 = new Command("Vazhdo", Command.SCREEN, 1);
  back7 = new Command("Kthehu", Command.BACK, 1);
  back2 = new Command("Kthehu", Command.BACK, 1);
  back3 = new Command("Kthehu", Command.BACK, 1);
  back4 = new Command("Kthehu", Command.BACK, 1);
  back5 = new Command("Kthehu", Command.BACK, 1);
  back6 = new Command("Kthehu", Command.BACK, 1);
  log = new Command("Kyqu" , Command.SCREEN, 1);
  Send = new Command("Dergo", Command.OK, 1);
//  getSettings = new Command("Parametrat", Command.OK, 1);

  /*********************************************************************************************/
  /********************************* Set Strings **********************************************/
  /*******************************************************************************************/
  
//  Vaksines = "BCG ne maternitet";
  te = new StringItem("BCG ne maternitet","",StringItem.PLAIN);
  
//  te.setPreferredSize(200, 10);
  Font theFont=Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE);
  
  te.setFont(theFont);
  
  Vaksines1 = "BCG";
  Vaksines2 = "Hep. 1 ne maternitet";
  Vaksines3 = "OPV1";
  Vaksines4 = "OPV2";
  Vaksines5 = "OPV3";
  Vaksines6 = "DPT1+HiB1+Hep";
  Vaksines7 = "DPT2+HiB2+Hep2";
  Vaksines8 = "DPT3+HiB3+Hep3";
  Vaksines9 = "MMR";
  Vaksines10 = "Vaksinat tjera";

  Vaksines11 = "OPV R1";
  Vaksines12 = "DPT R1";
  Vaksines13 = "OPV R2";
  Vaksines14 = "DT";
  Vaksines15 = "MMR r";
  Vaksines16 = "OPV3 R3";
  Vaksines17 = "Td";
  Vaksines18 = "TT";
//  Vaksines18 = "Vaksinat tjera";
  
  success = "Te dhenat jan derguar me sukses ju faleminderit"; // Succcess text 

  /*******************************************************************************************/
  /************************* Set Textfields and choicegroups *********************************/
  /*****************************************************************************************/

  
  String[] per = {"Janar","Shkurt","Mars","Prill","Maj","Qershor","Korrik","Gusht","Shtator","Tetor","Nentor","Dhjetor"};
  
//  String cal;
  
  zgj_m = per[cal.get(Calendar.MONTH) -1];
  prev_m = per[cal.get(Calendar.MONTH)];
  
  String[] muajt_zgj = {prev_m,zgj_m};
  values = new ChoiceGroup("Periudha",ChoiceGroup.POPUP,muajt_zgj,null);
  
  String inst[]={"Llashtice","Livoq_i_Eperm", "Velekince", "Malisheve", "Bukovik", "Kravarice", "Gumnishte", "Kmetovc"
  , "Verbice_e_Kmetovcit", "Ponesh", "Shillove", "Kosaqe", "Inatovc", "Llovce", "Bilinice", "Stubline"
  , "Shurdhan","Zhegovc"," Verbice_e_Zhegovcit","Haxhaj","Selishte","Muqibabe","Sllubice"};
  
  values1=new ChoiceGroup("Emri i Institucionit Shendetesor:",ChoiceGroup.POPUP,inst,null);
  
                /********************************/
               /************ Section2 **********/
              /********************************/
  
  /*
  t1 = new TextField("0-11 muaj", "",6,TextField.NUMERIC);
  t2 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
  t3 = new TextField("(12-59 muaj) OPV/DPT", "",6,TextField.NUMERIC);
  t4 = new TextField("(12-59 muaj) OPV/DPT-r","",6,TextField.NUMERIC);
  t5 = new TextField("(12-59 muaj) MMR","",6,TextField.NUMERIC);
  t6 = new TextField("Femijet e klases se I-re","",6,TextField.NUMERIC);
  t7 = new TextField("Femijet e klases se VI-te","",6,TextField.NUMERIC);
  t8 = new TextField("Femijet e klases se mesme","",6,TextField.NUMERIC);
                
  t9 = new TextField("0-11 muaj", "",6,TextField.NUMERIC);
  t10 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
  t11 = new TextField("(12-59 muaj) OPV/DPT", "",6,TextField.NUMERIC);
  t12 = new TextField("(12-59 muaj) OPV/DPT-r","",6,TextField.NUMERIC);
  t13 = new TextField("(12-59 muaj) MMR","",6,TextField.NUMERIC);
  t14 = new TextField("Femijet e klases se I-re","",6,TextField.NUMERIC);
  t15 = new TextField("Femijet e klases se VI-te","",6,TextField.NUMERIC);
  t16 = new TextField("Femijet e klases se mesme","",6,TextField.NUMERIC);
  * 
  */
               /*******************************/
              /*********** Section3 **********/
             /*******************************/
  
  t17 = new TextField("0-11 muaj", "",6,TextField.NUMERIC);         //BCG ne Maternitet
//  t18 = new TextField("12-23 muaj","",6,TextField.NUMERIC);;
//  t19 = new TextField("24-59 muaj", "",6,TextField.NUMERIC);
//  t20 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);
  t21 = new TextField("0-11 muaj","",6,TextField.NUMERIC);          //BCG
//  t22 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
//  t23 = new TextField("24-59 muaj","",6,TextField.NUMERIC);
//  t24 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);
  t25 = new TextField("0-11 muaj", "",6,TextField.NUMERIC);         //Hep. 1 ne maternitet
//  t26 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
//  t27 = new TextField("24-59 muaj", "",6,TextField.NUMERIC);
//  t28 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);

  t29 = new TextField("0-11 muaj","",6,TextField.NUMERIC);          //OPV1
  t30 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
  t31 = new TextField("24-59 muaj","",6,TextField.NUMERIC);
  t32 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);
  t33 = new TextField("0-11 muaj","",6,TextField.NUMERIC);          //OPV2
  t34 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
  t35 = new TextField("24-59 muaj","",6,TextField.NUMERIC);
  t36 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);
  t37 = new TextField("0-11 muaj","",6,TextField.NUMERIC);          //OPV3
  t38 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
  t39 = new TextField("24-59 muaj","",6,TextField.NUMERIC);
  t40 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);

  t41 = new TextField("0-11 muaj","",6,TextField.NUMERIC);         //DPT1+HiB1+Hep
  t42 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
  t43 = new TextField("24-59 muaj","",6,TextField.NUMERIC);
  t44 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);
  t45 = new TextField("0-11 muaj","",6,TextField.NUMERIC);          //DPT2+HiB2+Hep2
  t46 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
  t47 = new TextField("24-59 muaj","",6,TextField.NUMERIC);
  t48 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);
  t49 = new TextField("0-11 muaj","",6,TextField.NUMERIC);          //DPT3+HiB3+Hep3
  t50 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
  t51 = new TextField("24-59 muaj","",6,TextField.NUMERIC);
  t52 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);

//  t53 = new TextField("0-11 muaj","",6,TextField.NUMERIC);          //MMR
  t54 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
  t55 = new TextField("24-59 muaj","",6,TextField.NUMERIC);
  t56 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);
//  t57 = new TextField("0-11 muaj","",6,TextField.NUMERIC);          //Vaksinat tjera
//  t58 = new TextField("12-23 muaj","",6,TextField.NUMERIC);
//  t59 = new TextField("24-59 muaj","",6,TextField.NUMERIC);
//  t60 = new TextField("mbi 59 muaj","",6,TextField.NUMERIC);

  t61 = new TextField("12-23 muaj","",6,TextField.NUMERIC);        //OPV R1(12-18 m)
  t62 = new TextField("mbi 23 muaj","",6,TextField.NUMERIC);
  t64 = new TextField("12-23 muaj","",6,TextField.NUMERIC);        //DPT R1(12-18 m)
  t65 = new TextField("mbi 23 muaj","",6,TextField.NUMERIC);
//  t67 = new TextField("12-23 muaj","",6,TextField.NUMERIC);        //OPV R2(6-7 vj)
  t68 = new TextField("mbi 23 muaj","",6,TextField.NUMERIC);
//  t70 = new TextField("12-23 muaj","",6,TextField.NUMERIC);        //DT(6-7vj)
  t71 = new TextField("mbi 23 muaj","",6,TextField.NUMERIC);
//  t73 = new TextField("12-23 muaj","",6,TextField.NUMERIC);        //MMR r(6-7 vj)
  t74 = new TextField("mbi 23 muaj","",6,TextField.NUMERIC);
//  t76 = new TextField("12-23 muaj","",6,TextField.NUMERIC);        //OPV3 R3(12-13 vj)
  t77 = new TextField("mbi 23 muaj","",6,TextField.NUMERIC);
//  t79 = new TextField("12-23 muaj","",6,TextField.NUMERIC);        //Td(12-12 vj)
  t80 = new TextField("mbi 23 muaj","",6,TextField.NUMERIC);
  
  t84 = new TextField("mbi 23 muaj","",6,TextField.NUMERIC);          //TT(18 vj)
//  t82 = new TextField("12-23 muaj","",6,TextField.NUMERIC);        //Vaksinat tjera
//  t83 = new TextField("mbi 23 muaj","",6,TextField.NUMERIC);
  
  //Login TextFields 
  user = new TextField("Perdoruesi", "", 8,TextField.ANY);
  user.setLayout(TextField.LAYOUT_CENTER);
  pass = new TextField("Fjalekalimi", "", 8,TextField.PASSWORD);

  /*******************************************************************************************/
  /*********************************** Set Forms ********************************************/
  /*****************************************************************************************/
  form = new Form("Instituti Kombetar i Shendetesise Publike te Kosoves");
  //form2 = new Form("Numri vjetor i femijeve te grupit cak");
  //form3 = new Form("Numri mujor i femijeve te grupit cak");
  form4 = new Form("Numri femijeve te vaksinuar");
  form5 = new Form("Numri femijeve te vaksinuar");
  form6 = new Form("Numri femijeve te vaksinuar");
  form7 = new Form("Numri femijeve te vaksinuar");
  form8 = new Form("Numri femijeve te vaksinuar");
  form9 = new Form("Numri femijeve te vaksinuar");
  text = new Form("Fund");
  
  //Login Form
  login = new Form("Hyrja");
  
  si_log.addCommand(log);
  si_log.setItemCommandListener((ItemCommandListener)this);
  
//  form10 = new Form("Numri femijeve te vaksinuar");
  
  /*******************************************************************************************/
  /***************************** Add Commands and Textfields ********************************/
  /*****************************************************************************************/
  
  login.addCommand(exit);
  login.setCommandListener((CommandListener)this);
  
  form.addCommand(next);
  form.setCommandListener((CommandListener)this);
  
  text.addCommand(exit);
  text.setCommandListener((CommandListener)this);

  /*
   *
  form2.addCommand(next1);
  form2.addCommand(exit);
  form2.setCommandListener(this);

  form3.addCommand(next2);
  form3.addCommand(exit);
  form3.setCommandListener(this);
  */

  form4.addCommand(next1);
  form4.addCommand(back2);
  form4.addCommand(exit);
  form4.setCommandListener((CommandListener)this);

  form5.addCommand(next2);
  form5.addCommand(back3);
  form5.addCommand(exit);
  form5.setCommandListener((CommandListener)this);

  form6.addCommand(next3);
  form6.addCommand(back4);
  form6.addCommand(exit);
  form6.setCommandListener((CommandListener)this);

  form7.addCommand(next4);
  form7.addCommand(back5);
  form7.addCommand(exit);
  form7.setCommandListener((CommandListener)this);

  form8.addCommand(next5);
  form8.addCommand(back6);
  form8.addCommand(exit);
  form8.setCommandListener((CommandListener)this);

  form9.addCommand(start);
  form9.addCommand(back7);
  form9.addCommand(Send);
  form9.addCommand(exit);
  form9.setCommandListener((CommandListener)this);

  login.append(user);
  login.append(pass);
  login.append(si_log);
  
  form.append(values);
  form.append(values1);
//  try {
//      StringItem t = new StringItem("Emri i Qmfs \n","");
//      form.append(values1);
//      int append = form.append(getFmc());
//  } catch (RecordStoreException ex) {
//
//  }
  
  /*
  form2.append(t1);
  form2.append(t2);
  form2.append(t3);
  form2.append(t4);
  form2.append(t5);
  form2.append(t6);
  form2.append(t7);
  form2.append(t8);
  *
  
  form3.append(t9);
  form3.append(t10);
  form3.append(t11);
  form3.append(t12);
  form3.append(t13);
  form3.append(t14);
  form3.append(t15);
  form3.append(t16);
  * 
  */

//  form4.append(Vaksines);
//  form4.append(lineBr);
//  form4.append(t17);
//  form4.append(t18);
//  form4.append(t19);
//  form4.append(t20);
//  form4.append(Vaksines1);
//  form4.append(lineBr);
//  form4.append(t21);
//  form4.append(t22);
//  form4.append(t23);
//  form4.append(t24);
//  form4.append(Vaksines2);
//  form4.append(lineBr);
//  form4.append(t25);
//  form4.append(t26);
//  form4.append(t27);
//  form4.append(t28);

  form5.append(te);
  form5.append(lineBr);
  form5.append(t29);
  form5.append(t30);
  form5.append(t31);
  form5.append(t32);
  form5.append(Vaksines4);
  form5.append(lineBr);
  form5.append(t33);
  form5.append(t34);
  form5.append(t35);
  form5.append(t36);
  form5.append(Vaksines5);
  form5.append(lineBr);
  form5.append(t37);
  form5.append(t38);
  form5.append(t39);
  form5.append(t40);

  form6.append(Vaksines6);
  form6.append(lineBr);
  form6.append(t41);
  form6.append(t42);
  form6.append(t43);
  form6.append(t44);
  form6.append(Vaksines7);
  form6.append(lineBr);
  form6.append(t45);
  form6.append(t46);
  form6.append(t47);
  form6.append(t48);
  form6.append(Vaksines8);
  form6.append(lineBr);
  form6.append(t49);
  form6.append(t50);
  form6.append(t51);
  form6.append(t52);

  form7.append(Vaksines9);
//  form7.append(t53);
  form7.append(t54);
  form7.append(t55);
  form7.append(t56);
  
//  form7.append(Vaksines10);
//  form7.append(t57);
//  form7.append(t58);
//  form7.append(t59);
//  form7.append(t60);

  form8.append(Vaksines11);
  form8.append(t61);
  form8.append(t62);
  form8.append(Vaksines12);
  form8.append(t64);
  form8.append(t65);
  form8.append(Vaksines13);
//  form8.append(t67);
  form8.append(t68);
  form8.append(Vaksines14);
//  form8.append(t70);
  form8.append(t71);

  form9.append(Vaksines15);
//  form9.append(t73);
  form9.append(t74);
  form9.append(Vaksines16);
//  form9.append(t76);
  form9.append(t77);
  form9.append(Vaksines17);
//  form9.append(t79);
  form9.append(t80);
  form9.append(Vaksines18);
  form9.append(t84);
//  form9.append(Vaksines18);
//  form9.append(t82);
//  form9.append(t83);
  }

  public void startApp(){
      display.setCurrent(login);
  }
 
  public void pauseApp(){

  }

  public void destroyApp(boolean destroy ){
      if(destroy == true){
         notifyDestroyed();
      }
  }

  public void grab(){
      
      input = periudha;
      
      for(int a = 0; a < 2;a++){
        if(values.isSelected(a)){
            periudha = values.getString(a);
            muaji = as[a];
            return;
        }
     }
      /*
      input2 = t1.getString();
      input3 = t2.getString();
      input4 = t3.getString();
      input5 = t4.getString();
      input6 = t5.getString();
      input7 = t6.getString();
      input8 = t7.getString();
      input9 = t8.getString();
      * 

      input10 = t9.getString();
      input11 = t10.getString();
      input12 = t11.getString();
      input13 = t12.getString();
      input14 = t13.getString();
      input15 = t14.getString();
      input16 = t15.getString();
      input17 = t16.getString();
 
      input18 = t17.getString();
      input19 = t18.getString();
      input20 = t19.getString();
      input21 = t20.getString();
      input22 = t21.getString();
      input23 = t22.getString();
      input24 = t23.getString();
      input25 = t24.getString();
      input26 = t25.getString();
      input27 = t26.getString();
      input28 = t27.getString();
      input29 = t28.getString();

      input30 = t29.getString();
      input31 = t30.getString();
      input32 = t31.getString();
      input33 = t32.getString();
      input34 = t33.getString();
      input35 = t34.getString();
      input36 = t35.getString();
      input37 = t36.getString();
      input38 = t37.getString();
      input39 = t38.getString();
      input40 = t39.getString();
      input41 = t40.getString();

      input42 = t41.getString();
      input43 = t42.getString();
      input44 = t43.getString();
      input45 = t44.getString();
      input46 = t45.getString();
      input47 = t46.getString();
      input48 = t47.getString();
      input49 = t48.getString();
      input50 = t49.getString();
      input51 = t50.getString();
      input52 = t51.getString();
      input53 = t52.getString();

      input54 = t53.getString();
      input55 = t54.getString();
      input56 = t55.getString();
      input57 = t56.getString();
      input58 = t57.getString();
      input59 = t58.getString();
      input60 = t59.getString();
      input61 = t60.getString();

      input63 = t62.getString();
      input65 = t64.getString();
      input66 = t65.getString();
      input68 = t67.getString();
      input69 = t68.getString();
      input71 = t70.getString();
      input72 = t71.getString();
      input74 = t73.getString();
      input75 = t74.getString();
      input77 = t76.getString();
      input78 = t77.getString();
      input62 = t61.getString();
      input63 = t62.getString();
      input65 = t64.getString();
      input66 = t65.getString();
      input68 = t67.getString();
      input69 = t68.getString();
      input71 = t70.getString();
      input72 = t71.getString();
      input74 = t73.getString();
      input75 = t74.getString();
      input77 = t76.getString();
      input78 = t77.getString();
      input80 = t79.getString();
      input81 = t80.getString();
      input83 = t82.getString();
      input84 = t83.getString();
      */

  }

  public void select() {

    for(int i = 0;i<=23;i++)                                 //Display item from dropdown menu
       {
           if (values1.isSelected(i)){
               input1 = values1.getString(i);
               fmc_id = fmc_id_a[i];
//               f = fmc_id_a[i];
               return;
           }
       }
  }

  public void commandAction(Command command,
  Displayable displayable){
  grab();
  select();
  /*
   * 
  if(command == next){
      display.setCurrent(form2);
  }fmc_i
 
  if(command == next){
      display.setCurrent(form3);
  }
  */

  if(command == next){
      display.setCurrent(form5);
  }

//  if(command == next1){
//      display.setCurrent(form5);
//  }

  if(command == next2){
      display.setCurrent(form6);
  }

  if(command == next3){
      display.setCurrent(form7);
  }
  
  if(command == next4){
      display.setCurrent(form8);
  }

  if(command == next5){
      display.setCurrent(form9);
  }

  if(command == exit){
      destroyApp(true);
  }
  
//  if(command == log){
//      login();
//  }
  
  if(command == Send){
      
   final String data = periudha;
   final String data2 = input1;
   final String data3 = (!t17.getString().equals(""))? t17.getString(): "0";
//   final String data4 ="12-23muaj" + t18.getString() + "12-23muaj";
//   final String data5 ="24-59muaj" + t19.getString() + "24-59muaj";
//   final String data6 ="mbi_59_muaj" + t20.getString() + "mbi_59_muaj";
   final String data7 = (!t21.getString().equals(""))? t21.getString(): "0";
//   final String data8 ="12-23muaj" + t22.getString() + "12-23muaj";
//   final String data9 ="24-59muaj" + t23.getString() + "24-59muaj";
//   final String data10 ="mbi_59muaj" + t24.getString() + "mbi_59muaj";
   final String data11 = (!t25.getString().equals(""))? t25.getString(): "0";
//   final String data12 ="12-23muaj" + t26.getString() + "12-23muaj";
//   final String data13 ="24-59muaj" + t27.getString() + "24-59muaj";
//   final String data14 ="mbi_59muaj" + t28.getString() + "mbi_59muaj";
   final String data15 = (!t29.getString().equals(""))? t29.getString(): "0";
   final String data16 = (!t30.getString().equals(""))? t30.getString(): "0";
   final String data17 = (!t31.getString().equals(""))? t31.getString(): "0";
   final String data18 = (!t32.getString().equals(""))? t32.getString(): "0";
   final String data19 = (!t33.getString().equals(""))? t33.getString(): "0";
   final String data20 = (!t34.getString().equals(""))? t34.getString(): "0";
   final String data21 = (!t35.getString().equals(""))? t35.getString(): "0";
   final String data22 = (!t36.getString().equals(""))? t36.getString(): "0";
   final String data23 = (!t37.getString().equals(""))? t37.getString(): "0";
   final String data24 = (!t38.getString().equals(""))? t38.getString(): "0";
   final String data25 = (!t39.getString().equals(""))? t39.getString(): "0";
   final String data26 = (!t40.getString().equals(""))? t40.getString(): "0";
   final String data27 = (!t41.getString().equals(""))? t41.getString(): "0";
   final String data28 = (!t42.getString().equals(""))? t42.getString(): "0";
   final String data29 = (!t43.getString().equals(""))? t43.getString(): "0";
   final String data30 = (!t44.getString().equals(""))? t44.getString(): "0";
   final String data31 = (!t45.getString().equals(""))? t45.getString(): "0";
   final String data32 = (!t46.getString().equals(""))? t46.getString(): "0";
   final String data33 = (!t47.getString().equals(""))? t47.getString(): "0";
   final String data34 = (!t48.getString().equals(""))? t48.getString(): "0";
   final String data35 = (!t49.getString().equals(""))? t49.getString(): "0";
   final String data36 = (!t50.getString().equals(""))? t50.getString(): "0";
   final String data37 = (!t51.getString().equals(""))? t51.getString(): "0";
   final String data38 = (!t52.getString().equals(""))? t52.getString(): "0";
//   final String data39 ="0-11muaj" + t53.getString() + "0-11muaj";
   final String data40 = (!t54.getString().equals(""))? t54.getString(): "0";
   final String data41 = (!t55.getString().equals(""))? t55.getString(): "0";
   final String data42 = (!t56.getString().equals(""))? t56.getString(): "0";
//   final String data43 ="0-11muaj" + t57.getString() + "0-11muaj";
//   final String data44 ="12-23muaj" + t58.getString() + "12-23muaj";
//   final String data45 ="24-59muaj" + t59.getString() + "24-59muaj";
//   final String data46 ="mbi_59muaj" + t60.getString() + "mbi_59muaj";
   final String data47 = (!t61.getString().equals(""))? t61.getString(): "0";
   final String data48 = (!t62.getString().equals(""))? t62.getString(): "0";
   final String data49 = (!t64.getString().equals(""))? t64.getString(): "0";
   final String data50 = (!t65.getString().equals(""))? t65.getString(): "0";
//   final String data51 ="12-23muaj" + t67.getString() + "12-23muaj";
   final String data52 = (!t68.getString().equals(""))? t68.getString(): "0";
//   final String data53 ="12-23muaj"+ t70.getString() + "12-23muaj";
   final String data54 = (!t71.getString().equals(""))? t71.getString(): "0";
//   final String data55 ="12-23muaj" + t73.getString() + "12-23muaj";
   final String data56 = (!t74.getString().equals(""))? t74.getString(): "0";
//   final String data57 ="12-23muaj" + t76.getString() + "12-23muaj";
   final String data58 = (!t77.getString().equals(""))? t77.getString(): "0";
//   final String data59 ="12-23muaj" + t79.getString() + "12-23muaj";
   final String data60 = (!t80.getString().equals(""))? t80.getString(): "0";
   
   final String data61 = (!t84.getString().equals(""))? t84.getString(): "0";
//   final String data61 ="12-23muaj" + t82.getString() + "12-23muaj";
//   final String data62 ="mbi_23_muaj" + t83.getString() + "mbi_23_muaj";
   String fmc = null;
   
   try{
       
    fmc = getFmc();
    imei = getImei();   
    
   }catch(RecordStoreException res){}
   
   final String url = "http://77.81.240.20/mysql_birthreg.php?fmc="+fmc_id+"&imei="+imei+"&mu="+muaji+"&rr1="+data+"&rr2="+data2+"&rr3="+data3+"&rr7="+data7+"&rr11="+data11
   +"&rr15="+data15+"&rr16="+data16+"&rr19="+data19+"&rr20="+data20+"&rr23="+data23+"&rr24="+data24+"&rr28="+data28+"&rr29="+data29+"&rr30="+data30+"&rr32="+data32
   +"&rr33="+data33+"&rr34="+data34+"&rr36="+data36+"&rr37="+data37+"&rr38="+data38+"&rr40="+data40+"&rr41="+data41+"&rr42="+data42+"&rr47="+data47+"&rr48="+data48+"&rr49="+data49+"&rr50="+data50
   +"&rr52="+data52+"&rr54="+data54+"&rr56="+data56+"&rr58="+data58+"&rr60="+data60+"&rr61="+data61+"&rr17="+data17+"&rr18="+data18+"&rr21="+data21+"&rr22="+data22+"&rr25="+data25+"&rr26="+data26
   +"&rr27="+data27+"&rr31="+data31+"&rr35="+data35;
   
   
   
   final String msg = "imr ."+fmc_id+"."+muaji+"."+data3+"."+data7+"."+data11
   +"."+data15+"."+data16+"."+data19+"."+data20+"."+data23+"."+data24+"."+data28+"."+data29+"."+data30+"."+data32
   +"."+data33+"."+data34+"."+data36+"."+data37+"."+data38+"."+data40+"."+data41+"."+data42+"."+data47+"."+data48+"."+data49+"."+data50
   +"."+data52+"."+data54+"."+data56+"."+data58+"."+data60+"."+data61+"."+data17+"."+data18+"."+data21+"."+data22+"."+data25+"."+data26
   +"."+data27+"."+data31+"."+data35;
   
   /*
    *
    * Function to connect to server and send data
    *
    */

   Thread connection = new Thread(new Runnable(){
       
          public void run(){
            
          //Declaring neccesary Objects for creating the connection , reading and writing data to server

          HttpConnection c;     
          InputStream is;
          OutputStream out;
          StringBuffer buff = new StringBuffer();
          String err = null;
          
          try{
              
                //Connecting to server passing the data and setting the connection property
                c = (HttpConnection)Connector.open(url);
                c.setRequestMethod(HttpConnection.GET);
                
//                c.setRequestProperty("User-Agent","Profile/MIDP-2.1 Configuration/CLDC-1.1");
//                c.setRequestProperty("If-Modified-Since","9 Oct 2012 17:49:31 GMT");
//                c.setRequestProperty("Content-Language","en-US");
//                c.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//                c.setRequestProperty("Accept","text/html");
               
                //OutputStream for writing data to file.

//                out = c.openOutputStream();

//                out.flush();

                //Read From the File

                try{
//                    int ResponseCode = c.getResponseCode();
//                    if(ResponseCode != HttpConnection.HTTP_OK){
//                        throw new Exception("Error");
//                    }
                    is = c.openInputStream();

                    //loop to read every character from file and append it to StringBuffer
                    int ch;
                    while((ch = is.read())  != -1){
                        buff.append((char) ch);
                    }
                    
                    String version = getAppProperty("MIDlet-Version");
                    
                     if(!buff.toString().equals(version)){
                        text.append("Ka Update");
                     }
                        
//                    try {
//                        platformRequest("http://77.81.240.20/Raportimi_i_imunizimit.jar");
                        
//                    } catch (Exception e) {
                        
                        display.setCurrent(text);
                        
//                    }
                    
//                    text.append(buff.toString());
//                    display.setCurrent(text);

                }catch(Exception e){
                    sendSms(number, msg);
                }
            }catch(Exception e){
                sendSms(number, msg);
                display.setCurrent(text);
           }
        }
     });
    
    connection.start();
    
 }else if(command == start){
     
    try{

        recordStore = RecordStore.openRecordStore("Duy Nguyen", true );
        
        String outputData = "value"+fmc_id+"<Periudha>" + input + "</Periudha>\n" + "<Institucioni>" + input1 +"</Institucioni>\n" +
        "<0-11muaj>" + t17.getString() + "</0-11muaj>\n" + "<0-11muaj>" + t21.getString() + "</0-11muaj>\n" + "<0-11muaj>" + t25.getString() + "</0-11muaj>\n"+
        "<0-11muaj>" + t29.getString() + "</0-11muaj>\n" + "<12-23muaj>" + t30.getString() + "</12-23muaj>\n"  + "</24-59muaj>\n" + "<0-11muaj>" + t33.getString() + "</0-11muaj>\n" + "<12-23muaj>" + t34.getString() + "</12-23muaj>\n"+ "<0-11muaj>" + t37.getString() + "</0-11muaj>\n" + "<12-23muaj>" + t38.getString() + "</12-23muaj>\n"  +
        "<12-23muaj>" + t42.getString() + "</12-23muaj>\n" + "<24-59muaj>" + t43.getString() + "</24-59muaj>\n" + "<mbi 59 muaj>" + t44.getString() + "</mbi 59 muaj>\n" + "<12-23muaj>" + t46.getString() + "</12-23muaj>\n" + "<24-59muaj>" + t47.getString() + "</24-59muaj>\n" + "<mbi 59muaj>" + t48.getString() + "</mbi 59muaj>\n" +  "<12-23muaj>" + t50.getString() + "</12-23muaj>\n" + "<24-59muaj>" + t51.getString() + "</24-59muaj>\n" + "<mbi 59muaj>" + t52.getString() + "</mbi 59muaj>\n" +
        "<12-23muaj>" + t54.getString() + "</12-23muaj>\n" + "<24-59muaj>" + t55.getString() + "</24-59muaj>\n" + "<mbi 59 muaj>" + t56.getString() + "</mbi 59 muaj>\n" +
        "<12-23muaj>" + t61.getString() + "</12-23muaj>\n" + "<mbi 23 muaj>" + t62.getString() + "</mbi 23 muaj>\n" + "<12-23muaj>" + t64.getString() + "</12-23muaj>\n" + "<mbi 23 muaj>" + t65.getString() + "</mbi 23muaj>\n" + "<mbi 23 muaj>" + t68.getString() + "<mbi 23 muaj>" + t71.getString() + "</mbi 23 muaj>\n"+
        "<mbi 23 muaj>" + t74.getString() + "</mbi 23 muaj>\n" + "<mbi 23 muaj>" + t77.getString() + "</mbi 23muaj>\n" + "<mbi 23 muaj>" + t80.getString();
        
        byte[] byteOutputData = outputData.getBytes();
        recordStore.addRecord(byteOutputData, 0,
        byteOutputData.length);

        //  String outputData1 = "<institucionit>" + input1 +"</institucionit>";
        //  byte[] byteOutputData1 = outputData1.getBytes();
        //  recordStore.addRecord(byteOutputData1, 0,
        //  byteOutputData1.length);

        byte[] byteInputData = new byte[1];
        int length = 0;
        for (int x = 1; x <= recordStore.getNumRecords(); x++){
        if (recordStore.getRecordSize(x) > byteInputData.length){
        byteInputData = new byte[recordStore.getRecordSize(x)];
        }
        length = recordStore.getRecord(x, byteInputData, 0);
        }

        alert = new Alert("Records", new String(byteInputData, 0,
        length), null, AlertType.INFO);
        alert.setTimeout(Alert.FOREVER);
        display.setCurrent(alert);
        recordStore.closeRecordStore();
        
    }catch (Exception e){
    }

    if(RecordStore.listRecordStores() != null){
        try{
        RecordStore.deleteRecordStore("Duy Nguyen");
        }catch (Exception e){}
    }
  }
  
  /*
   *
   *  BACK COMMANDS
   *
   */

//  if(command == back2){
//     display.setCurrent(form);
//  }
      if(command == back3){
         display.setCurrent(form);
      }

      if(command == back4){
         display.setCurrent(form5);
      }

      if(command == back5){
         display.setCurrent(form6);
      }

      if(command == back6){
         display.setCurrent(form7);
      }

      if(command == back7){
         display.setCurrent(form8);
      }

  }
  
//  String Item Command Listener
  public void commandAction(Command c, Item item) {
     if(c == log){
        try {
            login();
         } catch (RecordStoreException ex) {
         }
     }
  }
 
  /*
   *
   * SEND SMS FUNCTION.
   *
   */

 public boolean sendSms(String number, String message) throws SecurityException{
    boolean result = true;
    try {
      //sets address to send message
      String addr = "sms://"+number;
      // opens connection
      MessageConnection conn = (MessageConnection) Connector.open(addr);
      // prepares text message
      TextMessage msg =
      (TextMessage)conn.newMessage(MessageConnection.TEXT_MESSAGE);
      // set text
      msg.setPayloadText(message);
      // send message
      conn.send(msg);
      conn.close();
    }catch (SecurityException se) {
        // probably the user has not allowed to send sms
        result = false;
    }catch (Exception e) {
        result = false;
    }
    return result;
  }
 
 /*
  *
  *LOGIN FUNCTION
  *
  */

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
            

//            Users_rs.closeRecordStore();

      if(value1.toLowerCase().equals(user.getString().toLowerCase()) && value2.toLowerCase().equals(pass.getString().toLowerCase())){

//           try {
//               Change_Language(getLang());
//               form.append(Language[0]);
               display.setCurrent(form);

//            } catch (RecordStoreException ex) {
//                form.append("fail");
//                display.setCurrent(form);
//            }
//           
      }else{
         text.append("Perdoruesi ose Fjalkalimi nuk eshte i sakte");
         display.setCurrent(text);
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
  
   private String getLang() throws RecordStoreException{
     
        try {
            
            Lang = RecordStore.openRecordStore("Language", true );
            
            byte[] byteInputData = new byte[1];
            int length = 0;
            
            for (int x = 1; x <= Lang.getNumRecords(); x++){
                if (Lang.getRecordSize(x) > byteInputData.length){
                    byteInputData = new byte[Lang.getRecordSize(x)];
                }
                length = Lang.getRecord(x, byteInputData, 0);
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
   
   public void Change_Language(String lang){
       if(lang.equals("Sr")){
            String Language [] = new String[]{"Next","Back","Send","Exit"};
//            Language [1] = "Back";
       }else if (lang.equals("Al")){
           String Language [] = new String[]{"Vazhdo","Kthehu","Dergo","Dil"};
       }

   }
  
 
}