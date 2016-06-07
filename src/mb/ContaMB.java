package mb;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.el.ELException;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.ContaDao;
import model.Conta;
import model.Usuario;

@ManagedBean
@SessionScoped
public class ContaMB {
	private Conta contaAtual = new Conta();
	private List<Conta> contas = new ArrayList<Conta>();
	private ContaDao dao = new ContaDao(); 
	private String contasString = "";
	
	public String inserir(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		UsuarioMB u = app.evaluateExpressionGet(ctx, "#{usuarioMB}", UsuarioMB.class);
		try {
			contaAtual = verificarDataAnterior(contaAtual);
			contaAtual.setUsuario(u.getUsuario());
			if(dao.adicionar(contaAtual)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Conta inserida com sucesso!", "Conta inserida com sucesso");
				ctx.addMessage("formConta", msg);
				init(u.getUsuario());
				return "main";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Conta não inserida!", "Conta não inserida");
		ctx.addMessage("formContaRegistro", msg);
		return "";
	}
	
	public String atualizar(Conta c){
		contaAtual = c;
		return "conta?faces-redirect=true";
	}
	
	public String update(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			contaAtual = verificarDataAnterior(contaAtual);
			if(dao.alterar(contaAtual)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Conta atualizada com sucesso!", "Conta atualizada com sucesso");
				ctx.addMessage("formConta", msg);
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
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Conta não atualizada!", "Conta não atualizada");
		ctx.addMessage("formContaRegistro", msg);
		return "";
	}
	
	public String excluir(Conta c){
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			if(dao.remover(c.getId())){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Conta removida com sucesso!", "Conta removida com sucesso");
				ctx.addMessage("formConta", msg);
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
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha na remoção da conta!", "Falha na remoção da conta");
		ctx.addMessage("formConta", msg);
		return "main";
	}
	
	@SuppressWarnings("deprecation")
	public Conta atualizarData(Conta c){
		switch(c.getFrequencia()){
			case 1: c.getProxData().setDate(c.getProxData().getDate() + 1); break;
			case 2: c.getProxData().setDate(c.getProxData().getDate() + 7); break;
			case 3: c.getProxData().setMonth(c.getProxData().getMonth() + 1); break;
			case 4: c.getProxData().setYear(c.getProxData().getYear() + 1); break;
		}
		return c;
	}
	
	public Conta verificarDataAnterior(Conta c){
		c.setProxData(new Date(c.getData().getTime()));
		if(c.getFrequencia() != 0){
			Date dateAux = new Date();
			while(c.getProxData().before(dateAux) ){
				c = atualizarData(c);
			}
		}
		return c;
	}
	
	public void init(Usuario user){
		try {
			contas = dao.todasContas(user);
			for(Conta c : contas){
				verificarDataAnterior(c);
			}
			this.setContasString(getContasString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String nova(){
		contaAtual = new Conta();
		return "conta?faces-redirect=true";
	}
	
	public String cancelar(){
		contaAtual = new Conta();
		return "main?faces-redirect=true";
	}

	public String getContasString() {
		StringBuilder builder = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for(Conta c : this.getContas()){
			builder.append(c.getDescricao() + "|||"+sdf.format(c.getData()) + "---");
		}
		return (builder.toString());
	}

	public void setContasString(String contasString) {
		this.contasString = contasString;
	}

	public Conta getContaAtual() {
		return contaAtual;
	}
	public void setContaAtual(Conta contaAtual) {
		this.contaAtual = contaAtual;
	}
	public List<Conta> getContas() {
		return contas;
	}
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	public ContaDao getDao() {
		return dao;
	}
	public void setDao(ContaDao dao) {
		this.dao = dao;
	}
}