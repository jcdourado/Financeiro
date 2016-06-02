package teste;

import static org.junit.Assert.*;

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
		usuario.setUsuario("julio2");
		usuario.setEmail("jcd17071997");
		usuario.setSenha("43432");
		recebimento = new Recebimento();
		recebimento.setDescricao("teste recebimento");
		recebimento.setFrequencia(12);
		recebimento.setNome("recebimento da r3ua3");
		recebimento.setValor(423555);
		recebimento.setUsuario(usuario);
		recebimento.setId(13);
	}
	
//	@Test
//	public void adicionar() {
//		assertTrue(dao.adicionar(recebimento));
//	}
//	@Test
//	public void alterar() {
//		assertTrue(dao.alterar(recebimento));
//	}
	@Test
	public void excluir() {
		assertTrue(dao.remover(recebimento));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
