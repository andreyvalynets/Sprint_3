package model;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {

    private String[] color;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;


    public Order(
            String firstName,
            String lastName,
            String address,
            String metroStation,
            String phone,
            int rentTime,
            String deliveryDate,
            String comment,
            String[] color
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Order getRandom(String[] color) {
        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String address = RandomStringUtils.randomAlphabetic(10);
        String metroStation = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomAlphabetic(10);
        int rentTime = RandomUtils.nextInt(0, 100);
        String deliveryDate = getRandomDate();
        String comment = RandomStringUtils.randomAlphabetic(10);

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    public static String getRandomDate() {
        int daysToExtract = RandomUtils.nextInt(0, 5000);
        LocalDate localDate = LocalDate.now().minusDays(daysToExtract);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(dateTimeFormatter);
    }
}