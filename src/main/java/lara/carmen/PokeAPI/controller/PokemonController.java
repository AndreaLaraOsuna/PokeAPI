package lara.carmen.PokeAPI.controller;

import lara.carmen.PokeAPI.model.Pokemon;
import lara.carmen.PokeAPI.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    @Autowired
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // Obtener todos
    @GetMapping
    public List<Pokemon> listarPokemones() {
        return pokemonService.listarTodos();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> detallePokemon(@PathVariable Long id) {
        Pokemon pokemon = pokemonService.obtenerPokemonPorId(id);
        if (pokemon == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pokemon);
    }

    // Crear
    @PostMapping
    public ResponseEntity<Pokemon> guardarPokemon(@RequestBody Pokemon pokemon) {
        pokemon.setId(null); // fuerza a que sea un registro nuevo
        Pokemon nuevoPokemon = pokemonService.guardarPokemon(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPokemon);
    }

    // Actualizar completo (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarPokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        Pokemon actualizado = pokemonService.actualizarCompleto(id, pokemon);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // Actualizar parcial (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarParcialPokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        Pokemon actualizado = pokemonService.actualizarParcial(id, pokemon);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPokemon(@PathVariable Long id) {
        Pokemon pokemon = pokemonService.obtenerPokemonPorId(id);
        if (pokemon == null) {
            return ResponseEntity.notFound().build();
        }
        pokemonService.eliminarPokemon(id);
        return ResponseEntity.noContent().build();
    }
}
