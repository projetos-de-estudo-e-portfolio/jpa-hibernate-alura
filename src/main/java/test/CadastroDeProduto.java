package test;

import dao.CategoriaDao;
import dao.ProdutoDao;
import model.Categoria;
import model.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args){
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoPorNome("Xiaomi");
        System.out.println("Preço do produto: " +precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi", "Muito legal", new BigDecimal("900"), celulares );

        Categoria videogames = new Categoria("VIDEOGAMES");
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("5000"), videogames );

        Categoria informatica = new Categoria("INFORMATICA");
        Produto macbook = new Produto("Macbook", "Macbook Pro", new BigDecimal("900"), informatica );

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        categoriaDao.cadastrar(videogames);
        produtoDao.cadastrar(videogame);

        categoriaDao.cadastrar(informatica);
        produtoDao.cadastrar(macbook);


        em.getTransaction().commit();
        em.close();
    }
}
