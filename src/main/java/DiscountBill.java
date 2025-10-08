import ua.opnu.java.inheritance.bill.Employee;
import ua.opnu.java.inheritance.bill.GroceryBill;
import ua.opnu.java.inheritance.bill.Item;

public class DiscountBill extends GroceryBill {
    private boolean regularCustomer;
    private int discountCount = 0;
    private double totalDiscountAmount = 0.0;

    // Конструктор
    public DiscountBill(Employee clerk, boolean regularCustomer) {
        super(clerk);
        this.regularCustomer = regularCustomer;
    }

    // Перевизначення методу add(), щоб враховувати логіку знижок
    @Override
    public void add(Item item) {
        super.add(item);

        if (regularCustomer && item.getDiscount() > 0.0) {
            discountCount++;
            totalDiscountAmount += item.getDiscount();
        }
    }

    // Перевизначення методу getTotal() для врахування знижок
    @Override
    public double getTotal() {
        double fullTotal = super.getTotal();
        if (regularCustomer) {
            fullTotal -= totalDiscountAmount;
        }
        return Math.max(fullTotal, 0.0);
    }

    // Повертає кількість товарів зі знижкою
    public int getDiscountCount() {
        return discountCount;
    }

    // Повертає загальну суму знижки в гривнях
    public double getDiscountAmount() {
        return totalDiscountAmount;
    }

    // Повертає відсоток знижки на всі товари
    public double getDiscountPercent() {
        double fullTotal = super.getTotal();
        if (fullTotal == 0) {
            return 0.0;
        }
        return (totalDiscountAmount * 100) / fullTotal; // Формула для відсотків знижки
    }
}