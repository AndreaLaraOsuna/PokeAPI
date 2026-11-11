package lara.carmen.PokeAPI.service;

import lara.carmen.PokeAPI.model.Pokemon;
import lara.carmen.PokeAPI.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> listarTodos() {
        return pokemonRepository.findAll();
    }

    public Pokemon obtenerPokemonPorId(Long id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon guardarPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    // PUT: reemplaza todos los atributos
    public Pokemon actualizarCompleto(Long id, Pokemon nuevo) {
        Pokemon existente = obtenerPokemonPorId(id);
        if (existente == null) {
            return null;
        }
        existente.setNombre(nuevo.getNombre());
        existente.setTipo(nuevo.getTipo());
        existente.setNivel(nuevo.getNivel());
        existente.setAtaque(nuevo.getAtaque());
        existente.setDefensa(nuevo.getDefensa());
        existente.setDescripcion(nuevo.getDescripcion());
        existente.setImagenUrl(nuevo.getImagenUrl());
        return pokemonRepository.save(existente);
    }

    // PATCH: actualiza solo los atributos que se enviaron (no nulos)
    public Pokemon actualizarParcial(Long id, Pokemon cambios) {
        Pokemon existente = obtenerPokemonPorId(id);
        if (existente == null) {
            return null;
        }
        if (cambios.getNombre() != null) existente.setNombre(cambios.getNombre());
        if (cambios.getTipo() != null) existente.setTipo(cambios.getTipo());
        if (cambios.getNivel() != null) existente.setNivel(cambios.getNivel());
        if (cambios.getAtaque() != null) existente.setAtaque(cambios.getAtaque());
        if (cambios.getDefensa() != null) existente.setDefensa(cambios.getDefensa());
        if (cambios.getDescripcion() != null) existente.setDescripcion(cambios.getDescripcion());
        if (cambios.getImagenUrl() != null) existente.setImagenUrl(cambios.getImagenUrl());
        return pokemonRepository.save(existente);
    }

    public void eliminarPokemon(Long id) {
        pokemonRepository.deleteById(id);
    }
}
