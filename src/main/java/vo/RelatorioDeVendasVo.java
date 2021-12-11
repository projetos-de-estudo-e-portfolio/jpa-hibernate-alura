package vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {
    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataUltivaVenda;

    public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida, LocalDate dataUltivaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataUltivaVenda = dataUltivaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataUltivaVenda=" + dataUltivaVenda +
                '}';
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public LocalDate getDataUltivaVenda() {
        return dataUltivaVenda;
    }
}
