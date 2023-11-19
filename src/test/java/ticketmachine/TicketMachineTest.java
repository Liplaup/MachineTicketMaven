package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
public void setUp() {
    machine = new TicketMachine(PRICE); // On initialise l'objet à tester
    machine.insertMoney(0); // Réinitialise la balance à zéro
}


	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		// Les montants ont été correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}
	@Test
// S3 : On n'imprime pas le ticket si le montant inséré est insuffisant
void insufficientAmountDoesNotPrintTicket() {
    machine.insertMoney(30);
    assertFalse(machine.printTicket(), "Le ticket a été imprimé avec un montant insuffisant");
}

@Test
// S4 : On imprime le ticket si le montant inséré est suffisant
void sufficientAmountPrintsTicket() {
    machine.insertMoney(50);
    assertTrue(machine.printTicket(), "Le ticket n'a pas été imprimé avec un montant suffisant");
}

@Test
// S5 : Quand on imprime un ticket, la balance est décrémentée du prix du ticket
void balanceDecreasesAfterPrintingTicket() {
    machine.insertMoney(50);
    machine.printTicket();
    assertEquals(0, machine.getBalance(), "La balance n'a pas été correctement mise à jour après l'impression du ticket");
}

@Test
// S6 : Le montant collecté est mis à jour quand on imprime un ticket (pas avant)
void collectedAmountUpdatesAfterPrintingTicket() {
    machine.insertMoney(50);
    assertEquals(0, machine.getCollectedAmount(), "Le montant collecté n'est pas initialement à zéro");
    machine.printTicket();
    assertEquals(50, machine.getCollectedAmount(), "Le montant collecté n'a pas été correctement mis à jour après l'impression du ticket");
}

@Test
// S7 : refund() rend correctement la monnaie
void refundReturnsCorrectChange() {
    machine.insertMoney(70);
    assertEquals(70, machine.refund(), "La monnaie rendue n'est pas correcte");
}

@Test
// S8 : refund() remet la balance à zéro
void refundResetsBalanceToZero() {
    machine.insertMoney(30);
    machine.refund();
    assertEquals(0, machine.getBalance(), "La balance n'a pas été correctement remise à zéro après le remboursement");
}

@Test
// S9 : On ne peut pas insérer un montant négatif
void cannotInsertNegativeAmount() {
    assertThrows(IllegalArgumentException.class, () -> machine.insertMoney(-10), "Montant négatif inséré sans exception");
}

@Test
// S10 : On ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
void cannotCreateMachineWithNegativePrice() {
    assertThrows(IllegalArgumentException.class, () -> new TicketMachine(-50), "Machine créée avec un prix négatif sans exception");
}
}
