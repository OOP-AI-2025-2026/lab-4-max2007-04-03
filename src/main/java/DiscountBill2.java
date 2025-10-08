import ua.opnu.java.inheritance.bill.Employee;
import ua.opnu.java.inheritance.bill.GroceryBill;
import ua.opnu.java.inheritance.bill.Item;

public class DiscountBill2 {
    private final GroceryBill baseBill;
    private final boolean regularCustomer;
    private int discountCount = 0;
    private double discountAmount = 0.0;
    private double fullTotal = 0.0;

    public DiscountBill2(Employee clerk, boolean regularCustomer) {
        this.baseBill = new GroceryBill(clerk); // Створюємо базовий GroceryBill
        this.regularCustomer = regularCustomer;
    }

    // Додаємо товар до чеку
    public void add(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }

        baseBill.add(item); // Додаємо товар до базового чеку
        fullTotal += item.getPrice();

        if (regularCustomer && item.getDiscount() > 0.0) {
            discountCount++;
            discountAmount += item.getDiscount();
        }
    }

    // Повертаємо загальну суму покупок з урахуванням знижки
    public double getTotal() {
        return regularCustomer
                ? Math.round((fullTotal - discountAmount) * 100.0) / 100.0
                : Math.round(baseBill.getTotal() * 100.0) / 100.0;
    }

    // Отримати кількість товарів зі знижкою
    public int getDiscountCount() {
        return regularCustomer ? discountCount : 0;
    }

    // Отримати загальну знижку в гривнях
    public double getDiscountAmount() {
        return regularCustomer ? Math.round(discountAmount * 100.0) / 100.0 : 0.0;
    }

    // Обчислення відсотка знижки
    public double getDiscountPercent() {
        if (!regularCustomer || fullTotal <= 0.0) return 0.0;
        double discounted = getTotal();
        return Math.round((100.0 - (discounted * 100.0) / fullTotal) * 1e13) / 1e13;
    }

    // Повертає об'єкт працівника
    public Employee getClerk() {
        return baseBill.getClerk();
    }

    // Перевірка, чи є клієнт постійним
    public boolean isRegularCustomer() {
        return regularCustomer;
    }

    // Перевизначений метод toString() для зручного виводу інформації
    @Override
    public String toString() {
        return "DiscountBill2{"
                + "clerk=" + getClerk().getName()
                + ", total=" + getTotal()
                + ", discountCount=" + getDiscountCount()
                + ", discountAmount=" + getDiscountAmount()
                + ", discountPercent=" + getDiscountPercent() + "%}";
    }
}