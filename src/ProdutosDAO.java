import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet rs; // Mudamos para 'rs' para ser mais claro
    
    // Variável para reusar o objeto DTO
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    // ----------------------------------------------------
    // Atividade 2: Funcionalidade Cadastrar
    // ----------------------------------------------------
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = this.conn.prepareStatement(sql);
            
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.execute();
            
        } catch (SQLException erro) {
            // A VIEW deve exibir a mensagem, mas deixamos este debug
            System.out.println("Erro ao cadastrar produto: " + erro.getMessage());
        }
    }
    
    // ----------------------------------------------------
    // Atividade 2: Funcionalidade Listar (Completo)
    // ----------------------------------------------------
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos WHERE status = 'A Venda'"; // Listamos apenas os 'A Venda'
        
        try {
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            
            while(rs.next()){
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                lista.add(produto);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + erro.getMessage());
        }
        
        return lista;
    }

    // ----------------------------------------------------
    // Atividade 3 - COMMIT #1: Funcionalidade Vender
    // ----------------------------------------------------
    public void venderProduto(int idProduto) {
        conn = new conectaDAO().connectDB();
        
        // Comando SQL para atualização do status
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        
        try {
            prep = this.conn.prepareStatement(sql);
            
            // Define o ID do produto a ser vendido
            prep.setInt(1, idProduto);
            
            // Executa a atualização. executeUpdate é usado para INSERT, UPDATE, DELETE.
            prep.executeUpdate();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + erro.getMessage());
        }
    }
}