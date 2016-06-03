package mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import daoJPA.UsuarioDao;
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
		Usuario u = null;
		if((u = dao.consultar(usuario)) != null){
			if(u.getSenha() != usuario.getSenha()){
				FacesContext ctx = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha inválida", "Senha inválida");
				ctx.addMessage("formLogin", msg);
				return "";
			}
			return "registrar";
		}
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario não cadastrado", "Usuario não cadastrado");
		ctx.addMessage("formLogin", msg);
		return "";
	}
	
	public String registrar(){
		return "registrar?faces-redirect=true";
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
