package mgw.domaine_webchatt.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Validateur {

    public static String fmt="dd/MM/yyyy HH:mm:ss";
    public static String fmtD="dd/MM/yyyy";
    public static String fmtSQL01="yyyy/dd/MM HH:mm:ss";
    public static String fmtSQL02="dd/MM/yyyy HH:mm:ss";
    
    private static String code;
    /**
     * converti un string "dd/MM/yyyy HH:mm:ss" en objet Date
     * @param dateTxt
     * @return
     * @throws ParseException
     */
    public static Date convertStringFrToDate(String dateTxt) throws ParseException{
        // la forme en format français => "jj/mm/aaaa HH:mm/ss"
        // la forme en format Anglais  => "dd/MM/yyyy HH:mm/ss"
        //fmt="dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(fmtD);
        sdf.setLenient(false);//<==REFUSER LES DEPASSEMENTS !
        Date d=sdf.parse(dateTxt);
        
        return d;
    }
   
    /**
     * Convertit un objet Date en String "yyyy/dd/MM HH:mm:ss"
     * @param d
     * @return
     * @throws ParseException
     */
    public static String convertToDateSQL01(Date d) throws ParseException{
        // la forme en format français => "jj/mm/aaaa HH:mm/ss"
        // la forme en format Anglais  => "dd/MM/yyyy HH:mm/ss"
        //fmt="dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(fmtSQL02);
        String sd=sdf.format(d);       
        return sd;
    }
    
  /**
     * Convertit un objet Date en String fmtD="dd/MM/yyyy"
     * @param d
     * @return
     * @throws ParseException
     */
    public static String convertDateSQL01ToString(Date d) throws ParseException{
        // la forme en format français => "jj/mm/aaaa HH:mm/ss"
        // la forme en format Anglais  => "dd/MM/yyyy HH:mm/ss"
        //fmt="dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(fmtD);
        String sd=sdf.format(d);       
        return sd;
    }
    /**
     * méthode geherant un nombre aléatoire compris entre 0 et 50
     * @return
     */
    public static int generateRandomInt(){
    	Random r = new Random();
    	Random r2 = new Random();
    	return r.nextInt(50)*(r2.nextInt(150));

    }
   
    
    /**
     * méthode convertissant un nombre en son string code couleur associé, reconnu par bootstrap
     * @return
     */
    public static String bootstrapRowColorValue(int n){
    	
    	switch((n%10)%5){
		case 0:
			code="active";
			break;
		case 1:
			code="success";
			break;
		case 2:
			code="info";
			break;
		case 3:
			code= "warning";
			break;
		case 4:
			code= "danger";
			break;
		default :
            }
    	return code;
    }
}