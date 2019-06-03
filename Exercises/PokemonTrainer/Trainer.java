package PokemonTrainer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String name;
    private Integer badges;
    private List<Pokemon> pokeCollection;

    public Trainer(String name) {
        this.name = name;
        this.badges = 0;
        this.pokeCollection = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBadges() {
        return badges;
    }

    public void setBadges(Integer badges) {
        this.badges = badges;
    }

    public List<Pokemon> getPokeCollection() {
        return pokeCollection;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokeCollection.add(pokemon);
    }
    public void absentPokemonPenalty() {
        List<Pokemon> collection = new ArrayList<>(this.pokeCollection);
        ArrayDeque<Integer> removePokemonIndexes = new ArrayDeque<>();

        for (Pokemon pokemon : collection) {

            pokemon.setHealth(pokemon.getHealth() - 10);
            if (pokemon.getHealth() <= 0) {
                removePokemonIndexes.push(collection.indexOf(pokemon));
            }
        }
        while (!removePokemonIndexes.isEmpty()) {
            int index = removePokemonIndexes.pop();
            collection.remove(index);
        }
        this.pokeCollection = new ArrayList<>(collection);
    }
    private Integer pokemonCount () {
        return this.pokeCollection.size();
    }
    public void awardBadge() {
        this.badges++;
    }
    public String printTrainer() {
        return String.format("%s %d %d"
        , this.name, this.badges, this.pokemonCount());
    }
}
