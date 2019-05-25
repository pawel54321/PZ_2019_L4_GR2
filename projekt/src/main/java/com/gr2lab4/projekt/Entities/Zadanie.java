package com.gr2lab4.projekt.Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javafx.beans.property.SimpleStringProperty;

/**
 * Obiekt reprezentujący zadanie w firmie.
 * @author marcinrosol
 */
@Entity
@Table(name = "Zadanie")
public class Zadanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="tytul")
    private String tytul;

    @Column(name="tresc")
    private String tresc;

    @Column(name="data_rozp")
    private Date data_rozp;

    @Column(name="data_ukon")
    private Date data_ukon;

    @Column(name="aktywne")
    private int aktywne;

    @ManyToOne
    @JoinColumn(name = "id_pracownika")
    private Pracownik pracownik;

    public Zadanie() {

    }

    public Zadanie( String tytul, String tresc, Date data_rozp, Date data_ukon, int aktywne, Pracownik pracownicy) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.data_rozp = data_rozp;
        this.data_ukon = data_ukon;
        this.aktywne = aktywne;
       
    }



    public String getTytul() {
        return tytul;
    }


    public int getId() {
        return id;
    }

    public int getAktywne() {
        return aktywne;
    }

    public Pracownik getPracownik() {
       return pracownik;
    }
    
    public void setPracownik(Pracownik pracownik) {
    	this.pracownik = pracownik;
    }

    public Date getData_rozp() {
        return data_rozp;
    }

    public Date getData_ukon() {
        return data_ukon;
    }

    public int isAktywne() {
        return aktywne;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;//new SimpleStringProperty(tytul);
    }

    public String getTresc() {
        return tresc;//.get();
    }


    public void setTresc(String tresc) {
        this.tresc = tresc;//new SimpleStringProperty(tresc);
    }

    public void setData_rozp(Date data_rozp) { //throws ParseException {
    	
    	
    	//SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
    	//Date data_r = dt.parse(data_rozp+"");
        this.data_rozp = data_rozp; //data_r;
    }

    public void setData_ukon(Date data_ukon) { //throws ParseException {
    	
    //	SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
    //	Date data_u = (Date)dt.parse(data_ukon+"");
    	
        this.data_ukon = data_ukon; //data_u;
    }



    public void setAktywne(int aktywne) {
        this.aktywne = aktywne;
    }

	@Override
	public String toString() {
		return "Zadanie [id=" + id + ", tytul=" + tytul + ", tresc=" + tresc + ", data_rozp=" + data_rozp
				+ ", data_ukon=" + data_ukon + ", aktywne=" + aktywne + ", pracownicy=" + pracownik + "]";
	} 

}
