package pdfgeneratetest;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import static com.itextpdf.kernel.pdf.PdfName.Event;
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
import com.sun.javafx.font.FontFactory;
import java.awt.Desktop;
import java.awt.PageAttributes;
import static java.awt.PageAttributes.OrientationRequestedType.PORTRAIT;
import java.io.File;
import java.util.Random;

public class GeneratePdf {

    public static final PdfNumber LANDSCAPE = new PdfNumber(90);

    //private static final Logger LOG = LoggerFactory.getLogger(PdfGenerateTest.class);
    public static String RESULT;

    public static void main(String[] args) throws IOException {
        pdfgeneratetest.GeneratePdf.generatePDF("results/fakturomania.pdf");
    }
    
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
        table.addCell(getCell("Firma przykładowa systemu InsERT GT\n"
                + "Bławatkowa 25/3, 54-455 Wrocław\n"
                + "Tel.:354-65-89, NIP: 111-111-11-11\n"
                + "nazwa banku\n"
                + "numer rachunku", TextAlignment.LEFT));
        table.addCell(getCell(" ", TextAlignment.CENTER));
        //table.addCell(getCell("Text to the right", TextAlignment.RIGHT));
        Table table3 = new Table(1);
        table3.addCell(new Cell().add("Miejsce wystawienia").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
        table3.addCell(new Cell().add("Wrocław"));
        table3.addCell(new Cell().add("Data sprzedaży").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
        table3.addCell(new Cell().add("08-05-01"));
        table3.addCell(new Cell().add("Data wystawienia").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
        table3.addCell(new Cell().add("08-05-01"));
        table3.addCell(new Cell().add("Data otrzymania").setBackgroundColor(Color.LIGHT_GRAY).setHeight(23f));
        table3.addCell(new Cell().add("08-05-01"));
        table.addCell(getCell2(table3, TextAlignment.CENTER));
        document.add(table);

        Text boldX = new Text("Faktura VAT wewnetrzna z tytulu nabycia "
                + "wewnatrzwspolnotowego: 7/2008")
                .setFontSize(35.0f);
        Paragraph p = new Paragraph(boldX);
        document.add(p);

        Table table6 = new Table(3);
        table6.setMarginBottom(30f);
        table6.addCell(getCell(" ", TextAlignment.LEFT));
        table6.addCell(getCell("Dokument dostawcy: 318/2008", TextAlignment.CENTER));
        table6.addCell(getCell(" ", TextAlignment.RIGHT));
        document.add(table6);

        Table table22 = new Table(3);
        table22.setMarginLeft(150f);
        table22.setMarginRight(150f);
        table22.setMarginBottom(30f);
        table22.addCell(getCell("Drogeria NOVUM\n"
                + "Kolorowa 98/3\n"
                + "65-465 Sosnowiec\n"
                + "NIP: 894-151-57-99\n", TextAlignment.LEFT));
        table22.addCell(getCell(" ", TextAlignment.CENTER));
        //table.addCell(getCell("Text to the right", TextAlignment.RIGHT));
        table22.addCell(getCell("Fiirma przyk. systemu InsERT GT\n"
                + "Bławatkowa 25/3\n"
                + "54-455 Wrocław\n"
                + "NIP: 894-151-57-99\n", TextAlignment.LEFT));
        document.add(table22);

        Table table2 = new Table(10);
        table2.setMarginBottom(30f);
        table2.addCell(new Cell().add("Lp.").setBackgroundColor(Color.LIGHT_GRAY));
        table2.addCell(new Cell().add("Symbol").setBackgroundColor(Color.LIGHT_GRAY));
        table2.addCell(new Cell(1, 2).add("Nazwa").setBackgroundColor(Color.LIGHT_GRAY));
        table2.addCell(new Cell().add("Ilość").setBackgroundColor(Color.LIGHT_GRAY));
        table2.addCell(new Cell().add("j.m.").setBackgroundColor(Color.LIGHT_GRAY));
        table2.addCell(new Cell().add("Cena netto").setBackgroundColor(Color.LIGHT_GRAY));
        table2.addCell(new Cell().add("Vat%").setBackgroundColor(Color.LIGHT_GRAY));
        table2.addCell(new Cell().add("VAT").setBackgroundColor(Color.LIGHT_GRAY));
        table2.addCell(new Cell().add("Wart.netto").setBackgroundColor(Color.LIGHT_GRAY));

        table2.addCell(new Cell().add("1."));
        table2.addCell(new Cell().add("DZSOZ0"));
        table2.addCell(new Cell(1, 2).add("So dezodorant perf. 20ml"));
        table2.addCell(new Cell().add("2,000"));
        table2.addCell(new Cell().add("szt."));
        table2.addCell(new Cell().add("42,51"));
        table2.addCell(new Cell().add("23"));
        table2.addCell(new Cell().add("18,70"));
        table2.addCell(new Cell().add("85,02"));

        table2.addCell(new Cell().add("2."));
        table2.addCell(new Cell().add("POYAR03"));
        table2.addCell(new Cell(1, 2).add("Pomadka długotrwała 03"));
        table2.addCell(new Cell().add("3,000"));
        table2.addCell(new Cell().add("szt."));
        table2.addCell(new Cell().add("10,63"));
        table2.addCell(new Cell().add("8"));
        table2.addCell(new Cell().add("2,23"));
        table2.addCell(new Cell().add("31,89"));

        table2.addCell(new Cell().add("3."));
        table2.addCell(new Cell().add("WOFO150"));
        table2.addCell(new Cell(1, 2).add("Forever woda toa 15 ml ozd."));
        table2.addCell(new Cell().add("4,000"));
        table2.addCell(new Cell().add("szt."));
        table2.addCell(new Cell().add("104,63"));
        table2.addCell(new Cell().add("23"));
        table2.addCell(new Cell().add("122,23"));
        table2.addCell(new Cell().add("631,89"));
        document.add(table2);
        
        Table table4 = new Table(4);
        table4.setMarginLeft(300f);
        table4.setMarginRight(100f);
        table4.setMarginBottom(30f);
        table4.addCell(new Cell().add("Według stawki VAT").setBackgroundColor(Color.LIGHT_GRAY));
        table4.addCell(new Cell().add("wartość netto").setBackgroundColor(Color.LIGHT_GRAY));
        table4.addCell(new Cell().add("kwota VAT").setBackgroundColor(Color.LIGHT_GRAY));
        table4.addCell(new Cell().add("wartość brutto").setBackgroundColor(Color.LIGHT_GRAY));

        table4.addCell(new Cell().add("podstawowy podatek VAT 23%"));
        table4.addCell(new Cell().add("722"));
        table4.addCell(new Cell().add("158"));
        table4.addCell(new Cell().add("881"));

        table4.addCell(new Cell().add("POdatek VAT 7%"));
        table4.addCell(new Cell().add("31"));
        table4.addCell(new Cell().add("2"));
        table4.addCell(new Cell().add("34"));
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
