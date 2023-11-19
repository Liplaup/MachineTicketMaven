package ticketmachine;

/**
 * TicketMachine models a naive ticket machine that issues flat-fare tickets. The price of a ticket is specified via the
 * constructor. It is a naive machine in the sense that it trusts its users to insert enough money before trying to print a
 * ticket. It also assumes that users enter sensible amounts.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public class TicketMachine {
	// The price of a ticket from this machine.
	private final int price;
	// The amount of money entered by a customer so far.
	private int balance;
	// The total amount of money collected by this machine.
	private int total;

	private int collectedAmount; // New attribute to track the collected amount

	private int montantCollecte; // Nouvel attribut pour suivre le montant collecté

	/**
	 * Create a machine that issues tickets of the given price.
	 *
	 * @param ticketCost the price of a ticket, >=0
	 */

	     /**
     * Return the total amount collected by the machine.
     *
     * @return the total amount collected by the machine.
     */
    public int getCollectedAmount() {
        return collectedAmount;
    }

	public int getMontantCollecte() {
        return montantCollecte;
    }

	public TicketMachine(int ticketCost) {
		// Test de validité du paramètre
		if (ticketCost <= 0) {
			throw new IllegalArgumentException("Ticket price must be positive");
		}
		price = ticketCost;
		balance = 0;
		total = 0;
	}

	/**
	 * Return the price of a ticket.
	 *
	 * @return the price of tickets for this machine
	 */
	public int getPrice() {
		return price;
	}

	

	/**
	 * Return the total amount collected by the machine.
	 *
	 * @return the total amount collected by the machine.
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @return the amount of money already inserted for the next ticket.
	 */
	public int getBalance() {
		return balance;
	}

// Méthode insertMoney() mise à jour pour gérer les montants négatifs
    /**
     * Reçoit un montant d'argent en centimes de la part d'un client.
     *
     * @param amount le montant inséré, en centimes (positif)
     * @throws IllegalArgumentException si le montant n'est pas positif
     */
    public void insertMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Montant négatif non autorisé");
        }
        balance = balance + amount;
    }


// Méthode refund() mise à jour pour retourner la monnaie correctement
    /**
     * Rembourse la balance au client.
     *
     * @return la balance remboursée
     */
	public int refund() {
		System.out.println("Je vous rends : " + balance + " centimes");
		int refundedAmount = balance; // Sauvegarde le montant à rembourser
		balance = 0; // Remet la balance à zéro
		return refundedAmount;
	}
	

	/**
	 * Print a ticket. Update the total collected and reduce the balance 
	 *
	 * @return vrai si le ticket a été imprimé, faux sinon
	 */
	public boolean printTicket() {
		if (balance >= price) {
			// Simule l'impression d'un ticket.
			System.out.println("##################");
			System.out.println("# La Ligne BlueJ");
			System.out.println("# Ticket");
			System.out.println("# " + price + " centimes.");
			System.out.println("##################");
			System.out.println();
	
			// Met à jour le montant total collecté, réduit la balance et met à jour le montant collecté
			total += price;
			collectedAmount += price; // Met à jour le montant collecté
			balance -= price;
	
			return true;
		} else {
			System.out.println("Fonds insuffisants pour imprimer le ticket.");
			return false;
		}
	}
}