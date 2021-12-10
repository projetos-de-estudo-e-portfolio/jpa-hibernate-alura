package test;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        Produto produto = produtoDao.buscarPorId(1l);
        Cliente cliente = clienteDao.buscarPorId(1l);

        em.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: " +totalVendido);

        List<Object[]> relatorio = pedidoDao.relatorioDeVendas();
        for (Object[] obj : relatorio) {
            System.out.println(obj[0]);
            System.out.println(obj[1]);
            System.out.println(obj[2]);
        }
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi", "Muito legal", new BigDecimal("900"), celulares );

        Cliente cliente = new Cliente("Rodrigo", "123456");


        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);


        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
