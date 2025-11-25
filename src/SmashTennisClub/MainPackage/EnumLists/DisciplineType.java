package SmashTennisClub.MainPackage.EnumLists;

public enum DisciplineType {
    JUNIOR(800.00),
    SENIOR(1500.00),
    //    PENSIONIST(1125.00),
    PASSIVT(250.00);


    private final double price;

    DisciplineType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Payment price: " + price + " kr";
    }
}



