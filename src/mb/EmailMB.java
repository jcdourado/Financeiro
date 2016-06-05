package mb;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import dao.UsuarioDao;
import model.Usuario;
import services.Email;

@ManagedBean
@RequestScoped
public class EmailMB {
	private String email = "";
	private Usuario user = new Usuario();
	
	public String esqueci(){
		UsuarioDao dao = new UsuarioDao();
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			user = dao.consultar(email);
			if(user.getEmail() != null){
				Email serv = new Email(user.getEmail(), user.getUsuario(), user.getNome(), user.getSenha());
				serv.sendEmail();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email enviado com sucesso!", "Email enviado com sucesso!");
				ctx.addMessage("formEsqueci", msg);
				return "";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email não encontrado!", "Email não encontrado!");
		ctx.addMessage("formEsqueci", msg);
		return "";
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
