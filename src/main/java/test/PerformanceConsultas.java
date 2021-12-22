package test;

import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceConsultas {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
        em.close();
        System.out.println(pedido.getCliente().getNome());
    }

    private static void popularBancoDeDados(){
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi", "Muito legal", new BigDecimal("900"), celulares );
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("5000"), videogames );
        Produto macbook = new Produto("Macbook", "Macbook Pro", new BigDecimal("10000"), informatica );

        Cliente cliente = new Cliente("Rodrigo", "123456");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, celular));
        pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

        Pedido pedido2 = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(2, pedido, macbook));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        categoriaDao.cadastrar(videogames);
        produtoDao.cadastrar(videogame);
        categoriaDao.cadastrar(informatica);
        produtoDao.cadastrar(macbook);

        clienteDao.cadastrar(cliente);

    }
}

