package fr.haizen.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionState {

    CLIENT("Débit Client", 100), DRIVER("Crédit Chauffeur", 85), APPLICATION("Crédit Application", 15);

    private String state;
    private double pourcentageValue;

}
