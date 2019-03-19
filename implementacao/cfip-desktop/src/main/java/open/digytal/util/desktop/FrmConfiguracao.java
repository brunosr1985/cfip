package open.digytal.util.desktop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import open.digytal.util.Imagem;
import open.digytal.util.desktop.ss.SSBotao;
import open.digytal.util.desktop.ss.SSCabecalho;
import open.digytal.util.desktop.ss.SSCaixaCombinacao;
import open.digytal.util.desktop.ss.SSCampoSenha;
import open.digytal.util.desktop.ss.SSCampoTexto;
import open.digytal.util.desktop.ss.SSMensagem;

public class FrmConfiguracao extends JFrame {
	private JPanel content = new JPanel();
	private SSBotao cmdConfirmar = new SSBotao();
	private SSBotao cmdSair = new SSBotao();
	
	private JPanel form = new JPanel();
	private SSCaixaCombinacao cboConfiguracao = new SSCaixaCombinacao();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private Configuracao configuracao;
	
	private JPanel pnlServer = new JPanel();
	private SSCampoTexto txtDbLogin = new SSCampoTexto();
	private SSCampoSenha txtDbSenha = new SSCampoSenha();
	private SSCampoTexto txtUrl = new SSCampoTexto();
	private SSCampoSenha txtDbRepeteSenha = new SSCampoSenha();
	public FrmConfiguracao() {
		init();
	}
	private void init() {
		this.setIconImage(Imagem.pngImage("app"));
		txtDbLogin.setTudoMaiusculo(false);
		txtDbSenha.setTudoMaiusculo(false);
		txtDbRepeteSenha.setTudoMaiusculo(false);
		txtUrl.setColunas(35);
		txtUrl.setTudoMaiusculo(false);
		
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		content.setLayout(new BorderLayout(0, 0));
		setContentPane(content);

		SSCabecalho cabecalho = new SSCabecalho();
		cabecalho.setDescricao("Configuração inicial do sistema");
		cabecalho.setTitulo("Configuração");
		content.add(cabecalho, BorderLayout.NORTH);

		JPanel botoes = new JPanel();
		botoes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout fl_botoes = (FlowLayout) botoes.getLayout();
		fl_botoes.setAlignment(FlowLayout.RIGHT);
		content.add(botoes, BorderLayout.SOUTH);
		cmdConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmar();
			}
		});

		cmdConfirmar.setText("Confirmar");
		cmdConfirmar.setIcone("ok");

		botoes.add(cmdConfirmar);
		cmdSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();

			}
		});

		cmdSair.setText("Sair");
		cmdSair.setIcone("fechar");
		botoes.add(cmdSair);
		form.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		content.add(form, BorderLayout.CENTER);
		GridBagLayout gbl_form = new GridBagLayout();
		form.setLayout(gbl_form);

		GridBagConstraints gbc_cboConfiguracao = new GridBagConstraints();
		gbc_cboConfiguracao.insets = new Insets(3, 3, 0, 3);
		gbc_cboConfiguracao.anchor = GridBagConstraints.NORTHWEST;
		gbc_cboConfiguracao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboConfiguracao.gridx = 0;
		gbc_cboConfiguracao.gridy = 1;
		cboConfiguracao.setRotulo("Conexão");
		form.add(cboConfiguracao, gbc_cboConfiguracao);

		
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(3, 0, 3, 0);
		gbc_tabbedPane.weightx = 1.0;
		gbc_tabbedPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_tabbedPane.weighty = 1.0;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 2;
		form.add(tabbedPane, gbc_tabbedPane);
		pnlServer.setLayout(new GridBagLayout());
		GridBagConstraints gbc_txtUrl = new GridBagConstraints();
		gbc_txtUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUrl.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtUrl.insets = new Insets(0, 3, 0, 3);
		gbc_txtUrl.gridx = 0;
		gbc_txtUrl.gridy = 0;
		
		txtUrl.setRotulo("URL");
		pnlServer.add(txtUrl, gbc_txtUrl);

		GridBagConstraints gbc_txtDbLogin = new GridBagConstraints();
		gbc_txtDbLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDbLogin.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtDbLogin.insets = new Insets(3, 3, 0, 3);
		gbc_txtDbLogin.gridx = 0;
		gbc_txtDbLogin.gridy = 1;
		
		txtDbLogin.setRotulo("Usuário");
		pnlServer.add(txtDbLogin, gbc_txtDbLogin);

		GridBagConstraints gbc_txtDbSenha = new GridBagConstraints();
		gbc_txtDbSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDbSenha.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtDbSenha.insets = new Insets(3, 3, 0, 3);
		gbc_txtDbSenha.gridx = 0;
		gbc_txtDbSenha.gridy = 2;
		
		txtDbSenha.setTudoMaiusculo(false);
		
		txtDbSenha.setRotulo("Senha");
		txtDbSenha.setColunas(10);
		pnlServer.add(txtDbSenha, gbc_txtDbSenha);

		GridBagConstraints gbc_txtDbRepeteSenha = new GridBagConstraints();
		gbc_txtDbRepeteSenha.weighty = 1.0;
		gbc_txtDbRepeteSenha.weightx = 1.0;
		gbc_txtDbRepeteSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDbRepeteSenha.anchor = GridBagConstraints.NORTHEAST;
		gbc_txtDbRepeteSenha.insets = new Insets(3, 3, 3, 3);
		gbc_txtDbRepeteSenha.gridx = 0;
		gbc_txtDbRepeteSenha.gridy = 3;
		
		txtDbRepeteSenha.setTudoMaiusculo(false);
		
		txtDbRepeteSenha.setRotulo("Confirma Senha");
		pnlServer.add(txtDbRepeteSenha, gbc_txtDbRepeteSenha);
		tabbedPane.addTab("ORIGEM", null, pnlServer, null);
		
		cboConfiguracao.setItens(Configuracao.CONFIGURACOES, "tipo");
		cboConfiguracao.setValue(Configuracao.LOCAL);
		cboConfiguracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				definirconfiguracao();
			}
		});
		
		definirconfiguracao();
	}
	private void definirconfiguracao() {
		configuracao = (Configuracao) cboConfiguracao.getValue();
		if (configuracao != null) {
			txtUrl.setText(Objects.toString(configuracao.getDbUrl(),configuracao.getApiUrl()));
			txtDbLogin.setText(configuracao.getDbUser());
			txtDbSenha.setText(configuracao.getDbPass());
			txtDbRepeteSenha.setText(configuracao.getDbPass());
		}
	}
	private void confirmar() {
		try {
			if(txtUrl.getText()==null || txtUrl.getText().trim().isEmpty()){
				SSMensagem.avisa("Informe uma URL");
				return ;
			}
			if(txtDbLogin.getText()==null || txtDbLogin.getText().trim().isEmpty()){
				SSMensagem.avisa("Informe o usuário do banco de dados");
				return ;
			}
			if(txtDbSenha.getText()==null || txtDbSenha.getText().trim().isEmpty()){
				SSMensagem.avisa("Informe a senha do banco de dados");
				return ;
			}
			if(!txtDbSenha.getText().equals(txtDbRepeteSenha.getSenha())){
				SSMensagem.avisa("Senhas não conferem");
				return; 
			}
			if(!SSMensagem.pergunta("Concluir a configuração atual")){
				return;
			}
			if(configuracao.getTipo().equals(Configuracao.CONF_API))
				configuracao.setApiUrl(txtUrl.getText());
			else
				configuracao.setDbUrl(txtUrl.getText());
			
			configuracao.setDbUser(txtDbLogin.getText());
			configuracao.setDbPass(txtDbSenha.getText());
			configurar();
			SSMensagem.informa("Acesse o sistema com as novas configurações");
			fechar();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void fechar() {
		System.exit(0);
	}
	//CONFIGURA UM AMBIENTE SELECIONADO
	private void configurar() throws Exception {
		String configuracao = configuracao();
		if(!configuracao.trim().isEmpty()){
			FileWriter writer=null;
			BufferedWriter bf=null;
			File arquivo = Configuracao.getArquivoConfiguracao();
			arquivo.createNewFile();
			writer = new FileWriter(arquivo);
			bf = new BufferedWriter(writer);
			bf.write(configuracao);
			bf.flush();
			bf.close();
			bf=null;
			writer=null;
		}
	}
	private  String configuracao() {
		StringBuilder sb =  new StringBuilder();
		if(!configuracao.getTipo().equals(Configuracao.CONF_API)) {
			sb.append(Configuracao.DB_DRIVER +"=" + configuracao.getDbDriver()+"\n");
			sb.append(Configuracao.DB_PASS +"="+ configuracao.getDbPass()  +"\n");
			sb.append(Configuracao.DB_USER +"="+ configuracao.getDbUser()  +"\n");
			sb.append(Configuracao.DB_DIALECT +"=" + configuracao.getDbDialect()+"\n");
			sb.append(Configuracao.DB_DDL +"=" + configuracao.getDbDdl()+"\n");
			sb.append(Configuracao.DB_SHOWSQL +"=" + configuracao.getDbShowSql()+"\n");
			sb.append(Configuracao.DB_URL +"="+ configuracao.getDbUrl()  +"\n");
		}else {
			sb.append(Configuracao.API_URL +"="+ configuracao.getApiUrl()  +"\n");
		}
		return sb.toString();
	}
	
	public static void iniciar(){
		FrmConfiguracao frm= new FrmConfiguracao();
		frm.setUndecorated(true);
		frm.pack();
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
	}

}