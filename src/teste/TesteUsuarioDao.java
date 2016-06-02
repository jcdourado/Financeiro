package teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.UsuarioDao;
import model.Usuario;

public class TesteUsuarioDao {
	private static UsuarioDao dao;
	private static Usuario usuario;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new UsuarioDao();
		usuario = new Usuario();
		usuario.setNome("julio");
		usuario.setUsuario("julio2");
		usuario.setEmail("jcd17071997");
		usuario.setSenha("4343255");
	}
	
	@Test
	public void adicionar() {
		assertTrue(dao.adicionar(usuario));
	}
//	@Test
//	public void alterar() {
//		assertTrue(dao.alterar(usuario));
//	}
//	@Test
//	public void excluir() {
//		assertTrue(dao.remover(usuario));
//	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}



}
