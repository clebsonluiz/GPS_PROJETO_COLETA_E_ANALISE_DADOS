package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import app.app;
import entidade.Dado;
import entidade.EstruturaPesquisa;
import entidade.Pesquisa;
import exceptions.ValidacaoException;
import facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import view.Message;


public class ControlerCadastro implements Initializable {

	private Pesquisa pesquisa;
	private EstruturaPesquisa estruturaPesquisa;
	private Dado dado;
	private List<EstruturaPesquisa> estruturaPesquisas;
	private List<Dado> dados;
	
	
	@FXML
	private JFXTextField tituloPesqField;

	@FXML
	private JFXTextField descricaoPesqField;

	@FXML
	private JFXDatePicker inicioPesqDate;

	@FXML
	private JFXDatePicker fimPesqDate;
	
    @FXML
    private JFXButton addPesqBtn;

	@FXML
	private JFXButton addEstruPesqBtn;

	@FXML
	private JFXTextField tituloEstruPesqField;

	@FXML
	private JFXTextField categEstruPesqField;

	@FXML
	private JFXTextField nomeFamiEstruPesqField;

	@FXML
	private JFXTextField nomeEstruPesqField;

	@FXML
	private JFXTextField valorEstruPesqField;

	@FXML
	private JFXButton addDadosBtn;

	@FXML
	private JFXTextField nomeFamiliaDadosField;

	@FXML
	private JFXTextField nomeDadosField;

	@FXML
	private JFXTextField valorDadosField;

	@FXML
	private JFXButton cancelarCadBtn;

	@FXML
	private JFXButton finalizarCadBtn;

	@FXML
	void action(ActionEvent event) {

		if(event.getSource() == addPesqBtn) {
		
			cadastrar();
			limparCamposPesquisa();
			
		}
		
		
		if (event.getSource() == addEstruPesqBtn) {

			cadastrarEstrutura();
			limparCamposEstrutura();
			
		}

		if (event.getSource() == addDadosBtn) {

			cadastrarDados();
			limparCamposDados();
			
		}

		if (event.getSource() == finalizarCadBtn) {

			limparCamposPesquisa();
			/**
			 * Acessando o método de trocar Pane do controle Início.
			 */
			ControlerInicio.controleInicio.updateFrame("pesquisas");
			
			
		}

		if (event.getSource() == cancelarCadBtn) {

			limparCamposPesquisa();
			/**
			 * Acessando o método de trocar Pane do controle Início.
			 */
			ControlerInicio.controleInicio.updateFrame("pesquisas");
			
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	
	/**
	 * Método para adicionar pesquisa 
	 */
	public void cadastrar() {

		
		try
		{
			pesquisa = new Pesquisa();
			
			pesquisa.setTitulo(tituloPesqField.getText());
			pesquisa.setUsuario(ControlerLogin.getUsuario());
			pesquisa.setDescricao(descricaoPesqField.getText());
			pesquisa.setEstruturaPesquisas(estruturaPesquisas);
			pesquisa.setDataInicio(inicioPesqDate.getValue());
			pesquisa.setDataFim(fimPesqDate.getValue());
			
			Facade.getInstance().inserir(pesquisa);
		} 
		catch (ValidacaoException e) 
		{
			Message.getInstance().viewMessage(
					AlertType.ERROR, 
					"Erro", 
					"Erro ao cadastrar pesquisa",
					e.getMessage());
		}
	}

	
	/**
	 * Método para adicionar estrutura da pesquisa
	 */
	public void cadastrarEstrutura() {

		
		try
		{
			estruturaPesquisa = new EstruturaPesquisa();
			
			estruturaPesquisa.setTitulo_estrutura(tituloEstruPesqField.getText());
			estruturaPesquisa.setPesquisa(pesquisa);
			estruturaPesquisa.setDados(dados);
			estruturaPesquisa.setCol_3_valor(valorEstruPesqField.getText());
			estruturaPesquisa.setCol_2_nome(nomeEstruPesqField.getText());
			estruturaPesquisa.setCol_1_nome_familia(nomeFamiEstruPesqField.getText());
			estruturaPesquisa.setCategoria_dados(categEstruPesqField.getText());
			
			Facade.getInstance().inserir(estruturaPesquisa);
			
			estruturaPesquisas.add(estruturaPesquisa);
		} 
		catch (ValidacaoException e) 
		{
			Message.getInstance().viewMessage(
					AlertType.ERROR, 
					"Erro", 
					"Erro ao cadastrar estrutura",
					e.getMessage());
		}
	}

	/**
	 * Método para adicionar os dados da pesquisa
	 */
	public void cadastrarDados() {

		
		try
		{
			dado = new Dado();
			
			dado.setEstruturaPesquisa(estruturaPesquisa);
			dado.setCol_3_valor(valorDadosField.getText());
			dado.setCol_2_nome(nomeDadosField.getText());
			dado.setCol_1_nome_familia(nomeFamiliaDadosField.getText());
			
			Facade.getInstance().inserir(dado);
			
			dados.add(dado);
		} 
		catch (ValidacaoException e) 
		{
			Message.getInstance().viewMessage(
					AlertType.ERROR, 
					"Erro", 
					"Erro ao cadastrar dado",
					e.getMessage());
		}
		
	}

	
	/**
	 * Método para limpar campos da pesquisa
	 */
	public void limparCamposPesquisa() {

		tituloPesqField.clear();

		descricaoPesqField.clear();

		inicioPesqDate.getEditor().clear();

		fimPesqDate.getEditor().clear();

	}

	/**
	 * Método para limpar campos da estrutura da pesquisa
	 */
	public void limparCamposEstrutura() {

		tituloEstruPesqField.clear();

		categEstruPesqField.clear();

		nomeFamiEstruPesqField.clear();

		nomeEstruPesqField.clear();

		valorEstruPesqField.clear();

	}

	/**
	 * Método para limpar campos dos dados
	 */
	public void limparCamposDados() {

		nomeFamiliaDadosField.clear();

		nomeDadosField.clear();

		valorDadosField.clear();

	}
}
