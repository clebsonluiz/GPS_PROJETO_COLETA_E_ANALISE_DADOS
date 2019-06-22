package relatorio;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import entidade.Dado;
import entidade.EstruturaPesquisa;
import entidade.Pesquisa;
import entidade.Usuario;
import facade.Facade;
import util.ChartsUtil;
import util.RelatorioUtil;

public class Relatorio {

	public static Font TITULO_FONT = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD);
	public static Font CATEGORIA_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
	public static Font NORMAL_VERMELHA_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
	public static Font SUBCATEGORIA_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.NORMAL);
	public static Font SUBCATEGORIA_NEGRITO_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
	public static Font NORMAL_NEGRITO_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	public static Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
	
	public static BaseColor COR_FUNDO_NOME_COLUNA = BaseColor.DARK_GRAY;
	public static Font NOME_COLUNA_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.WHITE);
	public static BaseColor COR_CELULA_1 = new BaseColor(245, 245, 245);
	public static BaseColor COR_CELULA_2 = new BaseColor(220, 220, 220);
	
	//1 cm = 28.33333333333333 pontos
	public static final float CM_1 = 28.3f;
	
	//MARGENS do Documento
	public static final float M_ESQ = 3 * CM_1;
	public static final float M_DIR = 2 * CM_1;
	public static final float M_SUP = 3 * CM_1;
	public static final float M_INF = 2 * CM_1;
	
	public static final float LARGURA_TOTAL = 21 * CM_1;
	public static final float ALTURA_TOTAL = 29.7f * CM_1;
	
	private Document documento;
	private PdfWriter writer;
	
	private boolean mudarCor;
	
	
	public static void main(String[] args) {
		
		List<EstruturaPesquisa> estruturas = new ArrayList<>();
		
		Pesquisa p = new Pesquisa();
		p.setTitulo("Titulo da Pesquisa");
		p.setDescricao("Descrição da Pesquisa");
		p.setDataInicio(LocalDate.of(2019, 5, 1));
		p.setDataFim(LocalDate.of(2019, 9, 1));
		
		
		
		Usuario u = new Usuario();
		u.setNome("Usuário123");
		p.setUsuario(u);
		p.setEstruturaPesquisas(estruturas);
		
		EstruturaPesquisa e1 = new EstruturaPesquisa();
		EstruturaPesquisa e2 = new EstruturaPesquisa();
		EstruturaPesquisa e3 = new EstruturaPesquisa();
		EstruturaPesquisa e4 = new EstruturaPesquisa();
		EstruturaPesquisa e5 = new EstruturaPesquisa();
		EstruturaPesquisa e6 = new EstruturaPesquisa();
		
		Collections.addAll(estruturas, e1, e2, e3, e4, e5, e6);
		
		estruturas.forEach(es-> 
		{
			es.setCategoria_dados("Categoria dos Dados");
			es.setTitulo_estrutura("Titulo da Estrutura");
			es.setCol_1_nome_familia("Nome da Familia");
			es.setCol_2_nome("Nome do Dado");
			es.setCol_3_valor("Valor do Dado");
			
			List<Dado> dados = new ArrayList<>();
			
			Dado d1 = new Dado();
			Dado d2 = new Dado();
			Dado d3 = new Dado();
			Dado d4 = new Dado();
			Dado d5 = new Dado();
			Dado d6 = new Dado();
			Dado d7 = new Dado();
			Dado d8 = new Dado();
			Dado d9 = new Dado();
			Dado d10 = new Dado();
			Dado d11 = new Dado();
			Dado d12 = new Dado();
			Dado d13 = new Dado();
			
			Collections.addAll(dados, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13);
			
			dados.forEach(d->
			{
				d.setEstruturaPesquisa(es);
				d.setCol_1_nome_familia("Familia");
				d.setCol_2_nome("Nome");
				d.setCol_3_valor("Valor");
			});
			
			es.setDados(dados);
			
		});
		
		try 
		{
			gerarRelatorio("C:\\Users\\Aluno.WIN-OT9K4KMKI2A\\Desktop\\GPS_RELATORIO");
		} 
		catch (FileNotFoundException | DocumentException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public Document criarDocumento(String pathCaminho) throws FileNotFoundException, DocumentException
	{
		documento = new Document(PageSize.A4, M_ESQ, M_DIR, M_SUP, M_INF);
		writer = PdfWriter.getInstance(documento, new FileOutputStream(pathCaminho + ".pdf"));
		
		RelatorioUtil.HeaderFooterPageEvent event = new RelatorioUtil.HeaderFooterPageEvent();
		
        writer.setPageEvent(event);
		documento.open();
		return documento;
	}
	
	public static void gerarRelatorio(String caminho) throws FileNotFoundException, DocumentException
	{
		Document documento = new Relatorio().criarDocumento(caminho);
		
		//TODO
		
		documento.close();
	}
	
	public Paragraph addTitulo(String titulo)
	{
		Anchor anchor = new Anchor(titulo, TITULO_FONT);
		anchor.setName(titulo);
		Paragraph paragraph = new Paragraph(anchor);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		return paragraph;
	}
	
	public Paragraph addSubTitulo(String titulo)
	{
		Anchor anchor = new Anchor(titulo, CATEGORIA_FONT);
		anchor.setName(titulo);
		Paragraph paragraph = new Paragraph(anchor);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		return paragraph;
	}
	
	public Paragraph addParagrafo(String conteudo, Font font, int elementAlign) {
		Paragraph paragraph = new Paragraph(conteudo, font);
		paragraph.setAlignment(elementAlign);
		return paragraph;
	}
	
	public Image addImagem(java.awt.Image image) throws BadElementException, IOException
	{
		Image imagem = Image.getInstance(image, Color.white);
		return imagem;
	}
	
	
	public String getSQLPorcentagem(LocalDate dataInicial, LocalDate dataFinal)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String data1 = dataInicial.format(formatter);
		String data2 = dataFinal.format(formatter);
		
		String sql = "SELECT porcentagem from ( " + 
				" " + 
				"	SELECT ( " + 
				" " + 
				"	CASE WHEN (ABS(CAST('"+ data1 + "' AS DATE) - CAST('" + data2 + "' AS DATE)) <= 0) " + 
				"	THEN " + 
				"		100 " + 
				"	ELSE " + 
				"		(CAST(ABS(CAST('"+ data1 + "' AS DATE) - CURRENT_DATE) AS FLOAT)/CAST(ABS(CAST('"+ data1 + "' AS DATE) - CAST('"+ data2 + "' AS DATE)) as FLOAT))*100 " + 
				"	END) as porcentagem " + 
				" " + 
				") as alias_table";
				
		return sql;
	}
	
	
	public Image getGrafico(Pesquisa pesquisa) throws Exception {
		
		final java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 20);
		
		JFreeChart chart = ChartsUtil.getPizza("ANDAMENTO DO PRAZO DA PESQUISA (Em %)");
		PiePlot plot = (PiePlot) chart.getPlot();
		DefaultPieDataset dataSet = (DefaultPieDataset) plot.getDataset();
		
		chart.getLegend().setItemFont(font);
		
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator( "({2})"));
		plot.setLabelFont(font);
		
		plot.setShadowPaint(Color.gray);
		
		plot.setBackgroundPaint(Color.WHITE);
		chart.setBackgroundPaint(Color.white);
		
		int porc_andamento = (Integer) ((Double)Facade.getInstance().getBussinessPesquisa()
				.buscaSQLGenerica(getSQLPorcentagem(pesquisa.getDataInicio(), pesquisa.getDataFim()))).intValue();
		int porc_restante = 100 - porc_andamento;
		
		dataSet.setValue("Atual", porc_andamento);
		dataSet.setValue("Restante", porc_restante);
		plot.setExplodePercent("Atual", 0.15f);
		
		ChartsUtil.changeColorsPiePlot(plot, ChartsUtil.CORES_1);
		
		int width = 1000;
        int height = 400;
        
        BufferedImage bufferedImage = chart.createBufferedImage(width, height);
        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
        image.scalePercent(50);
		image.setAlignment(Element.ALIGN_CENTER);
		return image;
	}
	
	
	public Paragraph linhaDivisoria() {

		Paragraph paragrafo = new Paragraph("______________________________________________________________________________");
		paragrafo.setAlignment(Element.ALIGN_CENTER);
		return paragrafo;
	}
	
	public void addEmptyLine(Paragraph paragrafo, int numeroLinhas) {
		for (int i = 0; i < numeroLinhas; i++) {
			paragrafo.add(new Paragraph(" "));
		}
	}
	
	public String addTab(int n_tabs) 
	{
		String s = "";
		for (int i = 0; i < n_tabs; i++) {
			s += "    ";
		}
		return s;
	}
	
	public void conteudoPesquisa(String subTitulo,  Pesquisa pesquisa, Document documento) throws DocumentException
	{
		Paragraph paragrafo = new Paragraph();
		
		paragrafo.add(addSubTitulo(subTitulo));
		addEmptyLine(paragrafo, 2);
		paragrafo.add(addTitulo(pesquisa.getTitulo()));
		addEmptyLine(paragrafo, 1);
		paragrafo.add(addParagrafo(pesquisa.getDescricao(), SUBCATEGORIA_FONT, Element.ALIGN_JUSTIFIED));
		addEmptyLine(paragrafo, 2);
		Paragraph image = new Paragraph();
		try 
		{
			image.add(getGrafico(pesquisa));
		} 
		catch (Exception e) {e.printStackTrace();}
		image.setAlignment(Element.ALIGN_CENTER);
		paragrafo.add(image);
		
		paragrafo.add(new Paragraph("Criado por: " + pesquisa.getUsuario().getNome() + "."));
		addEmptyLine(paragrafo, 1);
		paragrafo.add(new Paragraph(
				"Data de ínicio da pesquisa: " + pesquisa.getDataInicio().toString() + "."
				+ "\n" + 
				"Data final da pesquisa: " + pesquisa.getDataFim().toString() + "."));
		
		
		/*try 
		{
			int dias = Fachada.getInstance().getBoProjeto().diferenca_Datas(projeto);
			int dias_decorridos = Fachada
					.getInstance()
					.getBoProjeto()
					.diferenca_Datas(
							projeto.getData_inicio(),
							DateUtil.getDataAtual()
							);
			paragrafo.add(new Paragraph("São " + dias + " dias ao todo e "+ 
							dias_decorridos + " dias decorridos desde a data de inicio do projeto."));
		} 
		catch (ValidacaoException e) {e.printStackTrace();}
		*/
		addEmptyLine(paragrafo, 1);
		
		//ADICIONAR PARAGRAFO
		documento.add(paragrafo);
	}
	
}
