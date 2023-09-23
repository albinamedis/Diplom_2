package stellarburgers.nomoreparties.orders;

import java.util.List;
import java.util.Random;

public class RandomIngredients {
    public String randomIngredients(List<String> idList) {
        Random random = new Random();
        int randomIndex = random.nextInt(idList.size());
        String randomId = idList.get(randomIndex);
        return randomId;
    }

    public String randomStringIngredients(List<String> idList) {

        StringBuilder requestBodyBuilder = new StringBuilder();
        Random random = new Random();

        //рандомное количество ингридиентов от 1 до 5
        int randomInt = random.nextInt(5) + 1;

        for (int i = 0; i < randomInt; i++) {
            // Генерация случайного индекса для выбора рандомного элемента из списка
            int randomIndex = random.nextInt(idList.size());
            // Получение рандомного элемента и добавление к строке
            String randomIngredient = idList.get(randomIndex);
            requestBodyBuilder.append("\"").append(randomIngredient).append("\"");
            // Если это не последний элемент, добавьте запятую
            if (i < randomInt - 1) {
                requestBodyBuilder.append(",");
            }
        }
    return requestBodyBuilder.toString();
    }

}
