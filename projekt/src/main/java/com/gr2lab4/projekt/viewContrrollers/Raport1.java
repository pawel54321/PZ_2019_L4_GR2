package com.gr2lab4.projekt.viewContrrollers;

import javafx.scene.control.DatePicker;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gr2lab4.projekt.MainApp;
import com.gr2lab4.projekt.Entities.Pracownik;
import com.gr2lab4.projekt.Entities.Zadanie;
import com.gr2lab4.projekt.Entities.Dao.ZadanieDAO;
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

import javafx.collections.ObservableList;

public class Raport1 {
	public static final PdfNumber LANDSCAPE = new PdfNumber(90);

	// private static final Logger LOG =
	// LoggerFactory.getLogger(PdfGenerateTest.class);
	public static String RESULT;

	public static void generatePDF(String filePathAndName, DatePicker data1, DatePicker data1drugi) {
	
		
		RESULT = filePathAndName;
		File file = new File(RESULT);
		file.getParentFile().mkdirs();
		try {
			createPdf(RESULT, data1, data1drugi);
			Desktop.getDesktop().open(file);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void createPdf(String filename, DatePicker data1, DatePicker data1drugi) throws IOException {
		// Initialize PDF writer
		PdfWriter writer = new PdfWriter(RESULT);

		// Initialize PDF document
		PdfDocument pdf = new PdfDocument(writer);

		Document document = new Document(pdf, PageSize.B3.rotate());

		Table table = new Table(3);
		table.setMarginBottom(30f);
		table.addCell(getCell("Firma pomidorobranie SA\n" + "Wymyslona 15/3, 54-455 Warszawa\n"
				+ "Tel.:111-111-111, NIP: 111-111-11-11\n", TextAlignment.LEFT));
		table.addCell(getCell(" ", TextAlignment.CENTER));
		Table table3 = new Table(1);

		table3.addCell(new Cell().add("Miejsce wystawienia").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
		table3.addCell(new Cell().add("Warszawa"));
		table3.addCell(new Cell().add("Data wystawienia").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
		table3.addCell(new Cell().add(LocalDate.now()+""));
		table3.addCell(new Cell().add("Data otrzymania").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
		table3.addCell(new Cell().add(LocalDate.now()+""));

		table.addCell(getCell2(table3, TextAlignment.CENTER));
		document.add(table);

		Text boldX = new Text("Raport dotyczacy wszystkich zadan w danym przedziale\n Od: " + data1.getValue()
			+ "\n Do: " + data1drugi.getValue()).setFontSize(44.0f)
		.setTextAlignment(TextAlignment.CENTER);
		Paragraph p = new Paragraph(boldX);
		document.add(p);

		Table table6 = new Table(3);
		table6.setMarginBottom(30f);
		table6.addCell(getCell(" ", TextAlignment.LEFT));
		table6.addCell(getCell(" ", TextAlignment.RIGHT));
		document.add(table6);

		Table table22 = new Table(3);
		table22.setMarginLeft(150f);
		table22.setMarginRight(150f);
		table22.setMarginBottom(30f);
		table22.addCell(getCell(" ", TextAlignment.CENTER));

		document.add(table22);

		Table table2 = new Table(10);
		table2.setMarginBottom(30f);
		table2.addCell(new Cell().add("Lp.").setBackgroundColor(Color.LIGHT_GRAY));
		table2.addCell(new Cell(1, 2).add("Tytul").setBackgroundColor(Color.LIGHT_GRAY));
		table2.addCell(new Cell(1, 3).add("Tresc").setBackgroundColor(Color.LIGHT_GRAY));
		table2.addCell(new Cell(1, 2).add("Data rozpoczecia").setBackgroundColor(Color.LIGHT_GRAY));
		table2.addCell(new Cell(1, 2).add("Data zakonczenia").setBackgroundColor(Color.LIGHT_GRAY));

		List<Zadanie> resultList = MainApp.instance.zadanieDAO.findAll();
		
		List<Zadanie> lista = resultList.stream().filter(a -> 
		( convertToLocalDateViaSqlDate(a.getData_rozp()).isAfter(data1.getValue()) || 
		convertToLocalDateViaSqlDate(a.getData_rozp()).isEqual(data1.getValue()) ) &&
		
		( convertToLocalDateViaSqlDate(a.getData_rozp()).isBefore(data1drugi.getValue()) ||  
		convertToLocalDateViaSqlDate(a.getData_rozp()).isEqual(data1drugi.getValue()) )
				).collect(Collectors.toList());
		
		
		//print(LocalDate.of(a.getData_rozp().getYear(),a.getData_rozp().getMonth(),a.getData_rozp().getDay())+"");
		int mojid = 1;
		for (int i = 0; i < lista.size(); i++) {

			
			
			//if(data1.getValue() == LocalDate.of(resultList.get(i).getData_rozp().getYear(), resultList.get(i).getData_rozp().getMonth(), resultList.get(i).getData_rozp().getDay()) )
			//{
				table2.addCell(new Cell().add(mojid + ""));
				table2.addCell(new Cell(1, 2).add(lista.get(i).getTytul() + ""));
				table2.addCell(new Cell(1, 3).add(lista.get(i).getTresc() + ""));
				table2.addCell(new Cell(1, 2).add(lista.get(i).getData_rozp() + ""));
	
				if (resultList.get(i).getData_ukon() != null) {
					table2.addCell(new Cell(1, 2).add(lista.get(i).getData_ukon() + ""));
				} else
					table2.addCell(new Cell(1, 2).add("nie dotyczy"));
			//}
				mojid++;
		}

		document.add(table2);

		Table table4 = new Table(4);
		table4.setMarginLeft(300f);
		table4.setMarginRight(100f);
		table4.setMarginBottom(30f);

		document.add(table4);

		// Close document
		document.close();
	}
	
	public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
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
