package mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELException;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.RecebimentoDao;
import model.Recebimento;
import model.Usuario;

@ManagedBean
@SessionScoped
public class RecebimentoMB {
	private Recebimento recebimentoAtual = new Recebimento();
	private List<Recebimento> recebimentos = new ArrayList<Recebimento>();
	private RecebimentoDao dao = new RecebimentoDao(); 

	public String inserir(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		UsuarioMB u = app.evaluateExpressionGet(ctx, "#{usuarioMB}", UsuarioMB.class);
		try {
			recebimentoAtual.setUsuario(u.getUsuario());
			if(dao.adicionar(recebimentoAtual)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Recebimento inserido com sucesso!", "Recebimento inserido com sucesso");
				ctx.addMessage("formRecebimento", msg);
				init(u.getUsuario());
				return "main";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Recebimento não inserido!", "Recebimento não inserido");
		ctx.addMessage("formRecebimentoRegistro", msg);
		return "";
	}
	
	public String atualizar(Recebimento c){
		recebimentoAtual = c;
		return "recebimento?faces-redirect=true";
	}
	
	public String update(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			if(dao.alterar(recebimentoAtual)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Recebimento atualizado com sucesso!", "Recebimento atualizada com sucesso");
				ctx.addMessage("formRecebimento", msg);
				Application app = ctx.getApplication();
				UsuarioMB u = app.evaluateExpressionGet(ctx, "#{usuarioMB}", UsuarioMB.class);
				init(u.getUsuario());
				return "main";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ELException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Recebimento não atualizado!", "Recebimento não atualizado");
		ctx.addMessage("formRecebimentoRegistro", msg);
		return "";
	}
	
	public String excluir(Recebimento c){
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			if(dao.remover(c.getId())){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Recebimento removido com sucesso!", "Recebimento removida com sucesso");
				ctx.addMessage("formRecebimento", msg);
				Application app = ctx.getApplication();
				UsuarioMB u = app.evaluateExpressionGet(ctx, "#{usuarioMB}", UsuarioMB.class);
				init(u.getUsuario());
				return "";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ELException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha na remoção do recebimento!", "Falha na remoção do recebimento");	
		ctx.addMessage("formRecebimento", msg);
		return "main";
	}
	
	public void init(Usuario user){
		try {
			recebimentos = dao.todasRecebimentos(user);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String nova(){
		recebimentoAtual = new Recebimento();
		return "recebimento?faces-redirect=true";
	}
	
	public String cancelar(){
		recebimentoAtual = new Recebimento();
		return "main?faces-redirect=true";
	}

	public Recebimento getRecebimentoAtual() {
		return recebimentoAtual;
	}
	public void setRecebimentoAtual(Recebimento recebimentoAtual) {
		this.recebimentoAtual = recebimentoAtual;
	}
	public List<Recebimento> getRecebimentos() {
		return recebimentos;
	}
	public void setRecebimentos(List<Recebimento> recebimentos) {
		this.recebimentos = recebimentos;
	}
	public RecebimentoDao getDao() {
		return dao;
	}
	public void setDao(RecebimentoDao dao) {
		this.dao = dao;
	}
}