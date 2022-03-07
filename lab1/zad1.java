package APS.lab1;

import java.util.Arrays;
import java.util.Scanner;

interface IMaraton {
    public Atleticar najdobroVreme();

    public int atleticariOd(String s);
}

class Atleticar {
    private String name;
    private String sex;
    private int age;
    private double time;
    private String country;

    public Atleticar() {
    }

    public Atleticar(String name, String sex, int age, double time, String country) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.time = time;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name + '\n' +
                age + '\n' +
                country + '\n' + time+ '\n';
    }
}

class Maraton implements IMaraton {
    private String place;
    private int year;
    private Atleticar[] athlets;

    public Maraton() {
    }

    public Maraton(String place, int year, Atleticar[] athlets) {
        this.place = place;
        this.year = year;
        this.athlets = athlets;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Atleticar[] getAthlets() {
        return athlets;
    }

    public void setAthlets(Atleticar[] athlets) {
        this.athlets = athlets;
    }


    @Override
    public String toString() {
        StringBuilder print = new StringBuilder(place + "\n"+  year + "\n");
        for (Atleticar athlete : athlets) {
            print.append(athlete.toString());
        }
        return print.toString();
    }

    @Override
    public Atleticar najdobroVreme() {
        int bestTime = 0;
        double min = athlets[0].getTime();
        for (int i = 0; i < athlets.length; i++) {
            if (min > athlets[i].getTime()) {
                min = athlets[i].getTime();
                bestTime = i;
            }
        }
        return athlets[bestTime];
    }

    @Override
    public int atleticariOd(String s) {
        int counter = 0;
        for (int i = 0; i < athlets.length; i++) {
            if (s.equals(athlets[i].getCountry())) {
                counter++;
            }
        }
        return counter;
    }
}

 class ZadacaMaraton {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Atleticar[] atleticari = new Atleticar[n];

        String ime;
        String pol;
        int vozrast;
        double vreme;
        String zemja;

        input.nextLine();

        for (int i = 0; i < n; i++) {
            ime = input.nextLine();
            pol = input.nextLine();
            vozrast = input.nextInt();
            vreme = input.nextDouble();
            input.nextLine();
            zemja = input.nextLine();
            atleticari[i] = new Atleticar(ime, pol, vozrast, vreme, zemja);
        }

        String mesto;
        int godina;
        String zemjaP;
        mesto = input.nextLine();
        godina = input.nextInt();
        input.nextLine();

        Maraton m1 = new Maraton(mesto, godina, atleticari);
        System.out.print(m1.toString());

        zemjaP = input.nextLine();
        System.out.println("Prvo mesto: " + m1.najdobroVreme().toString());
        System.out.println("Ima vkupno " + m1.atleticariOd(zemjaP) + " atleticar/i od " + zemjaP);
    }
}