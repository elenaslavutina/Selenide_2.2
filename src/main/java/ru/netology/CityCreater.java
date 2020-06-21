package ru.netology;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class CityCreater {



    public static String getCity() {
        Random rnd = new Random();
        List<String> list = Arrays.asList(
                "Москва", "Санкт-Петербург","Нижний Новгород", "Новосибирск", "Тамбов",
                "Тула", "Саратов", "Псков", "Саранск", "Уфа", "Омск", "Красноярск");

        return list.get(rnd.nextInt(list.size()));
    }

}
