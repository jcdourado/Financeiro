package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Recebimento;
import model.Usuario;

public class RecebimentoDao {
	
	public boolean adicionar(Recebimento c) throws SQLException, ClassNotFoundException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "INSERT INTO RECEBIMENTO(FREQUENCIA,NOME,DESCRICAO,VALOR,USUARIO) VALUES (?,?,?,?,?)";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, c.getFrequencia());
		pS.setString(2, c.getNome());
		pS.setString(3, c.getDescricao());
		pS.setFloat(4, c.getValor());
		pS.setString(5, c.getUsuario().getUsuario());
		return !pS.execute();
	}
	
	public boolean remover(int c) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "DELETE FROM RECEBIMENTO WHERE ID = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, c);
		return !pS.execute();
	}
	
	public boolean alterar(Recebimento c) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "UPDATE RECEBIMENTO SET FREQUENCIA = ?, NOME = ?, DESCRICAO = ?, VALOR = ? WHERE ID = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, c.getFrequencia());
		pS.setString(2, c.getNome());
		pS.setString(3, c.getDescricao());
		pS.setFloat(4, c.getValor());
		pS.setInt(5, c.getId());
		return !pS.execute();
	}
	
	public List<Recebimento> consultarPorNome(String nome, Usuario usuario) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "SELECT * FROM RECEBIMENTO WHERE NOME LIKE ? AND USUARIO = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, "%"+nome+"%");
		pS.setString(2, usuario.getUsuario());
		ResultSet rS = pS.executeQuery();
		List<Recebimento> recebimentos = new ArrayList<Recebimento>();
		while(rS.next()){
			Recebimento c = new Recebimento();
			c.setFrequencia(rS.getInt("FREQUENCIA"));
			c.setNome(rS.getString("nome"));
			c.setDescricao(rS.getString("descricao"));
			c.setUsuario(usuario);
			c.setValor(rS.getFloat("valor"));
			c.setId(rS.getInt("ID"));
			recebimentos.add(c);
		}
		return recebimentos;
	}
	
	public List<Recebimento> todasRecebimentos(Usuario usuario) throws ClassNotFoundException, SQLException{
		return consultarPorNome("", usuario);
	}
}
