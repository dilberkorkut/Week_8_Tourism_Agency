package com.tourismagency.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setLayout(){ //uygulamanin gorunumu ayarlanir.
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if("`Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static int screenCenterPoint(String exxen, Dimension size) {
        int point;
        switch (exxen) {
            case "x" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().width = size.width) / 2;
                break;
            case "y" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().height = size.height) / 2;
                break;
            default :
                point = 0;
        }
        return point;
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static boolean isComboBoxEmpty(JComboBox cmb) {
        return cmb.getModel().getSelectedItem().equals("");
    }

    public static void showMsg(String str){
        optionPageTR();
        String msg;
        String title;

        switch(str) {
            case "fill":
                msg = "Please fill the all empty fields!";
                title = "Error!";
                break;
            case "done":
                msg = "Operation succesfull";
                title = "Done";
                break;
            case "error":
                msg = "Bir hata olustu!";
                title = "Error!";

            default :
                msg = str;
                title = "Message";
        }

        JOptionPane.showMessageDialog(null , msg , title , JOptionPane.INFORMATION_MESSAGE );
        // JOptionePane ile bir iletisim penceresi olusturur ve mesaji gosterir.(onay kutusu,uyari iletileri...)
    }

    public static boolean confirm(String str) { //kullanicidan onay istemek icin kullanilir.
        String msg;
        optionPageTR();

        switch (str) {
            case "sure" :
                msg = "Are you sure you want to proceed with this operation?";
                break;
            default:
                msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Is this your final choice?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPageTR(){ //UIManager ile iletisim penceresini turkce olarak degistirir.
        UIManager.put("OptionPane.okButtonText" , "Tamam");
        UIManager.put("OptionPane.yesButtonText" , "Evet");
        UIManager.put("OptionPane.noButtonText" , "Hayir");

    }
}
