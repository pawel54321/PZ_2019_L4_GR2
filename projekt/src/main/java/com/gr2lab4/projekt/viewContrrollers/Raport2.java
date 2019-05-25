package com.gr2lab4.projekt.viewContrrollers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.gr2lab4.projekt.MainApp;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

public class Raport2 {
	 public static final PdfNumber LANDSCAPE = new PdfNumber(90);

	    //private static final Logger LOG = LoggerFactory.getLogger(PdfGenerateTest.class);
	    public static String RESULT;
	    
	    public static void generatePDF(String filePathAndName) {
	        RESULT = filePathAndName;
	        File file = new File(RESULT);
	        file.getParentFile().mkdirs();
	        try {
	            createPdf(RESULT);
	            Desktop.getDesktop().open(file);
	        } catch (IOException ex) {
	            System.out.println(ex.getMessage());
	        }
	    }

	    public static void createPdf(String filename) throws IOException {
	        //Initialize PDF writer
	        PdfWriter writer = new PdfWriter(RESULT);

	        //Initialize PDF document
	        PdfDocument pdf = new PdfDocument(writer);

	        Document document = new Document(pdf, PageSize.B3.rotate());

	        Table table = new Table(3);
	        table.setMarginBottom(30f);
	        table.addCell(getCell("Firma pomidorobranie SA\n"
	                + "Wymyslona 15/3, 54-455 Warszawa\n"
	                + "Tel.:111-111-111, NIP: 111-111-11-11\n"
	               , TextAlignment.LEFT));
	        table.addCell(getCell(" ", TextAlignment.CENTER));
	        //table.addCell(getCell("Text to the right", TextAlignment.RIGHT));
	        Table table3 = new Table(1);
	        
	      //  for(int i=0; i<MainApp.instance.appCfg.listaZadan.size() ; i++)
	      //  {
		        table3.addCell(new Cell().add("Miejsce wystawienia").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
		        table3.addCell(new Cell().add("Warszawa"));
		      //  table3.addCell(new Cell().add("Data sprzedaży").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
		      //  table3.addCell(new Cell().add("08-05-01"));
		        table3.addCell(new Cell().add("Data wystawienia").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
		        table3.addCell(new Cell().add("11-06-2019"));
		        table3.addCell(new Cell().add("Data otrzymania").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
		        table3.addCell(new Cell().add("11-06-2019"));
	        //}
	        table.addCell(getCell2(table3, TextAlignment.CENTER));
	        document.add(table);

	        Text boldX = new Text("Raport dotyczacy wszystkich zadan dla pracownika:\n Imie:"+MainApp.instance.appCfg.listaZadan.get(0).getPracownik().getImie() +
	        		"\n Nazwisko: "+MainApp.instance.appCfg.listaZadan.get(0).getPracownik().getNazwisko())
	                .setFontSize(44.0f).setTextAlignment(TextAlignment.CENTER);
	        Paragraph p = new Paragraph(boldX);
	        document.add(p);

	        Table table6 = new Table(3);
	        table6.setMarginBottom(30f);
	        table6.addCell(getCell(" ", TextAlignment.LEFT));
	      //  table6.addCell(getCell("Dokument dostawcy: 318/2008", TextAlignment.CENTER));
	        table6.addCell(getCell(" ", TextAlignment.RIGHT));
	        document.add(table6);

	        Table table22 = new Table(3);
	        table22.setMarginLeft(150f);
	        table22.setMarginRight(150f);
	        table22.setMarginBottom(30f);
	     //   table22.addCell(getCell("Drogeria NOVUM\n"
	     //           + "Kolorowa 98/3\n"
	     //           + "65-465 Sosnowiec\n"
	     //           + "NIP: 894-151-57-99\n", TextAlignment.LEFT));
	        table22.addCell(getCell(" ", TextAlignment.CENTER));
	        //table.addCell(getCell("Text to the right", TextAlignment.RIGHT));
	    //    table22.addCell(getCell("Fiirma przyk. systemu InsERT GT\n"
	    //            + "Bławatkowa 25/3\n"
	    //            + "54-455 Wrocław\n"
	    //            + "NIP: 894-151-57-99\n", TextAlignment.LEFT));
	        document.add(table22);

	        Table table2 = new Table(10);
	        table2.setMarginBottom(30f);
	        table2.addCell(new Cell().add("Lp.").setBackgroundColor(Color.LIGHT_GRAY));
	        table2.addCell(new Cell(1, 2).add("Tytul").setBackgroundColor(Color.LIGHT_GRAY));
	        table2.addCell(new Cell(1, 3).add("Tresc").setBackgroundColor(Color.LIGHT_GRAY));
	        table2.addCell(new Cell(1, 2).add("Data rozpoczecia").setBackgroundColor(Color.LIGHT_GRAY));
	        table2.addCell(new Cell(1, 2).add("Data zakonczenia").setBackgroundColor(Color.LIGHT_GRAY));

	        
	        for(int i=0; i < MainApp.instance.appCfg.listaZadan.size(); i++)
	        {       
	        	/*
	        	String a = MainApp.instance.appCfg.listaZadan.get(i).getData_rozp()+"";
	        	String b = MainApp.instance.appCfg.listaZadan.get(i).getData_ukon()+"";
	        	
	        	SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
	        	Date data_r = null;
				try {
					data_r = dt.parse(a);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        	SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
	        	Date data_u = null;
				try {
					data_u = dt2.parse(b);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
	        	
	        	
	        	//if(MainApp.instance.appCfg.listaZadan.get(i).getData_ukon()!=null)
	        	//{
	        		
	        		//System.out.print(MainApp.instance.appCfg.listaZadan.get(i).getData_ukon().getMonth()+","+ Calendar.getInstance().get(Calendar.MONTH)+"\n");
	        		
		        //	if(MainApp.instance.appCfg.listaZadan.get(i).getData_ukon().getMonth()==Calendar.getInstance().get(Calendar.MONTH))
		        //	{
		        	table2.addCell(new Cell().add(i+1+""));
			        table2.addCell(new Cell(1, 2).add(MainApp.instance.appCfg.listaZadan.get(i).getTytul()+""));
			        table2.addCell(new Cell(1, 3).add(MainApp.instance.appCfg.listaZadan.get(i).getTresc()+""));
			        // table2.addCell(new Cell(1, 2).add(dt.format(data_r)+""));
			        table2.addCell(new Cell(1, 2).add(MainApp.instance.appCfg.listaZadan.get(i).getData_rozp()+""));
			        
			        if(MainApp.instance.appCfg.listaZadan.get(i).getData_ukon()!=null)
			        {
			        	table2.addCell(new Cell(1, 2).add(MainApp.instance.appCfg.listaZadan.get(i).getData_ukon()+""));			        	
			    //    table2.addCell(new Cell(1, 2).add(dt2.format(data_u)+""));
			        }
			        else
			        	table2.addCell(new Cell(1, 2).add("nie dotyczy"));	
		        //	}
	        	//}
	        }

//	        table2.addCell(new Cell().add("1."));
//	        table2.addCell(new Cell().add("DZSOZ0"));
//	        table2.addCell(new Cell(1, 2).add("So dezodorant perf. 20ml"));
//	        table2.addCell(new Cell().add("2,000"));
//	        table2.addCell(new Cell().add("szt."));
//	        table2.addCell(new Cell().add("42,51"));
//	        table2.addCell(new Cell().add("23"));
//	        table2.addCell(new Cell().add("18,70"));
//	        table2.addCell(new Cell().add("85,02"));
//
//	        table2.addCell(new Cell().add("2."));
//	        table2.addCell(new Cell().add("POYAR03"));
//	        table2.addCell(new Cell(1, 2).add("Pomadka długotrwała 03"));
//	        table2.addCell(new Cell().add("3,000"));
//	        table2.addCell(new Cell().add("szt."));
//	        table2.addCell(new Cell().add("10,63"));
//	        table2.addCell(new Cell().add("8"));
//	        table2.addCell(new Cell().add("2,23"));
//	        table2.addCell(new Cell().add("31,89"));
//
//	        table2.addCell(new Cell().add("3."));
//	        table2.addCell(new Cell().add("WOFO150"));
//	        table2.addCell(new Cell(1, 2).add("Forever woda toa 15 ml ozd."));
//	        table2.addCell(new Cell().add("4,000"));
//	        table2.addCell(new Cell().add("szt."));
//	        table2.addCell(new Cell().add("104,63"));
//	        table2.addCell(new Cell().add("23"));
//	        table2.addCell(new Cell().add("122,23"));
//	        table2.addCell(new Cell().add("631,89"));
	        document.add(table2);
	        
	        Table table4 = new Table(4);
	        table4.setMarginLeft(300f);
	        table4.setMarginRight(100f);
	        table4.setMarginBottom(30f);
	       // table4.addCell(new Cell().add("Według stawki VAT").setBackgroundColor(Color.LIGHT_GRAY));
	       // table4.addCell(new Cell().add("wartość netto").setBackgroundColor(Color.LIGHT_GRAY));
	       // table4.addCell(new Cell().add("kwota VAT").setBackgroundColor(Color.LIGHT_GRAY));
	       // table4.addCell(new Cell().add("wartość brutto").setBackgroundColor(Color.LIGHT_GRAY));

	       // table4.addCell(new Cell().add("podstawowy podatek VAT 23%"));
	       // table4.addCell(new Cell().add("722"));
	       // table4.addCell(new Cell().add("158"));
	       // table4.addCell(new Cell().add("881"));

	       // table4.addCell(new Cell().add("POdatek VAT 7%"));
	       // table4.addCell(new Cell().add("31"));
	       // table4.addCell(new Cell().add("2"));
	       // table4.addCell(new Cell().add("34"));
	        document.add(table4);

	        //Close document
	        document.close();
	    }

	    public static Cell getCell(String text, TextAlignment alignment) {
	        Cell cell = new Cell().add(new Paragraph(text));
	        cell.setPadding(0);
	        cell.setTextAlignment(alignment);
	        cell.setBorder(Border.NO_BORDER);
	        return cell;
	    }

	    public static Cell getCell2(IBlockElement o, TextAlignment alignment) {
	        Cell cell = new Cell().add(o);
	        cell.setPadding(0);
	        cell.setTextAlignment(alignment);
	        cell.setBorder(Border.NO_BORDER);
	        return cell;
	    }

	    static class PageRotationEventHandler implements IEventHandler {

	        protected PdfNumber rotation = LANDSCAPE;

	        public void setRotation(PdfNumber orientation) {
	            this.rotation = orientation;
	        }

	        @Override
	        public void handleEvent(Event event) {
	            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
	            docEvent.getPage().put(PdfName.Rotate, rotation);
	        }
	    }
}
