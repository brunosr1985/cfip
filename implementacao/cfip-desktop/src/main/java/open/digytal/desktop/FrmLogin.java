package open.digytal.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import open.digytal.CfipDesktopApp;
import open.digytal.model.Usuario;
import open.digytal.service.UsuarioService;
import open.digytal.util.desktop.DesktopApp;
import open.digytal.util.desktop.LoginPanel;
import open.digytal.util.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmLogin extends LoginPanel {
	@Autowired
	private UsuarioService service;
	public FrmLogin() {
		super.logar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logarAction();
			}
		});
	}

	private void logarAction() {
		try {
			Usuario usuario = service.login(getLogin(),getSenha());
			if (usuario==null) {
				SSMensagem.avisa("Credencial Inválida");
				FrmUsuario frm = CfipDesktopApp.getBean(FrmUsuario.class);
				frm.setVisible(true);
			} else {
				DesktopApp.setLogin(usuario.getLogin());;
				MDICfip mdi = CfipDesktopApp.getBean(MDICfip.class);
				mdi.exibirSessao();
				mdi.setVisible(true);
				this.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
