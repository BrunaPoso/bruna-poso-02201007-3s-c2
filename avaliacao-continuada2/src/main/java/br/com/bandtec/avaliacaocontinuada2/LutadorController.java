package br.com.bandtec.avaliacaocontinuada2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @PostMapping
    public ResponseEntity postLutador(@RequestBody @Valid Lutador novoLutador) {
        repository.save(novoLutador);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getLutador() {
        List<Lutador> lutadores = repository.findAll();

        if (lutadores.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(repository.findAllByOrderByForcaGolpeDesc());
        }

    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getLutadorVivos() {
        List<Lutador> lutadores = repository.findAll();

        if (lutadores.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(repository.listaVivos());
        }

    }

    @PostMapping("/lutadores/{id}/concentrar")
    public ResponseEntity postLutador(@PathVariable int id, @RequestBody @Valid Lutador addConcentracao) {
        List<Lutador> concentracoes = repository.listaConcentracoes();
        if (concentracoes.isEmpty()) {
            System.out.println("Lutador já se concentrou 3 vezes!");
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.status(200).body(repository.listaConcentracoes());
        }
    }

    @GetMapping("/mortos")
    public ResponseEntity getLutadorMortos() {
        List<Lutador> lutadores = repository.findAll();

        if (lutadores.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(repository.listaMortos());
        }

    }


}
