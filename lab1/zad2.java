package APS.lab1;

import java.util.Scanner;

abstract class Patuvanje {
    private String agency;
    private int price;

    public Patuvanje(String agency, int price) {
        this.agency = agency;
        this.price = price;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public abstract int vratiVremeVoDenovi();

    public static int vratiMinCena(Patuvanje[] niza, int n, Patuvanje zaSporedba) {
        int minPrice = 0, flag=1;
        for (int i = 0; i < n; ++i) {
            if (niza[i].vratiVremeVoDenovi() > zaSporedba.vratiVremeVoDenovi()) {
                if (flag==1){
                    minPrice=niza[i].getPrice();
                    flag=0;
                }else{
                    if (minPrice > niza[i].getPrice()) {
                        minPrice = niza[i].getPrice();
                    }
                }
            }
        }
        return Math.max(minPrice, 0);

    }

    @Override
    public String toString() {
        return agency + " ";
    }


}

class PraznicnoPatuvanje extends Patuvanje {
    private int startDate;
    private int startMonth;
    private int endDate;
    private int endMonth;

    public PraznicnoPatuvanje(String agency, int price, int startDate, int startMonth, int endDate, int endMonth) {
        super(agency, price);
        if (testMonths(startMonth, endMonth)) {
            setDates(startDate, startMonth, endDate, endMonth);
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Iskluchok");
                setDates(startDate, endMonth, endDate, startMonth);
            }
        }
    }

    private void setDates(int startDate, int startMonth, int endDate, int endMonth) {
        this.startDate = startDate;
        this.startMonth = startMonth;
        this.endDate = endDate;
        this.endMonth = endMonth;
    }

    private boolean testMonths(int startingMonth, int endingMonth) {
        return endingMonth >= startingMonth;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getEndDate() {
        return endDate;
    }

    public int getEndMonth() {
        return endMonth;
    }

    @Override
    public int vratiVremeVoDenovi() {
        int days;
        if (startDate < endDate) {
            days = (endMonth - startMonth) * 30;
            days += endDate - startDate;
        } else {
            days = (endMonth - startMonth - 1) * 30;
            days = endDate + 30 - startDate;
        }
        return days;
    }


    @Override
    public String toString() {
        return getAgency() + " ";
    }
}

class GodishenOdmor extends Patuvanje {
    private int days;
    private static final int DISCOUNT = 1000;

    public GodishenOdmor(String agency, int price, int days) {
        super(agency, price - DISCOUNT);
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    @Override
    public int vratiVremeVoDenovi() {
        return days;
    }


    @Override
    public String toString() {
        return "GodishenOdmor{" +
                "days=" + days +
                '}';
    }

}

//вашиот код

 class Test {


    public static void main(String[] args) {


        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        Patuvanje nizaPatuvanje[] = new Patuvanje[n];

        for (int i = 0; i < n; i++) {
            int tip = in.nextInt();
            if (tip == 0) {
                String ime = in.next();
                int cena = in.nextInt();
                int vreme = in.nextInt();
                nizaPatuvanje[i] = new GodishenOdmor(ime, cena, vreme);
            } else {
                String ime = in.next();
                int cena = in.nextInt();
                int pocD = in.nextInt();
                int pocM = in.nextInt();
                int krajD = in.nextInt();
                int krajM = in.nextInt();
                nizaPatuvanje[i] = new PraznicnoPatuvanje(ime, cena, pocD, pocM, krajD, krajM);

            }
        }

        //решение на барање 1
        for (int i = 0; i < n; i++) {
            if (nizaPatuvanje[i] instanceof PraznicnoPatuvanje) {
                if (((PraznicnoPatuvanje) nizaPatuvanje[i]).getStartMonth() == 6) {
                    System.out.print(nizaPatuvanje[i].toString());
                }
            }
        }
        System.out.println("");
        //решение на барање 2
        double avg;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nizaPatuvanje[i].vratiVremeVoDenovi();
        }
        avg = sum * 1.0 / n;
        String avgrage=Double.toString(avg);
        System.out.println(avgrage);
        //решение на барање 3
        String agency = in.next();
        int price = in.nextInt();
        int days = in.nextInt();
        GodishenOdmor annualVacation = new GodishenOdmor(agency, price, days);

        //решение на барање 4
        System.out.println(Patuvanje.vratiMinCena(nizaPatuvanje,n,annualVacation));

    }

}
