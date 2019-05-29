package com.gr2lab4.projekt.viewContrrollers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.gr2lab4.projekt.MainApp;
import com.gr2lab4.projekt.Entities.Zadanie;
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

public class Raport3 {
	public static final PdfNumber LANDSCAPE = new PdfNumber(90);

	// private static final Logger LOG =
	// LoggerFactory.getLogger(PdfGenerateTest.class);
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
		List<Zadanie> resultList = MainApp.instance.zadanieDAO.findAll();
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
		table3.addCell(new Cell().add("11-06-2019"));
		table3.addCell(new Cell().add("Data otrzymania").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
		table3.addCell(new Cell().add("11-06-2019"));
		table.addCell(getCell2(table3, TextAlignment.CENTER));
		document.add(table);

		Text boldX = new Text("Raport dotyczacy zadan jakie zostaly ukonczone w danym miesiacu").setFontSize(44.0f)
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

		for (int i = 0; i < resultList.size(); i++) {
			if (resultList.get(i).getData_ukon() != null) {

				System.out.print(resultList.get(i).getData_ukon().getMonth() + ","
						+ Calendar.getInstance().get(Calendar.MONTH) + "\n");

				if (resultList.get(i).getData_ukon().getMonth() == Calendar.getInstance().get(Calendar.MONTH)) {
					table2.addCell(new Cell().add(i + 1 + ""));
					table2.addCell(new Cell(1, 2).add(MainApp.instance.appCfg.listaZadan.get(i).getTytul() + ""));
					table2.addCell(new Cell(1, 3).add(MainApp.instance.appCfg.listaZadan.get(i).getTresc() + ""));
					table2.addCell(new Cell(1, 2).add(MainApp.instance.appCfg.listaZadan.get(i).getData_rozp() + ""));
					table2.addCell(new Cell(1, 2).add(MainApp.instance.appCfg.listaZadan.get(i).getData_ukon() + ""));
				}
			}
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
