import com.esempio.RentalCar.dao.PrenotazioneDAOImpl;
import com.esempio.RentalCar.dao.UtenteDAO;
import com.esempio.RentalCar.dao.UtenteDAOImpl;
import com.esempio.RentalCar.dao.VeicoloDAOImpl;
import com.esempio.RentalCar.entities.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        UtenteDAOImpl ud = new UtenteDAOImpl();
        PrenotazioneDAOImpl pd = new PrenotazioneDAOImpl();
        VeicoloDAOImpl vd = new VeicoloDAOImpl();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Date date = new Date();
        boolean appro = true;


        //salvo prenotazione
       /* Prenotazione p = new Prenotazione();
        pd.savePrenotazione(p);
        PeriodoPrenotazione periodoPrenotazione = new PeriodoPrenotazione();
        p.setPeriodoPrenotazione(periodoPrenotazione);
        Utente utente = ud.getUtente(1L);
        Veicolo veicolo = vd.getVeicolo(1L);
        p.setUtente(utente);
        p.setVeicolo(veicolo);
        pd.updatePrenotazione(p);*/

        //inserisco l'admin
        /*Utente utente = new Utente();
        utente.setNome("Admin");
        utente.setCognome("Admin");
        utente.setUsername("admin");
        utente.setPassword(bCryptPasswordEncoder.encode("admin"));
        utente.setRuolo(Ruolo.SUPERUSER);
        ud.saveUtente(utente);*/



//        Prova per salvare l'utente

        /*Utente utente = new Utente("Nicola", "Vac", date ,"w","a", Ruolo.CUSTOMER,null);

        ud.saveUtente(utente);*/

        //Prova per modificare l'utente

        /*Long id = (long) 5L;
        Utente utente = ud.getUtente(id);
        utente.setNome("Luca");
        ud.updateUtente(utente);*/



        //inserimento auto
      /*  Veicolo veicolo = new Veicolo("ALFA ROMEO","Giulietta", TipoVeicolo.AUTO,2012,"EL042NV",null);
        vd.saveVeicolo(veicolo);
        Veicolo veicolo2 = new Veicolo("MERCEDES","Classe C", TipoVeicolo.AUTO,2007,"NN924AE",null);
        vd.saveVeicolo(veicolo2);
        Veicolo veicolo3 = new Veicolo("CITROEN","C4", TipoVeicolo.AUTO,2015,"LJ123AP",null);
        vd.saveVeicolo(veicolo3);
        Veicolo veicolo4 = new Veicolo("Volkswagen","T-Rock", TipoVeicolo.SUV,2020,"BN499LU",null);
        vd.saveVeicolo(veicolo4);
        Veicolo veicolo6 = new Veicolo("FIAT","Fiorino", TipoVeicolo.FURGONE,2010,"ZB105YX",null);
        vd.saveVeicolo(veicolo6);
        Veicolo veicolo5 = new Veicolo("FIAT","Ulisse", TipoVeicolo.MINIVAN,2014,"MC810MZ",null);
        vd.saveVeicolo(veicolo5);
        Veicolo veicolo7 = new Veicolo("OPEL","Zafira", TipoVeicolo.MINIVAN,2020,"AQ874PO",null);
        vd.saveVeicolo(veicolo7);
        Veicolo veicolo8 = new Veicolo("SMART","ForTwo", TipoVeicolo.AUTO,2013,"TB732PA",null);
        vd.saveVeicolo(veicolo8);
        Veicolo veicolo9 = new Veicolo("FIAT","500", TipoVeicolo.AUTO,2021,"ER025CX",null);
        vd.saveVeicolo(veicolo9);
        Veicolo veicolo10 = new Veicolo("PEUGEOT","208", TipoVeicolo.AUTO,2001,"AS756LK",null);
        vd.saveVeicolo(veicolo10);*/



         //Prova per eliminare l'utente
       /* Long id = (long) 3;
        Utente utente = ud.getUtente(id);
        ud.deleteUtente(utente);*/


        /*//Prova metodo getListaUtenti
        List<Utente> utenti = ud.getListaUtenti();
        for (Utente u : utenti) {
            System.out.println(u);
        }*/


    }
}

