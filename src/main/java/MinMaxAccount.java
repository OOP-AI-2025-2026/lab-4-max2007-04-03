import ua.opnu.java.inheritance.account.BankingAccount;
import ua.opnu.java.inheritance.account.Credit;
import ua.opnu.java.inheritance.account.Debit;
import ua.opnu.java.inheritance.account.Startup;

public class MinMaxAccount extends BankingAccount {
    private int minBalance;
    private int maxBalance;

    public MinMaxAccount(Startup s) {
        // Ініціалізуємо через конструктор суперкласу
        super(s);
        // Отримуємо початковий баланс
        int initialBalance = super.getBalance();
        // Ініціалізуємо мінімальне і максимальне значення
        this.minBalance = initialBalance;
        this.maxBalance = initialBalance;
    }

    @Override
    public void debit(Debit debit) {
        super.debit(debit);
        updateMinMax();
    }

    @Override
    public void credit(Credit credit) {
        super.credit(credit);
        updateMinMax();
    }

    private void updateMinMax() {
        int currentBalance = super.getBalance();
        if (currentBalance < minBalance) {
            minBalance = currentBalance;
        }
        if (currentBalance > maxBalance) {
            maxBalance = currentBalance;
        }
    }

    public int getMin() {
        return minBalance;
    }

    public int getMax() {
        return maxBalance;
    }
}