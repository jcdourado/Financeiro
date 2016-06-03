package teste;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.RecebimentoDao;
import model.Recebimento;
import model.Usuario;

public class TesteRecebimentoDao {
	private static RecebimentoDao dao;
	private static Usuario usuario;
	private static Recebimento recebimento;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new RecebimentoDao();
		usuario = new Usuario();
		usuario.setNome("julio");
		usuario.setUsuario("julio43");
		usuario.setEmail("jcd17071997");
		usuario.setSenha("43432");
		recebimento = new Recebimento();
		recebimento.setDescricao("teste recebimento");
		recebimento.setFrequencia(12);
		recebimento.setNome("recebimento da r443ua3");
		recebimento.setValor(423555);
		recebimento.setUsuario(usuario);
		recebimento.setId(1);
	}
	
//	@Test
//	public void adicionar() throws ClassNotFoundException, SQLException {
//		assertTrue(dao.adicionar(recebimento));
//	}
//	@Test
//	public void alterar() throws ClassNotFoundException, SQLException {
//		assertTrue(dao.alterar(recebimento));
//	}
	@Test
	public void excluir() throws ClassNotFoundException, SQLException {
		assertTrue(dao.remover(recebimento.getId()));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
