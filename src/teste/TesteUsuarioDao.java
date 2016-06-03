package teste;

import static org.junit.Assert.*;

import java.sql.SQLException;

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
		usuario.setNome("julio3");
		usuario.setUsuario("julio43");
		usuario.setEmail("jcd17071997145");
		usuario.setSenha("4343255");
	}
	
	@Test
	public void adicionar() throws ClassNotFoundException, SQLException {
		assertTrue(dao.adicionar(usuario));
	}
//	@Test
//	public void alterar() throws ClassNotFoundException, SQLException {
//		assertTrue(dao.alterar(usuario));
//	}
//	@Test
//	public void excluir() throws ClassNotFoundException, SQLException {
//		assertTrue(dao.remover(usuario.getUsuario()));
//	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}



}
