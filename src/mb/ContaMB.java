package mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.ContaDao;
import model.Conta;
import model.Usuario;

@ManagedBean
@SessionScoped
public class ContaMB {
	private Conta contaAtual = new Conta();
	private List<Conta> contas = new ArrayList<Conta>();
	private ContaDao dao = new ContaDao(); 
	
	public void init(Usuario user){
		try {
			contas = dao.todasContas(user);
			System.out.println(contas);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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