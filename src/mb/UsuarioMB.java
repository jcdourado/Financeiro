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
	
	
	public String logar(){
		try {
			usuario = dao.consultar(usuario);
			if(usuario.getEmail() != null){
				usuario.setLogado(true);
				initContas();
				return "main?faces-redirect=true";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario ou senha inválidos", "Usuario ou senha inválidos");
		ctx.addMessage("formLogin", msg);
		return "";
	}
	
	public String registrarNovo(){
		try {
			if(dao.adicionar(usuario)){
				FacesContext ctx = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario cadastrado com sucesso", "Usuario cadastrado com sucesso");
				ctx.addMessage("formLogin", msg);
				return "";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario não cadastrado", "Usuario não cadastrado");
		ctx.addMessage("formLogin", msg);
		return "";
	}
	
	public String registrar(){
		usuario = new Usuario();
		return "usuario?faces-redirect=true";
	}

	public void initContas(){
		 FacesContext ctx = FacesContext.getCurrentInstance();
		 Application app = ctx.getApplication();
		 ContaMB contaMB = app.evaluateExpressionGet(ctx, "#{contaMB}", ContaMB.class);		 
		 contaMB.init(usuario);
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
