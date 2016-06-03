package teste;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ContaDao;
import model.Conta;
import model.Usuario;

public class TesteContaDao {
	private static ContaDao dao;
	private static Usuario usuario;
	private static Conta conta;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new ContaDao();
		usuario = new Usuario();
		usuario.setNome("julio");
		usuario.setUsuario("julio43");
		usuario.setEmail("jcd17071997");
		usuario.setSenha("43432");
		conta = new Conta();
		conta.setDescricao("teste Cont321a");
		conta.setFrequencia(12);
		conta.setNome("Conta da 321321");
		conta.setValor(423555);
		conta.setUsuario(usuario);
		conta.setId(1);
	}
	
//	@Test
//	public void adicionar() throws ClassNotFoundException, SQLException {
//		assertTrue(dao.adicionar(conta));
//	}
//	@Test
//	public void alterar() throws ClassNotFoundException, SQLException {
//		assertTrue(dao.alterar(conta));
//	}
	@Test
	public void excluir() throws ClassNotFoundException, SQLException {
		assertTrue(dao.remover(conta.getId()));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
