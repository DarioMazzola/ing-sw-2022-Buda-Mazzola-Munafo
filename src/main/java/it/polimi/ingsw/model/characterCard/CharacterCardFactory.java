package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.model.Bag;

import java.util.Map;

public class CharacterCardFactory {

    public CharacterCard getCharacterCard(String cardType, Map<String, Object> parameters) throws Exception{

        CharacterCard c;

        switch (cardType) {
            case "Farmer" :
                c = new Farmer();
                break;
            case "MagicalMailMan" :
                c = new MagicalMailMan();
                break;
            case "HerbGranma" :
                c = new HerbGranma();
                break;
            case "Minstrel" :
                c = new Minstrel();
                break;
            case "Monk" :
                c = new Monk((Bag) parameters.get(("Bag")));
                break;
            case "Herald" :
                c = new Herald();
                break;
            case "Centaur" :
                c = new Centaur();
                break;
            case "Jolly" :
                c = new Jolly((Bag) parameters.get(("Bag")));
                break;
            case "Knight" :
                c = new Knight();
                break;
            case "MushroomHunter" :
                c = new MushroomHunter();
                break;
            case "SpoiledPrincess" :
                c = new SpoiledPrincess((Bag) parameters.get(("Bag")));
                break;
            case "Thief" :
                c = new Thief();
                break;
            default :
                throw new IllegalArgumentException("There is no characterCard with this name");
        }

        return c;
    }
}