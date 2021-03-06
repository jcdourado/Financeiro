package mb;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDao;
import model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private UsuarioDao dao = new UsuarioDao();
	
	public String logout() {
		limparCampos();
		return "index?faces-redirect=false";
	}
	
	public void limparCampos() {
		usuario.setUsuario(null);
		usuario.setNome(null);
		usuario.setEmail(null);
		usuario.setSenha(null);
		usuario.setLogado(false);
	}
	
	public String logar(){
		try {
			usuario = dao.consultar(usuario);
			if(usuario.getEmail() != null){
				usuario.setLogado(true);
				initAll();
				return "main?faces-redirect=true";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario ou senha inv�lidos", "Usuario ou senha inv�lidos");
		ctx.addMessage("formForm:txtUsuario", msg);
		return "";
	}
	
	public String registrarNovo(){
		try {
			if(dao.adicionar(usuario)){
				FacesContext ctx = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario cadastrado com sucesso", "Usuario cadastrado com sucesso");
				ctx.addMessage("formForm:txtUsuario", msg);
				return "";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario n�o cadastrado", "Usuario n�o cadastrado");
		ctx.addMessage("formForm:txtUsuario", msg);
		return "";
	}
	
	public String registrar(){
		usuario = new Usuario();
		return "usuario?faces-redirect=true";
	}

	public void initAll(){
		 FacesContext ctx = FacesContext.getCurrentInstance();
		 Application app = ctx.getApplication();
		 ContaMB contaMB = app.evaluateExpressionGet(ctx, "#{contaMB}", ContaMB.class);		 
		 contaMB.init(usuario);
		 RecebimentoMB recebimentoMB = app.evaluateExpressionGet(ctx, "#{recebimentoMB}", RecebimentoMB.class);
		 recebimentoMB.init(usuario);
	}
	
	public UsuarioDao getDao() {
		return dao;
	}
	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
