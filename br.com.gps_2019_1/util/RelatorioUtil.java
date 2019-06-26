package util;

import java.awt.Color;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public interface RelatorioUtil {
	
	public class MyFooter extends PdfPageEventHelper {
	    Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);

	    public void onEndPage(PdfWriter writer, Document document) {
	        PdfContentByte cb = writer.getDirectContent();
	        Phrase header = new Phrase("this is a header", ffont);
	        Phrase footer = new Phrase("this is a footer", ffont);
	        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
	                header,
	                (document.right() - document.left()) / 2 + document.leftMargin(),
	                document.top() + 10, 0);
	        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
	                footer,
	                (document.right() - document.left()) / 2 + document.leftMargin(),
	                document.bottom() - 10, 0);
	    }
	}
	
	public class HeaderFooterPageEvent extends PdfPageEventHelper {

	    private PdfTemplate t;
	    private Image total;

	    public void onOpenDocument(PdfWriter writer, Document document) {
	        t = writer.getDirectContent().createTemplate(30, 16);
	        try {
	            total = Image.getInstance(t);
	            total.setRole(PdfName.ARTIFACT);
	        } catch (DocumentException de) {
	            throw new ExceptionConverter(de);
	        }
	    }

	    @Override
	    public void onEndPage(PdfWriter writer, Document document) {
	        addHeader(writer, document);
	        addFooter(writer);
	    }

	    private void addHeader(PdfWriter writer, Document document){
	        PdfPTable header = new PdfPTable(2);
	        try {
	            // set defaults
	        	// Array[] Tamanho das Celulas
	            header.setWidths(new int[]{7, 24});
	            header.setTotalWidth(527);
	            header.setLockedWidth(true);
	            header.getDefaultCell().setFixedHeight(40);
	            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
	            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

	            // add image
//	            java.awt.Image imagem = ViewUtil.Icones.IMAGEM_LOGO_PROJETO_1.getImage();
	            
//	            Image logo = Image.getInstance(imagem, Color.WHITE);
//	            logo.scalePercent(30);
	            PdfPCell textLogo = new PdfPCell();
	            textLogo.setPaddingBottom(10);
	            textLogo.setPaddingLeft(10);
	            textLogo.setBorder(Rectangle.BOTTOM);
	            textLogo.setBorderColor(BaseColor.LIGHT_GRAY);
//	            textLogo.addElement(logo);
//	            textLogo.addElement(new Phrase("iText PDF Library for Java", new Font(Font.FontFamily.HELVETICA, 7)));
	            header.addCell(textLogo);
	            
	            // add text
	            PdfPCell text = new PdfPCell();
	            text.setPaddingBottom(10);
	            text.setPaddingLeft(10);
	            text.setBorder(Rectangle.BOTTOM);
	            text.setBorderColor(BaseColor.LIGHT_GRAY);
	            text.setHorizontalAlignment(PdfPCell.ALIGN_TOP);
	            text.addElement(new Phrase("Relatorio PDF para PBD - GEST�O DE PROJETO", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
	            text.addElement(new Phrase("Tutorial para o Cabe�alho e Rodap� tirados de https://memorynotfound.com", new Font(Font.FontFamily.HELVETICA, 8)));
	            header.addCell(text);

	            // write content
//	            header.writeSelectedRows(0, -1, 34, 810, writer.getDirectContent());
	            header.writeSelectedRows(0, -1, 34, 830, writer.getDirectContent());
	        } catch(DocumentException de) {
	            throw new ExceptionConverter(de);
//	        } catch (MalformedURLException e) {
//	            throw new ExceptionConverter(e);
//	        } catch (IOException e) {
//	            throw new ExceptionConverter(e);
	        }
	    }

	    private void addFooter(PdfWriter writer){
	        PdfPTable footer = new PdfPTable(3);
	        try {
	            // set defaults
	            footer.setWidths(new int[]{24, 3, 1});
	            footer.setTotalWidth(527);
	            footer.setLockedWidth(true);
	            footer.getDefaultCell().setFixedHeight(40);
	            footer.getDefaultCell().setBorder(Rectangle.TOP);
	            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

	            // add copyright
//	            footer.addCell(new Phrase("Unidade Acad�mica de Serra Talhada - UFRPE", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GRAY)));
	         
	            // add image
	            PdfPCell textLogo = new PdfPCell();
	            textLogo.setPaddingBottom(10);
	            textLogo.setPaddingLeft(10);
	            textLogo.setBorder(Rectangle.TOP);
	            textLogo.setBorderColor(BaseColor.LIGHT_GRAY);
//	            try {
//	            	java.awt.Image imagem = ViewUtil.Icones.IMAGEM_LOGO_ITEXT.getImage();
//	            	
//	            	Image logo = Image.getInstance(imagem, Color.WHITE);
//	            	logo.scalePercent(6);
//	            	
//	            	textLogo.addElement(logo);
//					
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
	            textLogo.addElement(new Phrase("iText PDF Library for Java", new Font(Font.FontFamily.HELVETICA, 7)));
	            footer.addCell(textLogo);

	            // add current page count
	            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            footer.addCell(new Phrase(String.format("P�g. %d de", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

	            // add placeholder for total page count
	            PdfPCell totalPageCount = new PdfPCell(total);
	            totalPageCount.setBorder(Rectangle.TOP);
	            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
	            footer.addCell(totalPageCount);

	            // write page
	            PdfContentByte canvas = writer.getDirectContent();
	            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
	            footer.writeSelectedRows(0, -1, 34, 50, canvas);
	            canvas.endMarkedContentSequence();
	        } catch(DocumentException de) {
	            throw new ExceptionConverter(de);
	        }
	    }

	    public void onCloseDocument(PdfWriter writer, Document document) {
	        int totalLength = String.valueOf(writer.getPageNumber()).length();
	        int totalWidth = totalLength * 5;
	        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
	                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
	                totalWidth, 6, 0);
	    }
	}
	
}
