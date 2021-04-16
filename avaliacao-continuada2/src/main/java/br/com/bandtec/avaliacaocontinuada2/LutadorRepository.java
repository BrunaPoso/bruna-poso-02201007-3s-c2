package br.com.bandtec.avaliacaocontinuada2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {

    List<Lutador> findAllByOrderByForcaGolpeDesc();

    @Query("from Lutador where vida > 0")
    List<Lutador> listaVivos();

    @Query("from Lutador where vida = 0")
    List<Lutador> listaMortos();

    @Query("from Lutador where concentracoesRealizadas > 3")
    List<Lutador> listaConcentracoes();

}
