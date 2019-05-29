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

    /**
     * id zadania.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    /**
     * zmienna przechowuje tytuł zadania.
     */
    @Column(name="tytul")
    protected String tytul;

    /**
     * zmienna przechowuje tresc zadania.
     */
    @Column(name="tresc")
    protected String tresc;

    /**
     * zmienna przechowuje date dodania zadania.
     */
    @Column(name="data_rozp")
    protected Date data_rozp;

    /**
     * zmienna przechowuje date ukonczenia zadania.
     */
    @Column(name="data_ukon")
    protected Date data_ukon;

    /**
     * zmienna przechowuje status zadania.
     */
    @Column(name="status")
    protected String status;

    /**
     * zmienna odwoluje sie do obiektu uzytkownika przypisanego do zadania.
     */
    @ManyToOne
    @JoinColumn(name = "id_pracownika")
    protected Pracownik pracownik;

    public Zadanie() {

    }

    /**
     * Konstruktor obiektu zadania
     * @param tytul parametr przekazuje tresc tytulu do zmiennej
     * @param tresc parametrz przekazuje tresc zadania do zmiennej
     * @param data_rozp parametr przekazuje parametr dodania zadania
     * @param status parametr przekazuje status zadania
     * @param pracownicy parametr przekazuje obiekt pracownika
     */
    public Zadanie( String tytul, String tresc, Date data_rozp, String status, Pracownik pracownicy) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.data_rozp = data_rozp;
        this.status = status;
       
    }



    public String getTytul() {
        return tytul;
    }


    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
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



    public void setStatus(String status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Zadanie [id=" + id + ", tytul=" + tytul + ", tresc=" + tresc + ", data_rozp=" + data_rozp
				+ ", data_ukon=" + data_ukon + ", status=" + status + ", pracownicy=" + pracownik + "]";
	} 

}
